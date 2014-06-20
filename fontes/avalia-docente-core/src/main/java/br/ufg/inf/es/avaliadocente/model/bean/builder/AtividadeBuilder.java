package br.ufg.inf.es.avaliadocente.model.bean.builder;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Multiplicador;

public class AtividadeBuilder {

	private Atividade atividade;
	
	public AtividadeBuilder() {
		atividade = new Atividade();
	}
	
	public AtividadeBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.atividade, "id", id);
		return this;
	}
	
	public AtividadeBuilder indice(final Long indice) {
		ReflectionTestUtils.setField(this.atividade, "indice", indice);
		return this;
	}
	
	public AtividadeBuilder descricao(final String descricao) {
		ReflectionTestUtils.setField(this.atividade, "descricao", descricao);
		return this;
	}
	
	public AtividadeBuilder grupoAtividade(final GrupoAtividade grupoAtividade) {
		ReflectionTestUtils.setField(this.atividade, "grupoAtividade", grupoAtividade);
		return this;
	}
	
	public AtividadeBuilder multiplicador(final Multiplicador multiplicador) {
		ReflectionTestUtils.setField(this.atividade, "multiplicador", multiplicador);
		return this;
	}
	
	public Atividade build() {
		return atividade;
	}
}
