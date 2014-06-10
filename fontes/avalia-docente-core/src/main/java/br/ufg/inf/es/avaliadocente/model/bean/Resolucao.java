package br.ufg.inf.es.avaliadocente.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;


/**
 * Resolução que norteia a forma de avaliação dos docentes.
 * 
 * @author Douglas Japiassu
 * @author Danilo Guimarães
 *
 */
@Entity
public class Resolucao extends AbstractEntity<Resolucao> {

	private static final long serialVersionUID = 8454327804099597504L;
	
	private String descricao;
	
	private int numeroNotas;
	
	private int numeroAtividades;

	@Column
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="numero_notas")
	public int getNumeroNotas() {
		return numeroNotas;
	}

	public void setNumeroNotas(int numeroNotas) {
		this.numeroNotas = numeroNotas;
	}

	@Column(name="numero_atividades")
	public int getNumeroAtividades() {
		return numeroAtividades;
	}

	public void setNumeroAtividades(int numeroAtividades) {
		this.numeroAtividades = numeroAtividades;
	}

}
