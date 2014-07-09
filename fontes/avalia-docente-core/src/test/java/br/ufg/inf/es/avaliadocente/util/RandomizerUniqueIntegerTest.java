package br.ufg.inf.es.avaliadocente.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Testa a {@link RandomizerUniqueInteger}.
 * 
 * @author Danilo Guimarães
 *
 */
public class RandomizerUniqueIntegerTest {
	Logger LOG = Logger.getLogger(RandomizerUniqueIntegerTest.class);
	
	// Subject under test...
	private RandomizerUniqueInteger sut;
	
	@Test
	public void garanteUnicidadeEm100() {
		Integer tamanho = 100;
		assertEquals(tamanho, new Integer(garanteUnicidade(tamanho).size()));
	}
	
	@Test
	public void garanteUnicidadeEm1000() {
		Integer tamanho = 1000;
		assertEquals(tamanho, new Integer(garanteUnicidade(tamanho).size()));
	}
	
	@Test
	public void garanteUnicidadeEm10000() {
		Integer tamanho = 10000;
		assertEquals(tamanho, new Integer(garanteUnicidade(tamanho).size()));
	}
	
	/**
	 * <ol>
	 * <li>Cria um {@link Set} de tamanho 'tamanho'.</li>
	 * <li>Instancia um {@link RandomizerUniqueInteger} de tamanho 'tamanho'</li>
	 * <li>Gera um {@link Integer} e o adiciona no {@link Set} 'tamanho' vezes</li>
	 * <li>Retorna o {@link Set}</li>
	 * </ol>
	 * 
	 * @param tamanho tamanho maximo que será passado ao {@link RandomizerUniqueInteger}.
	 * @return um {@link Set} de tamanho 'tamanho'.
	 */
	private Set<Integer> garanteUnicidade(Integer tamanho) {
		Set<Integer> numeros = new HashSet<>(tamanho);
		
		sut = new RandomizerUniqueInteger(tamanho);
		
		for (int i = 0; i < tamanho; i++) {
			Integer temp = sut.getNewRandom();
			numeros.add(temp);
		}

		return numeros;
		
	}
	
	@Test
	public void garanteUnicidade() {
		int size = 10;
		Integer[] a = sut.generateRandomArray(size);
		
		Set<Integer> conjunto = new HashSet<>();
		
		for (Integer i : a) {
			conjunto.add(i);
		}
		
		assertEquals(size, conjunto.size());
	}
}
