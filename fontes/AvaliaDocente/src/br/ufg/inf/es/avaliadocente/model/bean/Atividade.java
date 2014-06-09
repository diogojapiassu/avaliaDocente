package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class Atividade implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String descricao;
	
	@ManyToOne
	private GrupoAtividade grupoAtividade;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<ItemAtividade> itemAtividades;
	
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

	public GrupoAtividade getGrupoAtividade() {
		return grupoAtividade;
	}

	public void setGrupoAtividade(GrupoAtividade grupoAtividade) {
		this.grupoAtividade = grupoAtividade;
	}

	public List<ItemAtividade> getItemAtividades() {
		return itemAtividades;
	}

	public void setItemAtividades(List<ItemAtividade> itemAtividades) {
		this.itemAtividades = itemAtividades;
	}
}
