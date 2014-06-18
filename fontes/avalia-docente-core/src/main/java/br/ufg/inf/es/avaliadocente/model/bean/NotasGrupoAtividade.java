package br.ufg.inf.es.avaliadocente.model.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Conjunto de notas de uma determinada avaliação específica.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class NotasGrupoAtividade extends AbstractEntity<NotasGrupoAtividade> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@ManyToOne
	@JoinColumn(name = "quadro_sumario_id")
	private QuadroSumario quadroSumario;
	
	@ManyToOne
	@JoinColumn(name = "grupo_atividade_id")
	private GrupoAtividade grupoAtividade;
	
	@Column(precision = 19, scale = 3)
	private BigDecimal valor;

	public QuadroSumario getQuadroSumario() {
		return quadroSumario;
	}

	public void setQuadroSumario(QuadroSumario quadroSumario) {
		this.quadroSumario = quadroSumario;
	}

	public GrupoAtividade getGrupoAtividade() {
		return grupoAtividade;
	}

	public void setGrupoAtividade(GrupoAtividade grupoAtividade) {
		this.grupoAtividade = grupoAtividade;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
