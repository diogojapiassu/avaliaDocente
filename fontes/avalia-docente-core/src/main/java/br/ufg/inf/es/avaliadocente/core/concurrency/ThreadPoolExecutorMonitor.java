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
	
	/**
	 * {@link ThreadPoolExecutor} alvo (que será monitorado).
	 */
	private ThreadPoolExecutor executor;
	/**
	 * Intervalo entre um monitoramento e outro (em segundos). <br>
	 * Padrão: 10 segundos.
	 */
	private int delayMonitoramento = 10;
	private boolean run = true;

	/**
	 * Contrói um monitor de {@link ThreadPoolExecutor}.
	 * 
	 * @param executor {@link ThreadPoolExecutor} que será monitorado.
	 * @param delayMonitoramento intervalo entre um monitoramento e outro (em segundos).
	 */
	public ThreadPoolExecutorMonitor(ThreadPoolExecutor executor, int delayMonitoramento) {
		this.executor = executor;
		this.delayMonitoramento = delayMonitoramento;
	}
	
	/**
	 * Contrói um monitor de {@link ThreadPoolExecutor}.
	 * 
	 * @param delayMonitoramento intervalo entre um monitoramento e outro (em segundos).
	 */
	public ThreadPoolExecutorMonitor(int delayMonitoramento) {
		this(null, delayMonitoramento);
	}
	
	public ThreadPoolExecutorMonitor() { }

	public void shutdown() {
		this.run = false;
	}

	@Override
	public void run() {
		while (run) {
			LOG.debug(String.format("[thread-pool-monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
				this.executor.getPoolSize(),
				this.executor.getCorePoolSize(),
				this.executor.getActiveCount(),
				this.executor.getCompletedTaskCount(),
				this.executor.getTaskCount(),
				this.executor.isShutdown(),
				this.executor.isTerminated()));
			
			Sleeper.sleep(delayMonitoramento * 1000);
			
			if (this.executor.isTerminated()) {
				LOG.info(String.format("Encerrando o monitor %s para o ThreadPoolExecutor %s", Thread.currentThread().getName(), this.executor.toString()));
				shutdown();
			}
		}
	}

	public void setExecutor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public void setDelayMonitoramento(int delayMonitoramento) {
		this.delayMonitoramento = delayMonitoramento;
	}

}
