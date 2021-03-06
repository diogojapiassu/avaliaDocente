package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;
import br.ufg.inf.es.avaliadocente.model.support.annotation.Hiddenable;
import br.ufg.inf.es.avaliadocente.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Resolução que norteia a forma de avaliação dos docentes.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
@Updatable
@Hiddenable
public class Resolucao extends AbstractEntity<Resolucao> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Column
	private String descricao;

	@Column
	@JsonIgnore
	private Integer numeroNotas;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY) 
	private List<QuadroSumario> quadroSumarios;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="numero_notas")
	public Integer getNumeroNotas() {
		return numeroNotas;
	}

	public void setNumeroNotas(Integer numeroNotas) {
		this.numeroNotas = numeroNotas;
	}
	
	public List<QuadroSumario> getQuadroSumarios() {
		return quadroSumarios;
	}
	
	public void setQuadroSumarios(List<QuadroSumario> quadroSumarios) {
		this.quadroSumarios = quadroSumarios;
	}

}
