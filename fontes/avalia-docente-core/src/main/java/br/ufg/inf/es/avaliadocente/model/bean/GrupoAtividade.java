package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column
	@JsonIgnore
	private String descricao;
	
	@Column
	private Integer indice;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grupo_atividade_pai_id")
	@JsonIgnore
	private GrupoAtividade grupoAtividadePai;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Atividade> atividades;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotasGrupoAtividade> notasGrupoAtividades;
	
	@OneToOne
	@JoinColumn(name = "resolucao_id")
	@JsonIgnore
	private Resolucao resolucao;

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
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

	public Resolucao getResolucao() {
		return resolucao;
	}

	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}
	
}
