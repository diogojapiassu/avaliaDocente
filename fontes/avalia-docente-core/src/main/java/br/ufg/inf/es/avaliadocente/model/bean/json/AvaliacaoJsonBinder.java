package br.ufg.inf.es.avaliadocente.model.bean.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe responsável por fazer o bind/unbind de {@link Avaliacao} para JSON e
 * vice-versa.
 * 
 * @author Danilo Guimarães
 * 
 */
public class AvaliacaoJsonBinder {
	
	private static final Logger LOG = Logger.getLogger(AvaliacaoJsonBinder.class);
	
	private static String diretorioJson = "C:/avaliacoes.json";
	
	private static ObjectMapper mapper;
	
	public AvaliacaoJsonBinder() {	}

	public static void main(String[] args) {
		String json = getConteudoArquivoJson();
		List<Avaliacao> avaliacoes = unbindAvaliacoes(json);
		
		LOG.info("Tamanho da lista: "+ avaliacoes.size());
		
//		for (Avaliacao aval : avaliacoes) {
//			LOG.info(aval.toString());
//		}
	}
	
	public static List<Avaliacao> unbindAvaliacoes(String json) {
		List<Avaliacao> avaliacoes = new ArrayList<>();
		
		mapper = new ObjectMapper();
		try {
			avaliacoes = mapper.readValue(json, new TypeReference<List<Avaliacao>>(){});
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		
		return avaliacoes;
	}
	
	public static String bindAvaliacoes(List<Avaliacao> avaliacoes) {
		String json = null;
		
		mapper = new ObjectMapper();
		try {
			json  = mapper.writeValueAsString(avaliacoes);
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		
		return json;
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
