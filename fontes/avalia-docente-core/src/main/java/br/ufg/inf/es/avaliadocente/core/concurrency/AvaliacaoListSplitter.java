package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.List;

import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;

import com.google.common.collect.Lists;

/**
 * Separa uma {@link List} de {@link Avaliacao} em várias sub-listas.
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliacaoListSplitter {
	
	private List<Avaliacao> avaliacoes;
	
	/**
	 * quantos itens terá cada sub-lista
	 * Tamanho padrão: 1000.
	 */
	private Integer tamanhoSubListas = 1000;
	
	/**
	 * Assume o tamanho padrão de cada sub-lista.
	 * @param avaliacoes
	 */
	public AvaliacaoListSplitter(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	/**
	 * Constrói um objeto de separação de {@link List} de {@link Avaliacao}
	 * @param avaliacoes Lista contendo n {@link Avaliacao}.
	 * @param tamanhoSubListas quantos itens terá cada sub-lista.
	 */
	public AvaliacaoListSplitter(List<Avaliacao> avaliacoes, Integer tamanhoSubListas) {
		this.avaliacoes = avaliacoes;
		this.tamanhoSubListas = tamanhoSubListas;
	}
	
	/**
	 * Realiza a separação (split) de fato.
	 * 
	 * @return Lista de sub-listas de {@link Avaliacao}.
	 */
	public List<List<Avaliacao>> split() {
		synchronized (this) {
			return Lists.partition(avaliacoes, tamanhoSubListas);
		}
	}

}
