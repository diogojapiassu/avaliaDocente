package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representa uma avaliação de um determinado docente.
 * <br>É apenas uma abstração dos dados de entrada do sistema.
 * 
 * <p>
 * O processamento de uma determinada {@link Avaliacao} é transformado em um
 * {@link QuadroSumario}, que por sua vez é persistido.
 * </p>
 * 
 * 
 * @author Danilo Guimarães
 * 
 */
@Entity
public class Avaliacao extends AbstractEntity<Avaliacao> {
	
	private static final long serialVersionUID = -1120607385484817219L;

	@Column
	@JsonIgnore
	private Integer radoc;
		
	@JoinColumn(name = "resolucao_id")
	@JsonIgnore
	@ManyToOne
	private Resolucao resolucao;
	
	@JoinColumn(name = "docente_id")
	@ManyToOne(optional = false)
	private Docente docente;
	
	@Transient
	private List<GrupoAtividade> grupoAtividade;

	public Integer getRadoc() {
		return radoc;
	}

	public void setRadoc(Integer radoc) {
		this.radoc = radoc;
	}

	public Resolucao getResolucao() {
		return resolucao;
	}

	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}
	
	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	
	public List<GrupoAtividade> getGrupoAtividade() {
		return grupoAtividade;
	}

	public void setGrupoAtividade(List<GrupoAtividade> grupoAtividade) {
		this.grupoAtividade = grupoAtividade;
	}

}
