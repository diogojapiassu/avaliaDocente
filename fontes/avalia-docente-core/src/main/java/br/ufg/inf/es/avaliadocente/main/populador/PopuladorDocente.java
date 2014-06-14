package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.builder.DocenteBuilder;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;

/**
 * Classe simples para população de {@link Docente} no banco de dados.
 * 
 * @author Danilo Guimarães
 *
 */
public class PopuladorDocente implements IPopulador {
	
	@Autowired
	DocenteRepository repository;
	
	@Override
	public void popular() {
		Docente quinta = getMarceloQuinta();
		Docente fabio = getFabioNogueira();
		Docente ricardo = getRicardoRocha();
		
		repository.saveAndFlush(quinta);
		repository.saveAndFlush(fabio);
		repository.saveAndFlush(ricardo);
	}
	
	private Docente getFabioNogueira() {
		return new DocenteBuilder().nome("Fabio Nogueira")
				.departamento("Instituto de Informática")
				.matricula("123457")
				.build();
	}

	private Docente getMarceloQuinta() {
		return new DocenteBuilder().nome("Marcelo Quinta")
				.departamento("Instituto de Informática")
				.matricula("123456")
				.build();
	}
	
	private Docente getRicardoRocha() {
		return new DocenteBuilder().nome("Ricardo Rocha")
				.departamento("Instituto de Informática")
				.matricula("123898")
				.build();
	}
}
