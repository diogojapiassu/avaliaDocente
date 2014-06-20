package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe simples para população de algumas entidades no banco de dados.
 * 
 * @author Danilo Guimarães
 *
 */
public class PopuladorBancoDeDados implements IPopulador {
	
	@Autowired
	PopuladorDocente populadorDocente;
	
	@Autowired
	PopuladorGrupoAtividade populadorGrupoAtividade;
	
	@Autowired
	PopuladorResolucao populadorResolucao;
	
	@Autowired
	PopuladorAtividade populadorAtividade;
	
	public PopuladorBancoDeDados() {
		
	}

	@Override
	public void popular() {
		populadorResolucao.popular();
		populadorDocente.popular();
		populadorGrupoAtividade.popular();
		populadorAtividade.popular();
	}

}
