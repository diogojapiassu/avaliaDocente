package br.ufg.inf.es.avaliadocente.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.exception.AvaliacaoProcessamentoException;
import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.NotasGrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.QuadroSumario;
import br.ufg.inf.es.avaliadocente.model.bean.builder.NotasGrupoAtividadeBuilder;
import br.ufg.inf.es.avaliadocente.model.bean.builder.QuadroSumarioBuilder;
import br.ufg.inf.es.avaliadocente.repository.AtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.NotasGrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.QuadroSumarioRepository;
import br.ufg.inf.es.avaliadocente.util.MethodProfiling;

/**
 * Trata uma {@link Avaliacao}. Mantém todo o fluxo de processamento de uma {@link Avaliacao}.
 * 
 * <ol>
 * <li>obtém o {@link Docente} da {@link Avaliacao}</li>
 * <li>consulta o docente em banco de dados (caso nao encontrado, insere)</li>
 * <li>constrói um {@link QuadroSumario}</li>
 * <li>itera na lista de {@link GrupoAtividade} da avaliação</li>
 * <li>para cada {@link GrupoAtividade}, instancia um {@link NotasGrupoAtividade}</li>
 * <li>trata todas as {@link Atividade}s do {@link GrupoAtividade} e insere numa lista de {@link NotasGrupoAtividade}</li>
 * <li>persiste o {@link QuadroSumario}</li>
 * <li>fim do processamento ;)</li>
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliacaoHandler implements Runnable {
	
	private static final Logger LOG = Logger.getLogger(AvaliacaoHandler.class);

	private Avaliacao avaliacao;
	
	private MethodProfiling p = new MethodProfiling();
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	QuadroSumarioRepository quadroSumarioRepository;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	GrupoAtividadeRepository grupoAtividadeRepository;

	@Autowired
	NotasGrupoAtividadeRepository notasGrupoAtividadeRepository;
	
	public AvaliacaoHandler() {	}
	
	public AvaliacaoHandler(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	@Override
	public void run() {
		try {
			p.iniciarMedicao();
			LOG.info("Iniciando processamento de avaliacao");
			
			if (avaliacao != null) {
				Docente docente = avaliacao.getDocente();
				
				Docente docenteBD = docenteRepository
						.findByNomeAndMatricula(docente.getNome(), docente.getMatricula());
				
				if (docenteBD == null) {
					docenteBD = docente;
					docenteRepository.save(docenteBD);
				}
				
				QuadroSumario sumario = new QuadroSumarioBuilder()
						.docente(docenteBD)	
						.build();
				
				List<NotasGrupoAtividade> notas = new ArrayList<>();
				for (GrupoAtividade gpr : avaliacao.getGrupoAtividade()) {
					NotasGrupoAtividade notasGrupoAtividade = new NotasGrupoAtividadeBuilder().quadroSumario(sumario).build();
					
					tratarTodasAtividadesDoGrupoDeAtividades(gpr, notasGrupoAtividade);
					
					notas.add(notasGrupoAtividade);
					
				}
				
				quadroSumarioRepository.save(sumario);
				notasGrupoAtividadeRepository.save(notas);
			}
			
			p.finalizarMedicao();
			LOG.info("Avaliacao processada em "+ p.tempoExecucao() + " segundos");
		} catch (Exception e) {
			String mensagem = String.format("Houve algum problema ao processar a avalicao %s", this.avaliacao.toString());
			LOG.error(mensagem);
			throw new AvaliacaoProcessamentoException(mensagem, e);
		}
	}

	/**
	 * 
	 * @param gpr grupo de atividade cuja lista de atividade será tratada.
	 * @param notasGrupoAtividade 
	 */
	private void tratarTodasAtividadesDoGrupoDeAtividades(GrupoAtividade gpr, NotasGrupoAtividade notasGrupoAtividade) {
		LOG.info("Tratando todas as atividades do grupo de atividades de indice " + gpr.getIndice());
		
		//Sincroniza com o banco somente para nao dar TransientObjectException...
		GrupoAtividade gprTemp = grupoAtividadeRepository.findByIndice(gpr.getIndice());
		notasGrupoAtividade.setGrupoAtividade(gprTemp);
		
		for (Atividade atv : gpr.getAtividades()) {
			LOG.info("Tratando a atividade de indice " + atv.getIndice());
			
			//Sincronizando com o banco...
			Atividade atvTemp = atividadeRepository.findByGrupoAtividadeAndIndice(gprTemp.getId(), atv.getIndice());
			Long pesoMultiplicador = atvTemp.getMultiplicador().getFatorMultiplicador().longValue();
			
			if (atvTemp.getMultiplicador().isValorado()) {
				//Peso da atividade que veio na avaliacao
				Long pesoAtividade = atv.getValor();
				
				if (pesoAtividade == null) {
					pesoAtividade = new Long(0);
				}
				
				//Atividade 'composta' que possui o fatorMultiplicador e um dado de entrada
				Long valor = pesoAtividade * pesoMultiplicador;
				notasGrupoAtividade.addValor(new BigDecimal(valor));
			} else {
				//Atividade 'simples' que nao possui valor de entrada (apenas o fatorMultiplicador mesmo)
				notasGrupoAtividade.addValor(new BigDecimal(pesoMultiplicador));
			}
		}
	}
	
	/**
	 * Método invocado quando o bean é destruído pelo Spring.
	 */
	public void destroy() {
		avaliacao = null;
		p = null;
		docenteRepository = null;
		quadroSumarioRepository = null;
		atividadeRepository = null;
		grupoAtividadeRepository = null;
		notasGrupoAtividadeRepository = null;
	}

}
