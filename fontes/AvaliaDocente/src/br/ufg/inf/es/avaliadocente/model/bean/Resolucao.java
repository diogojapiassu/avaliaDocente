package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;

import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class Resolucao implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	private long id;
	
	private int numeroNotas;
	
	private int numeroAtividades;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumeroNotas() {
		return numeroNotas;
	}

	public void setNumeroNotas(int numeroNotas) {
		this.numeroNotas = numeroNotas;
	}

	public int getNumeroAtividades() {
		return numeroAtividades;
	}

	public void setNumeroAtividades(int numeroAtividades) {
		this.numeroAtividades = numeroAtividades;
	}

}
