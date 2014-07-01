package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

/**
 * Classe utilitária para monitorar, em um período regular de tempo,
 * o estado atual de um {@link ThreadPoolExecutor}.
 * 
 * <p>
 * Fonte de referência:
 * http://www.journaldev.com/1069/java-thread-pool-example-using-executors-and-threadpoolexecutor
 * </p>
 * 
 * @author Pankaj Kumar
 * @author Danilo Guimarães
 *
 */
public class ThreadPoolExecutorMonitor implements Runnable {

	private static final Logger LOG = Logger.getLogger(ThreadPoolExecutorMonitor.class);
	
	private ThreadPoolExecutor executor;
	private int delayMonitoramento;
	private boolean run = true;

	/**
	 * Contrói um monitor de {@link ThreadPoolExecutor}.
	 * 
	 * @param executor {@link ThreadPoolExecutor} que será monitorado.
	 * @param delayMonitoramento intervalo em um monitoramento e outro.
	 */
	public ThreadPoolExecutorMonitor(ThreadPoolExecutor executor, int delayMonitoramento) {
		this.executor = executor;
		this.delayMonitoramento = delayMonitoramento;
	}

	public void shutdown() {
		this.run = false;
	}

	@Override
	public void run() {
		while (run) {
			LOG.debug(String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
				this.executor.getPoolSize(),
				this.executor.getCorePoolSize(),
				this.executor.getActiveCount(),
				this.executor.getCompletedTaskCount(),
				this.executor.getTaskCount(),
				this.executor.isShutdown(),
				this.executor.isTerminated()));
			
			Sleeper.sleep(delayMonitoramento * 1000);
		}
	}

}
