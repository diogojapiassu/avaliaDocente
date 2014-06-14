package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.repository.GrupoAtividadeRepository;

/**
 * Classe simples para população de {@link GrupoAtividade} no banco de dados.
 * 
 * @author Danilo Guimarães
 *
 */
public class PopuladorGrupoAtividade implements IPopulador {
	
	@Autowired
	GrupoAtividadeRepository repository;

	@Override
	public void popular() {
		GrupoAtividade g1 = new GrupoAtividade();
		g1.setDescricao("I - Atividade de Ensino");
		
		GrupoAtividade g2 = new GrupoAtividade();
		g2.setDescricao("II - Producao Intelectual");
		
		GrupoAtividade g3 = new GrupoAtividade();
		g3.setDescricao("III - Atividades de Pesquisa e Extensão");
		
		GrupoAtividade g4 = new GrupoAtividade();
		g4.setDescricao("IV - Atividades Administrativas e de Representacao");
		
		GrupoAtividade g5 = new GrupoAtividade();
		g5.setDescricao("V - Outras atividades");
		
		repository.saveAndFlush(g1);
		repository.saveAndFlush(g2);
		repository.saveAndFlush(g3);
		repository.saveAndFlush(g4);
		repository.saveAndFlush(g5);
	}

}
