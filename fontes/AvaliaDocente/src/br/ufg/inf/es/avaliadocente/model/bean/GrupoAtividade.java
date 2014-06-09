package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class GrupoAtividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	private long id;
	
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY) 
	private List<Atividade> atividades;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<NotasGrupoAtividade> notasGrupoAtividades;
	
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

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<NotasGrupoAtividade> getNotasGrupoAtividades() {
		return notasGrupoAtividades;
	}

	public void setNotasGrupoAtividades(List<NotasGrupoAtividade> notasGrupoAtividades) {
		this.notasGrupoAtividades = notasGrupoAtividades;
	}
}
