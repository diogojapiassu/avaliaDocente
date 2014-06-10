package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Docente que será submetido a avaliação.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class Docente extends AbstractEntity<Docente> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	private String matricula;
	
	private String nome;
	
	private String departamento;
	
	private String periodo;
	
	private String regime;
	
	@Column
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	@Column
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	@Column
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Column
	public String getRegime() {
		return regime;
	}
	
	public void setRegime(String regime) {
		this.regime = regime;
	}
}
