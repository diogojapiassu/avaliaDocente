package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Representação de um Grupo de Atividades
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class GrupoAtividade extends AbstractEntity<GrupoAtividade> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	private String descricao;
	
	@ManyToOne
	private GrupoAtividade grupoAtividadePai;
	
	@OneToMany(fetch = FetchType.LAZY) 
	private List<Atividade> atividades;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<NotasGrupoAtividade> notasGrupoAtividades;
	
	@Column
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column
	public GrupoAtividade getGrupoAtividadePai() {
		return grupoAtividadePai;
	}
	
	public void setGrupoAtividadePai(GrupoAtividade grupoAtividadePai) {
		this.grupoAtividadePai = grupoAtividadePai;
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
