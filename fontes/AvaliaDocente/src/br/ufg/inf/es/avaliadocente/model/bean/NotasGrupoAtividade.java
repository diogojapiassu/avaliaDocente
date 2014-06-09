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
public class NotasGrupoAtividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private QuadroSumario quadroSumario;
	
	@ManyToOne
	private GrupoAtividade grupoAtividade;
	
	private BigDecimal valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
