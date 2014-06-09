package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class ItemAtividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Atividade atividade;
	
	private int idItemPai;
	
	private BigDecimal valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
