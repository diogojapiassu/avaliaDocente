package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class ItemAtividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	private long id;
	
	@OneToOne
	private Atividade atividade;
	
	private int idItemPai;
	
	private BigDecimal valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the atividade
	 */
	public Atividade getAtividade() {
		return atividade;
	}

	/**
	 * @param atividade the atividade to set
	 */
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	/**
	 * @return the idItemPai
	 */
	public int getIdItemPai() {
		return idItemPai;
	}

	/**
	 * @param idItemPai the idItemPai to set
	 */
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
