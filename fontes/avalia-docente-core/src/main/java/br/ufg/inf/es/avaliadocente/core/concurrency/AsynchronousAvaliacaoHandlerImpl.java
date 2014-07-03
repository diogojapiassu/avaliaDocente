package br.ufg.inf.es.avaliadocente.core.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;

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

	public AsynchronousAvaliacaoHandlerImpl(List<Avaliacao> listaDeAvaliacoes) {
		super(listaDeAvaliacoes);
	}
	
	public AsynchronousAvaliacaoHandlerImpl() { }

	@Override
	public void processarListaAvaliacoes()
			throws AvaliacaoProcessamentoException {
		MethodProfiling p = new MethodProfiling();
		p.iniciarMedicao();
		LOG.info("Hora de inicio" + p.getDataInicio());

		ExecutorService es;
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

			int ctr=0;
			for (Future future : futures) {
				try {
					// blocking call, explicitly waiting for the response from a
					// specific task, not necessarily the first task that is
					// completed
					Object obj = future.get();
					LOG.info("** response worker " + ++ctr + " is in");
				} catch (InterruptedException | ExecutionException e) {
				}
			}
	 
			p.finalizarMedicao();
			LOG.info("Job took " + p.tempoExecucao() + " segundos");
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
//		threadPoolExecutorMonitor = new ThreadPoolExecutorMonitor((ThreadPoolExecutor) es, 1000);
		threadPoolExecutorMonitor.setExecutor((ThreadPoolExecutor) es);
		
		Thread t = new Thread(threadPoolExecutorMonitor);
		t.start();
	}
	

}
