package br.ufg.inf.es.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.main.populador.PopuladorBancoDeDados;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.builder.DocenteBuilder;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;

@ManagedBean
@Configurable
public class TesteMB {

	private String value = "Teste projeto com Primefaces";
	
	private List<Docente> listaDeDocentes;
	
	@Autowired
	public DocenteRepository docenteRepository;
	
	private Docente docente;
	
	public TesteMB() {
		docenteRepository = CustomApplicationContext.getInstance().getContext().getBean(DocenteRepository.class);
		System.out.println("passa aqui primeiro?");
	}

	public String getValue() {
		docente = new Docente();
		docente.setNome("teste");
		this.value = docente.getNome();
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public List<Docente> getListaDeDocentes() {
		if (docenteRepository != null) {
			return docenteRepository.findAll();
		}
		
		List<Docente> lista = new ArrayList<Docente>();
		Docente docente1 = new DocenteBuilder().id(1L).matricula("123456").build();
		Docente docente2 = new DocenteBuilder().id(1L).matricula("654321").build();
		lista.add(docente1);
		lista.add(docente2);
		
		return lista;
	}
	
	public void setListaDeDocentes(List<Docente> listaDeDocentes) {
		this.listaDeDocentes = listaDeDocentes;
	}
}