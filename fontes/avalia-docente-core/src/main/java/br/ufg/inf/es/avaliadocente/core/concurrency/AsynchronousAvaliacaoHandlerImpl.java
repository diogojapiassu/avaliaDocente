package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.core.AvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.exception.AvaliacaoProcessamentoException;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.util.MethodProfiling;

/**
 * Implementação de {@link AbstractAsynchronousAvaliacaoHandler}.
 * 
 * @author Luã Silvério
 * @author Danilo Guimarães
 *
 */
public class AsynchronousAvaliacaoHandlerImpl extends
		AbstractAsynchronousAvaliacaoHandler {
	
	private ExecutorService es;

	public AsynchronousAvaliacaoHandlerImpl(List<Avaliacao> listaDeAvaliacoes) {
		super(listaDeAvaliacoes);
	}
	
	public AsynchronousAvaliacaoHandlerImpl() { }

	@Override
	@SuppressWarnings("rawtypes")
	public void processarListaAvaliacoes()
			throws AvaliacaoProcessamentoException {
		MethodProfiling p = new MethodProfiling();
		p.iniciarMedicao();

		try {
			es = threadPoolExecutorFactory.getObject();
			
			iniciarThreadPoolExecutorMonitor(es);
			
			List<Future> futures = new ArrayList<Future>();

			for (int i = 0; i < listaDeAvaliacoes.size(); i++) {
				LOG.info("Iniciando processamento assíncrono da Avaliacao #" + i);

				//Obtem um novo bean do Spring
				AvaliacaoHandler avaliacaoHandler = CustomApplicationContext.getInstance().getContext().getBean(AvaliacaoHandler.class);
				avaliacaoHandler.setAvaliacao(this.listaDeAvaliacoes.get(i));
				
				futures.add(es.submit(avaliacaoHandler));
			}
			
			//Fechando o ThreadPool: ninguem mais entra...
			es.shutdown();
			
			try {
				esperarExecutorServiceTerminar();
			} catch (InterruptedException e) {
				LOG.error("Nao foi possivel aguardar a finalizacao do ThreadPool", e);
			}

			//Desligando o monitor...
			threadPoolExecutorMonitor.shutdown();
			
			p.finalizarMedicao();
			LOG.info("Processamento assincrono levou " + p.tempoExecucao() + " segundos");
		} catch (Exception e1) {
			LOG.error("Falha ao obter o ThreadPoolExecutor do Factory."
					+ "Lista de avaliacoes nao sera processada: " + listaDeAvaliacoes.toString(), e1);
			return;
		}
	}

	/**
	 * Inicia a {@link Thread} que irá monitorar o {@link ThreadPoolExecutor}.
	 * 
	 * @param es {@link ExecutorService} que será monitorado.
	 */
	private void iniciarThreadPoolExecutorMonitor(ExecutorService es) {
		threadPoolExecutorMonitor.setExecutor((ThreadPoolExecutor) es);
		
		Thread t = new Thread(threadPoolExecutorMonitor);
		t.start();
	}
	
	/**
	 * Espera por durante 10 minutos até que o {@link ExecutorService} termine.
	 * 
	 * @throws InterruptedException 
	 */
	private void esperarExecutorServiceTerminar() throws InterruptedException {
		/*
		 * TODO parametrizar esse tempo, de preferencia
		 * possibilitando a injecao via spring.
		 */
		int tempo = 10;
		LOG.info("Esperarei o ThreadPool por durante " + tempo + " minutos ate que seja encerrado");
		es.awaitTermination(tempo, TimeUnit.MINUTES);
	}
	

}
