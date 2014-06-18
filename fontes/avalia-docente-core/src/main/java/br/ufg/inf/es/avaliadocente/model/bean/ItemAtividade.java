package br.ufg.inf.es.avaliadocente.model.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class ItemAtividade extends AbstractEntity<ItemAtividade> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@ManyToOne
	private Atividade atividade;
	
	@Column
	private Integer idItemPai;
	
	@Column
	private BigDecimal valor;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public int getIdItemPai() {
		return idItemPai;
	}

	public void setIdItemPai(int idItemPai) {
		this.idItemPai = idItemPai;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
