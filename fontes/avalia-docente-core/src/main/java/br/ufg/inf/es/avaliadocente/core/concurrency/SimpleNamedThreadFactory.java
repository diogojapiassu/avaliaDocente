package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fábrica de {@link Thread}s customizada para nomear as instâncias.
 * 
 * @author Danilo Guimarães
 *
 */
public class SimpleNamedThreadFactory implements ThreadFactory {

	private static AtomicInteger threadNumber = new AtomicInteger(1);
	private String namePrefix;

	/**
	 * Constructor accepting the prefix of the threads that will be created by this {@link ThreadFactory}
	 * 
	 * @param namePrefix
	 *            Prefix for names of threads
	 */
	public SimpleNamedThreadFactory(String namePrefix) {
		setNamePrefix(namePrefix);
	}
	
	public Thread newThread(Runnable runnable, String namePrefix) {
		setNamePrefix(namePrefix);
		return newThread(runnable);
	}

	/**
	 * Returns a new thread using a name as specified by this factory {@inheritDoc}
	 */
	public Thread newThread(Runnable runnable) {
		if (runnable instanceof Thread) {
			return new Thread(runnable, namePrefix + " pool " +((Thread)runnable).getName()+" [thread-" + threadNumber.getAndIncrement()+"]");	
		}
		return new Thread(runnable, namePrefix + " pool [thread-" + threadNumber.getAndIncrement()+"]");
	}
	
	private void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

}
