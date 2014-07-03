package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import br.ufg.inf.es.avaliadocente.exception.AvaliacaoProcessamentoException;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;

/**
 * 
 * @author Danilo Guimarães
 *
 */
public abstract class AbstractAsynchronousAvaliacaoHandler implements Runnable {
	
	protected static final Logger LOG = Logger.getLogger(AbstractAsynchronousAvaliacaoHandler.class);
	
	protected List<Avaliacao> listaDeAvaliacoes;
	
	@Autowired
	protected ThreadPoolExecutorFactoryBean threadPoolExecutorFactory;
	
	@Autowired
	protected ThreadPoolExecutorMonitor threadPoolExecutorMonitor;

	public AbstractAsynchronousAvaliacaoHandler(List<Avaliacao> listaDeAvaliacoes) {
		this.listaDeAvaliacoes = listaDeAvaliacoes;
	}
	
	public void setListaDeAvaliacoes(List<Avaliacao> listaDeAvaliacoes) {
		this.listaDeAvaliacoes = listaDeAvaliacoes;
	}
	
	public void setThreadPoolExecutorMonitor(ThreadPoolExecutorMonitor threadPoolExecutorMonitor) {
		this.threadPoolExecutorMonitor = threadPoolExecutorMonitor;
	}
	
	public AbstractAsynchronousAvaliacaoHandler() { }
	
	/**
	 * Método que realiza o processamento de uma lista de {@link Avaliacao}.
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
