package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.model.bean.Multiplicador;
import br.ufg.inf.es.avaliadocente.repository.MultiplicadorRepository;

/**
 * 
 * @author diogo.japiassu
 *
 */
@ManagedBean
@SessionScoped
public class MultiplicadorMB {

	private List<Multiplicador> listaDeMultiplicadores;
	
	@Autowired
	public MultiplicadorRepository multiplicadorRepository;
	
	private Multiplicador multiplicador;
	
	public MultiplicadorMB() {
		multiplicadorRepository = CustomApplicationContext.getInstance().getContext().getBean(MultiplicadorRepository.class);
	}

	public List<Multiplicador> getListaDeMultiplicadores() {
		if (multiplicadorRepository != null) {
			listaDeMultiplicadores = multiplicadorRepository.findAllOrdenadoPorId();
		}else{
			listaDeMultiplicadores = new ArrayList<>(); 
		}
		
		return listaDeMultiplicadores;
	}
	
	public void setListaDeMultiplicadores(List<Multiplicador> listaDeMultiplicadores) {
		this.listaDeMultiplicadores = listaDeMultiplicadores;
	}
	
	public String adicionarMultiplicador(){
		multiplicadorRepository.save(multiplicador);
		
		return "multiplicadores";
	}
	
	public String prepararAlterarMultiplicador(Multiplicador multiplicador){
		this.multiplicador = multiplicador;
		
		return "gerenciaMultiplicador";
	}
	
	public String excluirMultiplicador(Multiplicador multiplicador){
		this.multiplicador = multiplicador;
		
		multiplicadorRepository.delete(multiplicador);
		
		return "multiplicadores";
	}
	
	public String preparaAdicionarMultiplicador(){
		multiplicador = new Multiplicador();
		
		return "gerenciaMultiplicador";
	}

	public Multiplicador getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(Multiplicador multiplicador) {
		this.multiplicador = multiplicador;
	}
	
}