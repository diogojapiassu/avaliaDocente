package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Representação de atividade de um grupo de atividades.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class Atividade extends AbstractEntity<Atividade> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	
	private String descricao;
	
	@ManyToOne
	private GrupoAtividade grupoAtividade;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<ItemAtividade> itemAtividades;
	
	@Column
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
