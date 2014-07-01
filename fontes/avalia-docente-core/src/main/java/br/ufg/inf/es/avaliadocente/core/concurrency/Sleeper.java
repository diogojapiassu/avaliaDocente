package br.ufg.inf.es.avaliadocente.core.concurrency;

import org.apache.log4j.Logger;

/**
 * Facilitador para {@link Thread #sleep(long)}.
 * 
 * @author Danilo Guimarães
 */
public class Sleeper {
	
	private static final Logger LOG = Logger.getLogger(Sleeper.class);
	
	/**
	 * {@link Thread #sleep(long)}
	 * @param millis
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			LOG.error("", e);
			sleep(millis);
		}

	}

}
