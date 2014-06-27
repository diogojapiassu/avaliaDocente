package br.ufg.inf.es.avaliadocente.model.bean.builder;

import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;

public class AvaliacaoBuilder {
	
	private Avaliacao avaliacao;
	
	public AvaliacaoBuilder() {
		avaliacao = new Avaliacao();
	}
	
	public AvaliacaoBuilder id(final Long id) {
		ReflectionTestUtils.setField(this.avaliacao, "id", id);
		return this;
	}
	
	public AvaliacaoBuilder radoc(final Integer radoc) {
		ReflectionTestUtils.setField(this.avaliacao, "radoc", radoc);
		return this;
	}
	
	public AvaliacaoBuilder resolucao(final Resolucao resolucao) {
		ReflectionTestUtils.setField(this.avaliacao, "resolucao", resolucao);
		return this;
	}
	
	public AvaliacaoBuilder docente(final Docente docente) {
		ReflectionTestUtils.setField(this.avaliacao, "docente", docente);
		return this;
	}
	
	public AvaliacaoBuilder grupoAtividade(final List<GrupoAtividade> grupoAtividade) {
		ReflectionTestUtils.setField(this.avaliacao, "grupoAtividade", grupoAtividade);
		return this;
	}
	
	public Avaliacao build() {
		return avaliacao;
	}

}
