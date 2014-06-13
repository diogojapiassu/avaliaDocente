package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne
	private Multiplicador multiplicador;
	
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

	public Multiplicador getMultiplicador() {
		return multiplicador;
	}
	
	public void setMultiplicador(Multiplicador multiplicador) {
		this.multiplicador = multiplicador;
	}
}
