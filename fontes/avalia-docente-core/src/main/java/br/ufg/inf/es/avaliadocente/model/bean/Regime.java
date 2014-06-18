package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Sinônimo da carga horária de um docente na UFG.
 * Normalmente pode ser de 20h ou 40 horas semanais.
 * 
 * @author Danilo Guimarães
 *
 */
@Entity
public class Regime extends AbstractEntity<Regime> {
	
	private static final long serialVersionUID = 3583026773986427783L;

	private String nome;
	
	@Column(name = "carga_horaria_semanal")
	private Long cargaHorariaSemanal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(Long cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}
	
	

}
