package br.ufg.inf.es.avaliadocente.model.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
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
	
//	@OneToMany(fetch = FetchType.LAZY)
//	private List<NotasGrupoAtividade> notasGrupoAtividades;
	
	/**
	 * Valor total do somatório de todas as {@link NotasGrupoAtividade}.
	 */
	@Column(precision = 19, scale = 3)
	private BigDecimal valorTotal = new BigDecimal(0);
	
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

//	public List<NotasGrupoAtividade> getNotasGrupoAtividades() {
//		return notasGrupoAtividades;
//	}
//
//	public void setNotasGrupoAtividades(List<NotasGrupoAtividade> notasGrupoAtividades) {
//		this.notasGrupoAtividades = notasGrupoAtividades;
//	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	/**
	 * Adiciona o valor passado ao valor atual.
	 * <br>É o mesmo que <pre>this.valorTotal = valorTotal + valueToBeAdded;</pre>
	 * 
	 * @param valueToBeAdded valor a ser adicionado
	 */
	public void addValor(BigDecimal valueToBeAdded) {
		this.valorTotal = valorTotal.add(valueToBeAdded);
	}
}
