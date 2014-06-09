package br.ufg.inf.es.avaliadocente.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Entity;

@Entity
@SuppressWarnings("deprecation")
public class QuadroSumario implements Serializable {

	private static final long serialVersionUID = 8454327804099597504L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Resolucao resolucao;
	
	@OneToOne
	private Docente docente;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<NotasGrupoAtividade> notasGrupoAtividade;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<NotasGrupoAtividade> getNotasGrupoAtividade() {
		return notasGrupoAtividade;
	}

	public void setNotasGrupoAtividade(List<NotasGrupoAtividade> notasGrupoAtividade) {
		this.notasGrupoAtividade = notasGrupoAtividade;
	}
}
