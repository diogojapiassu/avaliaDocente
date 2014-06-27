package br.ufg.inf.es.avaliadocente.main.populador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Departamento;
import br.ufg.inf.es.avaliadocente.repository.DepartamentoRepository;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

public class PopuladorDepartamento implements IPopulador {
	
	private static final Logger LOG = Logger.getLogger(PopuladorDepartamento.class);
	
	private List<Departamento> departamentosList = new ArrayList<>();
	
	@Autowired
	DepartamentoRepository departamentoRepository;

	@Override
	public void popular() {
		String departamentos = getConteudoArquivoDepartamentos();
		
		String[] departamentosUfg = FileUtils.splitByNewLine(departamentos);
		
		for(String departamento : departamentosUfg) {
			Departamento dep = new Departamento();
			dep.setNome(departamento);
			
			departamentosList.add(dep);
		}
		
		departamentoRepository.save(departamentosList);
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
