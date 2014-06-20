package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;
import br.ufg.inf.es.avaliadocente.model.bean.builder.ResolucaoBuilder;
import br.ufg.inf.es.avaliadocente.repository.DocenteRepository;
import br.ufg.inf.es.avaliadocente.repository.ResolucaoRepository;

/**
 * Classe simples para população de {@link Resolucao} no banco de dados.
 * 
 * @author Danilo Guimarães
 *
 */

public class PopuladorResolucao implements IPopulador {
	
	@Autowired
	private ResolucaoRepository repository;
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	private Resolucao res;

	@Override
	public void popular() {
		res = new ResolucaoBuilder().descricao("Resolucao 32/2013")
				.build();
		repository.saveAndFlush(res);
		
	}
	
	public Resolucao getResolucao() {
		return this.res;
	}

}
