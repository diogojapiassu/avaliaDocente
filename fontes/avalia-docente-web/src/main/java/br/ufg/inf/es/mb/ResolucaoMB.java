package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;
import br.ufg.inf.es.avaliadocente.repository.ResolucaoRepository;

/**
 * 
 * @author diogo.japiassu
 *
 */
@ManagedBean
@SessionScoped
public class ResolucaoMB {

	private List<Resolucao> listaDeResolucao;
	
	@Autowired
	public ResolucaoRepository resolucaoRepository;
	
	private Resolucao resolucao;
	
	public ResolucaoMB() {
		resolucaoRepository = CustomApplicationContext.getInstance().getContext().getBean(ResolucaoRepository.class);
	}

	public List<Resolucao> getListaDeResolucao() {
		if (resolucaoRepository != null) {
			listaDeResolucao = resolucaoRepository.findAllOrdenadoPorId();
		}else{
			listaDeResolucao = new ArrayList<>(); 
		}
		
		return listaDeResolucao;
	}
	
	public void setListaDeResolucao(List<Resolucao> listaDeResolucao) {
		this.listaDeResolucao = listaDeResolucao;
	}
	
	public String adicionarResolucao(){
		resolucaoRepository.save(resolucao);
		
		/*if(resolucao.getId() == null){
			resolucaoRepository. saveAndFlush(resolucao);
		}else{
			resolucaoRepository.atualizarResolucao(
					resolucao.getDescricao(), 
					resolucao.getNumeroNotas(), 
					resolucao.getId());
		}*/
		
		return "resolucoes";
	}
	
	public String prepararAlterarResolucao(Resolucao resolucaoCarregada){
		this.resolucao = resolucaoCarregada;
		
		return "gerenciaResolucao";
	}
	
	public String excluirResolucao(Resolucao resolucaoCarregada){
		this.resolucao = resolucaoCarregada;
		
		resolucaoRepository.delete(resolucao);
		
		return "resolucoes";
	}
	
	public String preparaAdicionarResolucao(){
		resolucao = new Resolucao();
		
		return "gerenciaResolucao";
	}

	public Resolucao getResolucao() {
		return resolucao;
	}

	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}
	
}