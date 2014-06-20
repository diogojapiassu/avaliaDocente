package br.ufg.inf.es.avaliadocente.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Implementação que garante (vai nessa) a randomicidade e unicidade de
 * {@link Integer}s.
 * 
 * <p>
 * Utiliza um {@link Set} para ir gravando os números aleatórios gerados e
 * sempre que vai gerar um novo, checa se ele já não está adicionado no
 * {@link Set}.
 * <br>Caso esteja, ele gerará outro número até que a condição acima seja satisfeita.
 * </p>
 * 
 * @author Danilo Guimarães
 *
 */
public class RandomizerUniqueInteger {
	
	private static final Logger LOG = Logger.getLogger(RandomizerUniqueInteger.class);
	
	private Set<Integer> numbers;
	private Integer size;
	private Random r;
	
	/**
	 * 
	 * @param maxSize tamanho máximo que será gerado.
	 */
	public RandomizerUniqueInteger(Integer maxSize) {
		this.size = maxSize;
		numbers = new HashSet<>(this.size);
		numbers = Collections.synchronizedSet(numbers);
		r = new Random();
		Collections.synchronizedSet(numbers);
	}
	
	/**
	 * Gera um novo {@link Integer} randomicamente.
	 * 
	 * @return novo {@link Integer} randomicamente.
	 */
	public Integer getNewRandom() {
		// Gere um numero qualquer
		Integer random = nextInt();
		
		// Enquanto o numero estiver no set...
		while (numbers.contains(random)) {
			// Gera outro...
			random = nextInt();
		}
		//Achou um que nao está no set? entao adiciona.
		numbers.add(random);
		
		LOG.debug("New unique Integer random: " + random);
		return random;
	}
	
	/**
	 * Retorna um novo {@link Integer} qualquer.
	 * @return um novo {@link Integer} qualquer.
	 */
	private Integer nextInt() {
		return r.nextInt(size);
	}

}
