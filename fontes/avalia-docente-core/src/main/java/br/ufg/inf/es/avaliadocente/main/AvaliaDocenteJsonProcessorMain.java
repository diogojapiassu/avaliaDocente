package br.ufg.inf.es.avaliadocente.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.core.AvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.core.concurrency.AbstractAsynchronousAvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.model.bean.json.AvaliacaoJsonBinder;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

/**
 * Classe que inicia o processamento de uma avaliação.
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliaDocenteJsonProcessorMain {
	
private static final Logger LOG = Logger.getLogger(AvaliaDocenteJsonProcessorMain.class);
	
	@Autowired
	private static AvaliacaoHandler avaliacaoHandler;
	private static String diretorioJson = "/home/alunoinf/Downloads/avaliacao.json";
	private static String json = null;
	private static List<Avaliacao> avaliacoes = new ArrayList<>();
	
	@Autowired
	private static AbstractAsynchronousAvaliacaoHandler asyncAvaliacaoHandler;
	
	public static void main(String[] args) {
		LOG.info("Iniciando a aplicacao...");
		
		initBeans();
		
		parseJson();
		
		processarApenasUmaAvaliacao();
		
		processarVariasAvaliacoes();
	}
	
	/**
	 * Apenas inicializa os beans através do Spring.
	 */
	private static void initBeans() {
		//Inicializa o context do Spring...
		CustomApplicationContext.getInstance();
		
		//Estou pedindo o Spring pra construir esse objeto com base na classe (AvaliacaoHandler)...
		avaliacaoHandler = CustomApplicationContext.getInstance().getContext()
				.getBean(AvaliacaoHandler.class);
		
		//Estou pedindo o Spring pra construir esse objeto com base no id do bean...
		asyncAvaliacaoHandler = (AbstractAsynchronousAvaliacaoHandler) CustomApplicationContext
				.getInstance().getContext()
				.getBean("asyncAvaliacaoHandler");
	}
	
	/**
	 * Obtém o conteúdo do arquivo JSON e contrói uma lista de {@link Avaliacao}. 
	 */
	private static void parseJson() {
		json = getConteudoArquivoJson();
		
		avaliacoes = AvaliacaoJsonBinder.unbindAvaliacoes(json);
	}
	
	/**
	 * Processa apenas uma avaliação (para testes mais básicos e rápidos).
	 * Pega apenas a primeira {@link Avaliacao} obtida do JSON.
	 */
	private static void processarApenasUmaAvaliacao() {
		avaliacaoHandler.setAvaliacao(avaliacoes.get(0));
		
		new Thread(avaliacaoHandler).start();
	}
	
	/**
	 * Processa varias avaliações (para testes mais complexos).
	 */
	private static void processarVariasAvaliacoes() {
		//Injea mais uma propriedade no objeto ja instanciado...
		asyncAvaliacaoHandler.setListaDeAvaliacoes(avaliacoes);
		
		//Constroi uma Thread com o seu objeto ja instanciado...
		new Thread(asyncAvaliacaoHandler).start();
	}
	

	private static String getConteudoArquivoJson() {
		String json = null;
		try {
			json = FileUtils.getFileContent(new File(diretorioJson));
		} catch (IOException e) {
			LOG.error("Falha ao obter conteudo do arquivo json", e);
		}
		return json;
	}

}
