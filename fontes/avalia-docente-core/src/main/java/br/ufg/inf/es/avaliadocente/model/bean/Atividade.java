package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	
	@Column(length = 1000)
	private String descricao;
	
	@Column
	private Long indice;
	
	@ManyToOne
	@JoinColumn(name = "grupo_atividade_id")
	private GrupoAtividade grupoAtividade;
	
	@OneToOne
	@JoinColumn(name = "multiplicador_id")
	private Multiplicador multiplicador;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getIndice() {
		return indice;
	}

	public void setIndice(Long indice) {
		this.indice = indice;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString())
		.append(", Descricao: " + this.getDescricao())
		.append(", Indice : " + this.getIndice())
		.append(", Grupo Atividade: " + (this.getGrupoAtividade() != null ? this.getGrupoAtividade().getDescricao() : "n/a"));
		
		return sb.toString();
	}
}
