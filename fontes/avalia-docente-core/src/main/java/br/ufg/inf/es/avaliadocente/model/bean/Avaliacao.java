package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * 
 * @author Danilo Guimarães
 *
 */
@Entity
public class Avaliacao extends AbstractEntity<Avaliacao> {
	
	private static final long serialVersionUID = -1120607385484817219L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "docente_id")
	private Docente docente;
	
	@Transient
	private List<GrupoAtividade> grupoAtividade;

	
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
