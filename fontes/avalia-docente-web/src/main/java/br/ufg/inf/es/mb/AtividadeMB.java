package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.repository.AtividadeRepository;

@ManagedBean
@SessionScoped
public class AtividadeMB {

	private List<Atividade> listaDeAtividades;
	
	@Autowired
	public AtividadeRepository atividadeRepository;
	
	private Atividade atividade;
	
	public AtividadeMB() {
		atividadeRepository = CustomApplicationContext.getInstance().getContext().getBean(AtividadeRepository.class);
	}

	public List<Atividade> getListaDeAtividades() {
		if (atividadeRepository != null) {
			listaDeAtividades = atividadeRepository.findAll();
		}else{
			listaDeAtividades = new ArrayList<>(); 
		}
		
		return listaDeAtividades;
	}
	
	public void setListaDeAtividades(List<Atividade> listaDeAtividades) {
		this.listaDeAtividades = listaDeAtividades;
	}
	
	public String adicionarAtividade(){
		if(atividade.getId() == null){
			atividadeRepository. saveAndFlush(atividade);
		}else{
			atividadeRepository.atualizarAtividade(
					atividade.getDescricao(), 
					atividade.getIndice(), 
					atividade.getId());
		}
		
		return "atividades";
	}
	
	public String prepararAlterarAtividade(Atividade atividadeCarregada){
		this.atividade = atividadeCarregada;
		
		return "gerenciaAtividade";
	}
	
	public String excluirAtividade(Atividade atividadeCarregada){
		this.atividade = atividadeCarregada;
		
		atividadeRepository.excluirAtividade(atividade.getId());
		
		return "atividades";
	}
	
	public String preparaAdicionarAtividade(){
		atividade = new Atividade();
		
		return "gerenciaAtividade";
	}

	public Atividade getAtividade() {
		/*if(atividade == null){
			atividade = new Atividade();
		}*/
		
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
}