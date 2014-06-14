package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;
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

	@Override
	public void popular() {
		Resolucao res = new Resolucao();
		res.setDescricao("Resolucao 32/2013");
		
		repository.saveAndFlush(res);
		
	}

}
