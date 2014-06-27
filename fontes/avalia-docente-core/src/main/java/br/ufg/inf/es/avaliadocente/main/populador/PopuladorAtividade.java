package br.ufg.inf.es.avaliadocente.main.populador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Multiplicador;
import br.ufg.inf.es.avaliadocente.model.bean.builder.AtividadeBuilder;
import br.ufg.inf.es.avaliadocente.repository.AtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.MultiplicadorRepository;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

/**
 * 
 * @author Danilo Guimarães
 *
 */
public class PopuladorAtividade implements IPopulador {
	
	private static final Logger LOG = Logger.getLogger(PopuladorAtividade.class);

	
	List<Atividade> atividades = new ArrayList<>();
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	GrupoAtividadeRepository grupoAtividadeRepository;
	
	@Autowired
	MultiplicadorRepository multiplicadorRepository;
	
	@Override
	public void popular() {
		String csv = getConteudoArquivoAtividades();
		
		if (csv != null) {
			String[] linhas = FileUtils.splitByNewLine(csv);
			
			for (String linha : linhas) {
				Atividade atv = atividadeFromCSV(linha);
				atividades.add(atv);
			}
			atividadeRepository.save(atividades);
		} else {
			return;
		}
	}

	/**
	 * Obtém o conteúdo do arquivo CSV contendo as atividades
	 * que serão cadastradas no sistema.
	 * 
	 * @return conteúdo do arquivo CSV.
	 */
	private String getConteudoArquivoAtividades() {
		String csv = null;
		try {
			csv = FileUtils.getContent("cadastro/atividades/atividades.csv", "UTF-8");
		} catch (IOException e) {
			LOG.error("Falha ao obter o arquivo de atividades: ", e);
		}
		return csv;
	}
	
	public Atividade atividadeFromCSV(String line) {
		String[] values = FileUtils.splitByComma(line);
		
		if (NumberUtils.isNumber(values[0])) {
			Long idGrupoAtividade = Long.parseLong(values[0]);
			
			GrupoAtividade grp = grupoAtividadeRepository.findOne(idGrupoAtividade);
			Long indice = Long.parseLong(values[1]);
			String descricao = values[2];
			BigDecimal multiplicador = new BigDecimal(values[3]);
			
			BigDecimal valorMaximo = null;
			if (StringUtils.isNotBlank(values[4])) {
				valorMaximo = new BigDecimal(values[4]);
			}
			
			Boolean isValorado = Boolean.parseBoolean(values[5]);
			
			Multiplicador multi = null;
			
			Atividade atv = new AtividadeBuilder().indice(indice)
				.descricao(descricao)
				.grupoAtividade(grp)
				.build();
			
			multi = new Multiplicador(multiplicador, valorMaximo, isValorado);
			atv.setMultiplicador(multi);
			
			multiplicadorRepository.save(multi);
			
			return atv;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		new PopuladorAtividade().popular();
	}

}
