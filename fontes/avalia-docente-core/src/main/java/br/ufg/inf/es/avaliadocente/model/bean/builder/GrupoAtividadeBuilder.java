package br.ufg.inf.es.avaliadocente.model.bean.builder;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;

public class GrupoAtividadeBuilder {
	
	private GrupoAtividade grupoAtividade;
	
	public GrupoAtividadeBuilder() {
		grupoAtividade = new GrupoAtividade();
	}
	
	public GrupoAtividadeBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.grupoAtividade, "id", id);
		return this;
	}
	
	public GrupoAtividadeBuilder descricao(final String descricao) {
		ReflectionTestUtils.setField(this.grupoAtividade, "descricao", descricao);
		return this;
	}
	
	public GrupoAtividadeBuilder indice(final Integer indice) {
		ReflectionTestUtils.setField(this.grupoAtividade, "indice", indice);
		return this;
	}
	
	public GrupoAtividadeBuilder grupoAtividadePai(final GrupoAtividade grupoAtividadePai) {
		ReflectionTestUtils.setField(this.grupoAtividade, "grupoAtividadePai", grupoAtividadePai);
		return this;
	}
	
	public GrupoAtividadeBuilder resolucao(final Resolucao resolucao) {
		ReflectionTestUtils.setField(this.grupoAtividade, "resolucao", resolucao);
		return this;
	}
	
	public GrupoAtividade build() {
		return this.grupoAtividade;
	}
	
	
	
	

}
