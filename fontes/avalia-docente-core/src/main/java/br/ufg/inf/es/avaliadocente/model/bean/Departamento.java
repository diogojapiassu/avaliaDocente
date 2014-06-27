package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

@Entity
public class Departamento extends AbstractEntity<Departamento> {
	
	private static final long serialVersionUID = -2797751568354993428L;

	@Column
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
