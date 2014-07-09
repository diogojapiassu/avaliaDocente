package br.ufg.inf.es.avaliadocente.main.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.builder.AvaliacaoBuilder;
import br.ufg.inf.es.avaliadocente.repository.AtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.util.RandomizerUniqueInteger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Gera um JSON com uma lista de {@link Avaliacao}.
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliacoesJsonGenerator implements IGenerator {

	private static Logger LOG = Logger.getLogger(AvaliacoesJsonGenerator.class);
	
	private Integer anoRadoc = 2013;
	private List<Avaliacao> avaliacoes = new ArrayList<>();
	private List<GrupoAtividade> grupoAtividades = new ArrayList<>();
	private String diretorioJson;
	
	@Autowired
	DocenteRepository docenteRepository;
	@Autowired
	AtividadeRepository atividadeRepository;
	@Autowired
	GrupoAtividadeRepository grupoAtividadeRepository;

	/**
	 * 
	 * @param diretorioJson diretorio onde o JSON sera gravado.
	 */
	public AvaliacoesJsonGenerator(String diretorioJson) {
		this.diretorioJson = diretorioJson;
	}
	
	/**
	 * Gera um JSON com n avaliacoes, conforme a quantidade de docentes no banco
	 * de dados.
	 */
	@Override
	public void generate() {
		LOG.info("Iniciando geracao de avaliacoes");
		
		prepararListaAtividades();
		
		//Lista todos docentes
		List<Docente> docentes = docenteRepository.findAll();

		//Para cada docente...
		for (Docente docente : docentes) {
			//Gera uma avaliacao
			Avaliacao avaliacao = new AvaliacaoBuilder()
					.radoc(anoRadoc)
					.docente(docente)
					.grupoAtividade(grupoAtividades)
					.build();
			
			//Adiciona a avaliacao a lista
			avaliacoes.add(avaliacao);
		}
		
		LOG.info("Geracao de avaliacoes finalizada");
		
		gravarJson();
	}

	private void prepararListaAtividades() {
		grupoAtividades = grupoAtividadeRepository.findAll();
		
		for (GrupoAtividade grp : grupoAtividades) {
			List<Atividade> atividades = atividadeRepository.findByGrupoAtividade(grp);
			
			//Array com os valores inteiros randomizados
			Integer[] randoms = RandomizerUniqueInteger.generateRandomArray(atividades.size());
			
			int metadadeDasAtividades = atividades.size() / 2;
		
			for (int i = 0; i < metadadeDasAtividades; i++) {
				Atividade atv = atividades.get(randoms[i]-1);
				
				if (atv.getMultiplicador().isValorado()) {
					//LOG.info("Atividade " + atv.getDescricao() + " eh valorada");
					Long valor = new Long(new Random().nextInt(100));
					atv.setValor(valor);
					//LOG.info("Atribuindo valor " + valor);
				}
				
				grp.setAtividades(atividades);
			}
		}
	}
	

	/**
	 * Grava um JSON com as avaliacoes no disco rígido
	 */
	private void gravarJson() {
		ObjectMapper mapper  = new ObjectMapper();
		try {
			File jsonFile = new File(diretorioJson);
			if (!jsonFile.exists()) {
				jsonFile.mkdirs();
			}
				
			LOG.info("Iniciando a gravacao das avaliacoes");
			mapper.writeValue(jsonFile, avaliacoes);
			LOG.info("Gravacao das avaliacoes finalizada");
		} catch (JsonGenerationException e) {
			LOG.error("", e);
		} catch (JsonMappingException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		}
	}

}
