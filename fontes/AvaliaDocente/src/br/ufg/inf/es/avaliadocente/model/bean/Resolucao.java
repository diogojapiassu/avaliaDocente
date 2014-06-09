package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class Resolucao implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String descricao;
	
	private int numeroNotas;
	
	private int numeroAtividades;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
