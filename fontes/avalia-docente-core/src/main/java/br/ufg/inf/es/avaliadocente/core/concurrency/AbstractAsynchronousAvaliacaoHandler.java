package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import br.ufg.inf.es.avaliadocente.exception.AvaliacaoProcessamentoException;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;

/**
 * Processamento assíncrono de uma {@link List} de {@link Avaliacao}.
 * 
 * <p>
 * As sub-implementações devem garantir a implementação do processamento em si,
 * enquanto que essa classe abstrata garante o básico e os objetos de criação do
 * {@link ThreadPoolExecutor}.
 * </p>
 * 
 * @author Danilo Guimarães
 *
 */
public abstract class AbstractAsynchronousAvaliacaoHandler implements Runnable {
	
	protected static final Logger LOG = Logger.getLogger(AbstractAsynchronousAvaliacaoHandler.class);
	
	/**
	 * Lista de {@link Avaliacao} que será processada de forma assíncrona.
	 */
	protected List<Avaliacao> listaDeAvaliacoes;
	
	/**
	 * Fábrica de {@link ThreadPoolExecutor}.
	 */
	@Autowired
	protected ThreadPoolExecutorFactoryBean threadPoolExecutorFactory;
	
	/**
	 * Monitor de {@link ThreadPoolExecutor}
	 */
	@Autowired
	protected ThreadPoolExecutorMonitor threadPoolExecutorMonitor;

	/**
	 * Construtor padrão
	 */
	public AbstractAsynchronousAvaliacaoHandler() { }
	
	/**
	 * Prepara uma instância pronta para processar uma lista de
	 * {@link Avaliacao} de forma assíncrona.
	 * 
	 * @param listaDeAvaliacoes lista de {@link Avaliacao} que será processada de forma assíncrona.
	 */
	public AbstractAsynchronousAvaliacaoHandler(List<Avaliacao> listaDeAvaliacoes) {
		this.listaDeAvaliacoes = listaDeAvaliacoes;
	}
	
	public void setListaDeAvaliacoes(List<Avaliacao> listaDeAvaliacoes) {
		this.listaDeAvaliacoes = listaDeAvaliacoes;
	}
	
	public void setThreadPoolExecutorFactory(ThreadPoolExecutorFactoryBean threadPoolExecutorFactory) {
		this.threadPoolExecutorFactory = threadPoolExecutorFactory;
	}
	
	public void setThreadPoolExecutorMonitor(ThreadPoolExecutorMonitor threadPoolExecutorMonitor) {
		this.threadPoolExecutorMonitor = threadPoolExecutorMonitor;
	}

	/**
	 * Método abstrato que realiza o processamento de uma lista de {@link Avaliacao}.
	 * 
	 * @throws AvaliacaoProcessamentoException
	 */
	public abstract void processarListaAvaliacoes() throws AvaliacaoProcessamentoException;
	
	@Override
	public void run() {
		try {
			processarListaAvaliacoes();
		} catch (AvaliacaoProcessamentoException e) {
			LOG.error("Falha ao processar a lista de avaliacao.", e);
		}
	}
}
