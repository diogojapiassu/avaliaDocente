package br.ufg.inf.es.avaliadocente.model.bean.builder;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Multiplicador;

public class MultiplicadorBuilder {
	
	private Multiplicador multiplicador;
	
	public MultiplicadorBuilder() {
		multiplicador = new Multiplicador();
	}
	
	public MultiplicadorBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.multiplicador, "id", id);
		return this;
	}

}
