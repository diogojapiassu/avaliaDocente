package br.ufg.inf.es.avaliadocente.main.populador;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;
import br.ufg.inf.es.avaliadocente.model.bean.builder.GrupoAtividadeBuilder;
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
	
	@Autowired
	PopuladorResolucao populadorResolucao;

	@Override
	public void popular() {
		Resolucao resolucao = populadorResolucao.getResolucao();
		
		GrupoAtividade g1 = new GrupoAtividadeBuilder()
				.descricao("I - Atividade de Ensino")
				.resolucao(resolucao)
				.build();

		GrupoAtividade g2 = new GrupoAtividadeBuilder()
				.descricao("II - Producao Intelectual")
				.resolucao(resolucao)
				.build();

		GrupoAtividade g3 = new GrupoAtividadeBuilder()
				.descricao("III - Atividades de Pesquisa e Extensão")
				.resolucao(resolucao)
				.build();

		GrupoAtividade g4 = new GrupoAtividadeBuilder()
				.descricao("IV - Atividades Administrativas e de Representacao")
				.resolucao(resolucao)
				.build();

		GrupoAtividade g5 = new GrupoAtividadeBuilder()
				.descricao("V - Outras atividades")
				.resolucao(resolucao)
				.build();
		
		repository.saveAndFlush(g1);
		repository.saveAndFlush(g2);
		repository.saveAndFlush(g3);
		repository.saveAndFlush(g4);
		repository.saveAndFlush(g5);
	}

}
