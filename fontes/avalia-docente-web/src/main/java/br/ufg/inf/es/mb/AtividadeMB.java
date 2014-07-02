package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Multiplicador;
import br.ufg.inf.es.avaliadocente.repository.AtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.MultiplicadorRepository;

/**
 * 
 * @author diogo.japiassu
 *
 */
@ManagedBean
@SessionScoped
public class AtividadeMB {

	private List<Atividade> listaDeAtividades;
	private List<GrupoAtividade> listaDeGruposDeAtividades;
	private List<Multiplicador> listaDeMultiplicadores;
	
	@Autowired
	public AtividadeRepository atividadeRepository;
	
	@Autowired
	public GrupoAtividadeRepository grupoAtividadeRepository;
	
	@Autowired
	public MultiplicadorRepository multiplicadorRepository;
	
	private Atividade atividade;
	
	private Long idGrupoEscolhido;
	private Long idMultiplicadorEscolhido;
	
	public AtividadeMB() {
		atividadeRepository = CustomApplicationContext.getInstance().getContext().getBean(AtividadeRepository.class);
		grupoAtividadeRepository = CustomApplicationContext.getInstance().getContext().getBean(GrupoAtividadeRepository.class);
		multiplicadorRepository = CustomApplicationContext.getInstance().getContext().getBean(MultiplicadorRepository.class);
	}

	public List<Atividade> getListaDeAtividades() {
		if (atividadeRepository != null) {
			listaDeAtividades = atividadeRepository.findAllOrdenadoPorId();
		}else{
			listaDeAtividades = new ArrayList<>(); 
		}
		
		return listaDeAtividades;
	}
	
	public void setListaDeAtividades(List<Atividade> listaDeAtividades) {
		this.listaDeAtividades = listaDeAtividades;
	}
	
	public String adicionarAtividade(){
		if(idGrupoEscolhido != null){
			GrupoAtividade grupoEscolhido = grupoAtividadeRepository.findOne(idGrupoEscolhido);
			atividade.setGrupoAtividade(grupoEscolhido);
		}else{
			atividade.setGrupoAtividade(null);
		}
		
		if(idMultiplicadorEscolhido != null){
			Multiplicador multiplicadorEscolhido = multiplicadorRepository.findOne(idMultiplicadorEscolhido);
			atividade.setMultiplicador(multiplicadorEscolhido);
		}else{
			atividade.setMultiplicador(null);
		}
		
		atividadeRepository.save(atividade);
		
		/*if(atividade.getId() == null){
			atividadeRepository. saveAndFlush(atividade);
		}else{
			atividadeRepository.atualizarAtividade(
					atividade.getDescricao(), 
					atividade.getIndice(), 
					atividade.getId());
		}*/
		
		return "atividades";
	}
	
	public String prepararAlterarAtividade(Atividade atividadeCarregada){
		this.atividade = atividadeCarregada;
		
		if(atividadeCarregada.getGrupoAtividade() != null){
			idGrupoEscolhido = atividadeCarregada.getGrupoAtividade().getId();
		}else{
			idGrupoEscolhido = null;
		}
		
		if(atividadeCarregada.getMultiplicador() != null){
			idMultiplicadorEscolhido = atividadeCarregada.getMultiplicador().getId();
		}else{
			idMultiplicadorEscolhido = null;
		}
		
		return "gerenciaAtividade";
	}
	
	public String excluirAtividade(Atividade atividadeCarregada){
		this.atividade = atividadeCarregada;
		
		atividadeRepository.delete(atividade);
		
		return "atividades";
	}
	
	public String preparaAdicionarAtividade(){
		limparAtividade();
		
		return "gerenciaAtividade";
	}

	private void limparAtividade() {
		atividade = new Atividade();
		idGrupoEscolhido = null;
		idMultiplicadorEscolhido = null;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<GrupoAtividade> getListaDeGruposDeAtividades() {
		if (grupoAtividadeRepository != null) {
			listaDeGruposDeAtividades = grupoAtividadeRepository.findAll();
		}else{
			listaDeGruposDeAtividades = new ArrayList<>(); 
		}
		
		return listaDeGruposDeAtividades;
	}

	public void setListaDeGruposDeAtividades(
			List<GrupoAtividade> listaDeGruposDeAtividades) {
		this.listaDeGruposDeAtividades = listaDeGruposDeAtividades;
	}
	
	public List<Multiplicador> getListaDeMultiplicadores() {
		if(multiplicadorRepository != null){
			listaDeMultiplicadores = multiplicadorRepository.findAllOrdenadoPorId();
		}else{
			listaDeMultiplicadores = new ArrayList<>();
		}
		
		return listaDeMultiplicadores;
	}

	public void setListaDeMultiplicadores(List<Multiplicador> listaDeMultiplicadores) {
		this.listaDeMultiplicadores = listaDeMultiplicadores;
	}

	public Long getIdGrupoEscolhido() {
		return idGrupoEscolhido;
	}

	public void setIdGrupoEscolhido(Long idGrupoEscolhido) {
		this.idGrupoEscolhido = idGrupoEscolhido;
	}

	public Long getIdMultiplicadorEscolhido() {
		return idMultiplicadorEscolhido;
	}

	public void setIdMultiplicadorEscolhido(Long idMultiplicadorEscolhido) {
		this.idMultiplicadorEscolhido = idMultiplicadorEscolhido;
	}
}