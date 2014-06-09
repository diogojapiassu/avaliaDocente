package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;

import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class GrupoAtividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	private long id;
	private String descricao;
	
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
}
