package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Column
	private String matricula;
	
	@Column
	private String nome;
	
	@Column
	private String departamento;
	
	@Column
	private String periodo;
	
	@ManyToOne
	@JoinColumn(name = "regime_id")
	private Regime regime;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<QuadroSumario> quadroSumarios;
	

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public Regime getRegime() {
		return regime;
	}
	
	public void setRegime(Regime regime) {
		this.regime = regime;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString())
		.append(", Nome: " + this.getNome())
		.append(", Departamento : " + this.getDepartamento())
		.append(", Matricula: " + this.getMatricula())
		.append(", Regime: " + (this.getRegime() != null ? this.getRegime().getCargaHorariaSemanal() + " h/semana": "n/a") );
		
		return sb.toString();
	}
}
