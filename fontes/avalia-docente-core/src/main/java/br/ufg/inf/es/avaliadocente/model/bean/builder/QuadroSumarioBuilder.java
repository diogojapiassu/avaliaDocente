package br.ufg.inf.es.avaliadocente.model.bean.builder;

import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.NotasGrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.QuadroSumario;

public class QuadroSumarioBuilder {

	private QuadroSumario quadroSumario;

	public QuadroSumarioBuilder() {
		this.quadroSumario = new QuadroSumario();	
	}
	
	public QuadroSumarioBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.quadroSumario, "id", id);
		return this;
	}
	
	public QuadroSumarioBuilder docente(final Docente docente) {
		ReflectionTestUtils.setField(this.quadroSumario, "docente", docente);
		return this;
	}
	
//	public QuadroSumarioBuilder notasGrupoAtividades(final List<NotasGrupoAtividade> notasGrupoAtividades) {
//		ReflectionTestUtils.setField(this.quadroSumario, "notasGrupoAtividades", notasGrupoAtividades);
//		return this;
//	}
	
	public QuadroSumario build() {
		return quadroSumario;
	}
}
