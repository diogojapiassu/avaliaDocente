package br.ufg.inf.es.avaliadocente.model.bean.builder;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;

public class ResolucaoBuilder {
	
	private Resolucao resolucao;
	
	public ResolucaoBuilder() {
		resolucao = new Resolucao();
	}
	
	public ResolucaoBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.resolucao, "id", id);
		return this;
	}
	
	public ResolucaoBuilder descricao(final String descricao) {
		ReflectionTestUtils.setField(this.resolucao, "descricao", descricao);
		return this;
	}
	public Resolucao build() {
		return resolucao;
	}

}
