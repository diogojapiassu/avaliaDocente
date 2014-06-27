package br.ufg.inf.es.avaliadocente.main.populador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Departamento;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.builder.DocenteBuilder;
import br.ufg.inf.es.avaliadocente.repository.DepartamentoRepository;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;
import br.ufg.inf.es.avaliadocente.util.FileUtils;
import br.ufg.inf.es.avaliadocente.util.RandomizerUniqueInteger;

/**
 * Classe simples para população de {@link Docente} no banco de dados.
 * 
 * <p>
 * Utiliza um arquivo de nomes e outro de sobrenomes para obter 10.000 docentes distintos.
 * É utilizado também as 38 unidades acadêmicas da UFG para dar maior realismo a base.
 * </p> 
 * 
 * @author Danilo Guimarães
 *
 */
public class PopuladorDocente implements IPopulador {
	
	private static final Logger LOG = Logger.getLogger(PopuladorDocente.class);

	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	DepartamentoRepository departamentoRepository;
	
	Set<Integer> numerosAleatorios = new HashSet<>();
	
	List<Docente> docentes = new ArrayList<>();
	
	private final int QUANTIDADE_DOCENTES = 10000;
	
	@Override
	public void popular() {
		
		String arquivoNomes = getConteudoArquivoNomes();
		String arquivoSobrenomes = getConteudoArquivoSobrenomes();
			
		String[] nomes = FileUtils.splitByNewLine(arquivoNomes);
		String[] sobrenomes = FileUtils.splitByNewLine(arquivoSobrenomes);
		
		for (int i = 1; i <= QUANTIDADE_DOCENTES; i++) {
			RandomizerUniqueInteger r1 = new RandomizerUniqueInteger(nomes.length);
			Integer randomNome = r1.getNewRandom();
			
			RandomizerUniqueInteger r2 = new RandomizerUniqueInteger(sobrenomes.length);
			Integer randomSobrenome = r2.getNewRandom();
			
			Random r3 = new Random();
			//sao 28 departamentos...
			int depRandom = r3.nextInt(29);
			if (depRandom == 0) {
				depRandom = 1;
			}
			Departamento dep = departamentoRepository.findOne(new Long(depRandom));
			
			String docenteNome = nomes[randomNome] + " " + sobrenomes[randomSobrenome];
			
			Docente docente = new DocenteBuilder()
					.nome(docenteNome)
					.departamento(dep)
					.matricula(""+i)
					.build();
			
			docentes.add(docente);
			
		}
		
		docenteRepository.save(docentes);
	}

	private String getConteudoArquivoNomes() {
		String arquivoNomes = null;
		try {
			arquivoNomes = FileUtils.getContent("cadastro/docentes/nomes.txt", "UTF-8");
		} catch (IOException e) {
			LOG.error("Erro ao obter o conteudo do arquivo de nomes: ", e);
		}
		return arquivoNomes;
	}
	
	private String getConteudoArquivoSobrenomes() {
		String arquivoSobrenomes = null;
		try {
			arquivoSobrenomes = FileUtils.getContent("cadastro/docentes/sobrenomes.txt", "UTF-8");
		} catch (IOException e) {
			LOG.error("Erro ao obter o conteudo do arquivo de sobrenomes: ", e);
		}
		return arquivoSobrenomes;
	}

	private String getConteudoArquivoDepartamentos() {
		String departamentos = null;
		try {
			departamentos = FileUtils.getContent("cadastro/departamentos/departamentos_ufg.txt", "UTF-8");
		} catch (IOException e) {
			LOG.error("Erro ao obter o conteudo do arquivo de departamentos: ", e);
		}
		return departamentos;
	}
}
