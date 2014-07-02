package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;
import br.ufg.inf.es.avaliadocente.model.support.annotation.Hiddenable;
import br.ufg.inf.es.avaliadocente.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representação de atividade de um grupo de atividades.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
@Updatable
@Hiddenable
public class Atividade extends AbstractEntity<Atividade> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Column(length = 1000)
	@JsonIgnore
	private String descricao;
	
	@Column
	private Long indice;
	
	/**
	 * Representa o valor de entrada na avaliação.
	 */
	@Transient
	private Long valor;
	
	@JoinColumn(name = "grupo_atividade_id")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private GrupoAtividade grupoAtividade;
	
	@JoinColumn(name = "multiplicador_id")
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
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
	
	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
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
