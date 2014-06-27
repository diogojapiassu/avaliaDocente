package br.ufg.inf.es.avaliadocente.model.bean.json;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DocenteJsonBinder {
	
	private static final Logger LOG = Logger.getLogger(DocenteJsonBinder.class);
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
	
		String json = FileUtils.getContent("json/docentes-list.json", "UTF-8");
		
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createParser(json);
		// advance stream to START_ARRAY first:
		jp.nextToken();
		// and then each time, advance to opening START_OBJECT
		ObjectMapper mapper = new ObjectMapper();
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			Docente doc = mapper.readValue(jp, Docente.class);
		 	LOG.info(doc.toString());
		}
		
		long tfinal = System.currentTimeMillis() - start;
		LOG.info("Tempo de execucao: " + tfinal);
	}

}
