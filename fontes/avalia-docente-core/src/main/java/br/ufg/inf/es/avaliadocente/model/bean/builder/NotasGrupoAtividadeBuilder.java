package br.ufg.inf.es.avaliadocente.model.bean.builder;

import java.math.BigDecimal;

import org.springframework.test.util.ReflectionTestUtils;

import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.NotasGrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.QuadroSumario;

public class NotasGrupoAtividadeBuilder {
	
	private NotasGrupoAtividade notasGrupoAtividade;
	
	public NotasGrupoAtividadeBuilder() {
		this.notasGrupoAtividade = new NotasGrupoAtividade();
	}
	
	public NotasGrupoAtividadeBuilder quadroSumario(final QuadroSumario quadroSumario) {
		ReflectionTestUtils.setField(this.notasGrupoAtividade, "quadroSumario", quadroSumario);
		return this;
	}
	
	public NotasGrupoAtividadeBuilder grupoAtividade(final GrupoAtividade grupoAtividade) {
		ReflectionTestUtils.setField(this.notasGrupoAtividade, "grupoAtividade", grupoAtividade);
		return this;
	}
	
	public NotasGrupoAtividadeBuilder valor(final BigDecimal valor) {
		ReflectionTestUtils.setField(this.notasGrupoAtividade, "valor", valor);
		return this;
	}

	public NotasGrupoAtividade build() {
		return notasGrupoAtividade;
	}

}
