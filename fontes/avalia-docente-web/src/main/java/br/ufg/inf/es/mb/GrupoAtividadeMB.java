package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;

/**
 * 
 * @author diogo.japiassu
 *
 */
@ManagedBean
@SessionScoped
public class GrupoAtividadeMB {

	private List<GrupoAtividade> listaDeGruposDeAtividades;
	
	private List<GrupoAtividade> listaDeGruposDeAtividadesPai;
	
	@Autowired
	public GrupoAtividadeRepository grupoAtividadeRepository;
	
	private GrupoAtividade grupoAtividade;
	
	private Long idGrupoPaiEscolhido;
	
	public GrupoAtividadeMB() {
		grupoAtividadeRepository = CustomApplicationContext.getInstance().getContext().getBean(GrupoAtividadeRepository.class);
	}
	
	public String adicionarGrupoAtividade(){
		grupoAtividade = grupoAtividadeRepository.save(grupoAtividade);
		
		if(idGrupoPaiEscolhido != null){
			GrupoAtividade grupoEscolhido = grupoAtividadeRepository.findOne(idGrupoPaiEscolhido);
			grupoAtividade.setGrupoAtividadePai(grupoEscolhido);
		}else{
			grupoAtividade.setGrupoAtividadePai(null);
		}
		
		grupoAtividadeRepository.save(grupoAtividade);
		
		/*if(grupoAtividade.getId() == null){
			grupoAtividadeRepository. saveAndFlush(grupoAtividade);
		}else{
			grupoAtividadeRepository.atualizarGrupoAtividade(
					grupoAtividade.getDescricao(), 
					grupoAtividade.getId());
		}*/
		
		return "grupoAtividades";
	}
	
	public String prepararAlterarGrupoAtividade(GrupoAtividade grupoAtividadeCarregada){
		this.grupoAtividade = grupoAtividadeCarregada;
		getListaDeGruposDeAtividadesPai();
		
		if(grupoAtividadeCarregada.getGrupoAtividadePai() != null){
			idGrupoPaiEscolhido = grupoAtividadeCarregada.getGrupoAtividadePai().getId();
		}else{
			idGrupoPaiEscolhido = null;
		}
		
		return "gerenciaGrupoAtividade";
	}
	
	public String excluirGrupoAtividade(GrupoAtividade grupoAtividadeCarregada){
		this.grupoAtividade = grupoAtividadeCarregada;
		
		grupoAtividadeRepository.delete(grupoAtividade);
		
		return "grupoAtividades";
	}
	
	public String preparaAdicionarGrupoAtividade(){
		grupoAtividade = new GrupoAtividade();
		idGrupoPaiEscolhido = null;
		getListaDeGruposDeAtividadesPai();
		
		return "gerenciaGrupoAtividade";
	}
	
	public List<GrupoAtividade> getListaDeGruposDeAtividadesPai() {
		if (grupoAtividadeRepository != null) {
			listaDeGruposDeAtividadesPai = grupoAtividadeRepository.findAllOrdenadoPorId();
		}else{
			listaDeGruposDeAtividadesPai = new ArrayList<>(); 
		}
		
		return listaDeGruposDeAtividadesPai;
	}

	public void setListaDeGruposDeAtividadesPai(
			List<GrupoAtividade> listaDeGruposDeAtividadesPai) {
		this.listaDeGruposDeAtividadesPai = listaDeGruposDeAtividadesPai;
	}

	public List<GrupoAtividade> getListaDeGruposDeAtividades() {
		if (grupoAtividadeRepository != null) {
			listaDeGruposDeAtividades = grupoAtividadeRepository.findAllOrdenadoPorId();
		}else{
			listaDeGruposDeAtividades = new ArrayList<>(); 
		}
		
		return listaDeGruposDeAtividades;
	}

	public void setListaDeGruposDeAtividades(
			List<GrupoAtividade> listaDeGruposDeAtividades) {
		this.listaDeGruposDeAtividades = listaDeGruposDeAtividades;
	}

	public Long getIdGrupoPaiEscolhido() {
		return idGrupoPaiEscolhido;
	}

	public void setIdGrupoPaiEscolhido(Long idGrupoPaiEscolhido) {
		this.idGrupoPaiEscolhido = idGrupoPaiEscolhido;
	}

	public GrupoAtividade getGrupoAtividade() {
		return grupoAtividade;
	}

	public void setGrupoAtividade(GrupoAtividade grupoAtividade) {
		this.grupoAtividade = grupoAtividade;
	}
}