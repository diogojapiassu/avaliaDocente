package br.ufg.inf.es.avaliadocente.model.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Quadro sumário contendo as informações de avaliação de um docente.
 *
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class QuadroSumario extends AbstractEntity<QuadroSumario> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@ManyToOne
	@JoinColumn(name = "resolucao_id")
	private Resolucao resolucao;
	
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private Docente docente;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<NotasGrupoAtividade> notasGrupoAtividades;
	
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

	public List<NotasGrupoAtividade> getNotasGrupoAtividades() {
		return notasGrupoAtividades;
	}

	public void setNotasGrupoAtividades(List<NotasGrupoAtividade> notasGrupoAtividades) {
		this.notasGrupoAtividades = notasGrupoAtividades;
	}
}
