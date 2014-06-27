package br.ufg.inf.es.avaliadocente.main;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.core.AvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.core.ThreadListaDeAvaliacoes;
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
	
	public static void main(String[] args) {
		LOG.info("Iniciando a aplicacao...");
		
		CustomApplicationContext.getInstance();
		
		String json = getConteudoArquivoJson();
		
		//avaliacaoHandler.setAvaliacao(AvaliacaoJsonBinder.unbindAvaliacoes(json).get(0));
		
		ThreadListaDeAvaliacoes testaProcessamentoEmParalelo = 
				new ThreadListaDeAvaliacoes(AvaliacaoJsonBinder.unbindAvaliacoes(json));
		
		new Thread(testaProcessamentoEmParalelo).start();
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
