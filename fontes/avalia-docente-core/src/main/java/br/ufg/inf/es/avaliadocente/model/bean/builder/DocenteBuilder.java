package br.ufg.inf.es.avaliadocente.model.bean.builder;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Docente;

/**
 * Classe builder da entidade {@link Docente} para facilitar a construção de
 * objetos durante o desenvolvimento e também durante a etapa dos testes unitários.
 * 
 * <p>
 * Mudanças na entidade {@link Docente} devem ser refletidas aqui.
 * </p>
 * 
 * @author Danilo Guimarães
 */
public class DocenteBuilder {
	
	private final Docente docente;
	
	public DocenteBuilder() {
		this.docente = new Docente();
	}
	
	public DocenteBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.docente, "id", id);
		return this;
	}
	
	public DocenteBuilder matricula(final String matricula) {
		ReflectionTestUtils.setField(this.docente, "matricula", matricula);
		return this;
	}
	
	public DocenteBuilder nome(final String nome) {
		ReflectionTestUtils.setField(this.docente, "nome", nome);
		return this;
	}
	
	public DocenteBuilder departamento(final String departamento) {
		ReflectionTestUtils.setField(this.docente, "departamento", departamento);
		return this;
	}
	
	public DocenteBuilder periodo(final String periodo) {
		ReflectionTestUtils.setField(this.docente, "periodo", periodo);
		return this;
	}
	
	public DocenteBuilder regime(final String regime) {
		ReflectionTestUtils.setField(this.docente, "matricula", regime);
		return this;
	}
	
	public Docente build() {
		return this.docente;
	}

}
