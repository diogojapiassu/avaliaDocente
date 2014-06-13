package br.ufg.inf.es.avaliadocente.model.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;
/**
 * Multiplicador a ser aplicado pela Atividade.
 * 
 * @author Douglas Japiassu
 *
 */
@Entity
public class Multiplicador extends AbstractEntity<Multiplicador>{

	private static final long serialVersionUID = 7117819398892815646L;
	
	private BigDecimal fator_multiplicador;
	
	@OneToOne
	private Atividade atividade;
	
	@Column
	public BigDecimal getFator_multiplicador() {
		return fator_multiplicador;
	}
	
	public void setFator_multiplicador(BigDecimal fator_multiplicador) {
		this.fator_multiplicador = fator_multiplicador;
	}

}
