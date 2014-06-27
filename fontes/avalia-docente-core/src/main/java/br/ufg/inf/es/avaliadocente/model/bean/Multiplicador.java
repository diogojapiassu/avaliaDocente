package br.ufg.inf.es.avaliadocente.model.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Multiplicador a ser aplicado pela Atividade.
 * 
 * <p>
 * Quando {@link #valorado}, significa que não será considerado apenas o valor
 * de {@link #fatorMultiplicador}, mas sim será multiplicado com um valor
 * qualquer passado.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 * 
 */
@Entity
public class Multiplicador extends AbstractEntity<Multiplicador>{

	private static final long serialVersionUID = 7117819398892815646L;
	
	public Multiplicador() { }
	
	public Multiplicador(BigDecimal fatorMultiplicador) {
		this(fatorMultiplicador, null, null);
	}
	
	public Multiplicador(BigDecimal fatorMultiplicador, BigDecimal valorMaximo) {
		this(fatorMultiplicador, valorMaximo, null);
	}
	
	public Multiplicador(BigDecimal fatorMultiplicador, Boolean isValorado) {
		this(fatorMultiplicador, null, isValorado);
	}
	
	public Multiplicador(BigDecimal fatorMultiplicador, BigDecimal valorMaximo, Boolean isValorado) {
		setFatorMultiplicador(fatorMultiplicador);
		setValorMaximo(valorMaximo);
		setValorado(isValorado);
	}
	
	@Column
	private BigDecimal fatorMultiplicador;
	
	@Column
	private BigDecimal valorMaximo;
	
	@Column
	private Boolean valorado;
	
	public BigDecimal getFatorMultiplicador() {
		return fatorMultiplicador;
	}
	
	public void setFatorMultiplicador(BigDecimal fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Boolean isValorado() {
		return valorado;
	}

	public Boolean getValorado() {
		return valorado;
	}

	public void setValorado(Boolean valorado) {
		this.valorado = valorado;
	}

}
