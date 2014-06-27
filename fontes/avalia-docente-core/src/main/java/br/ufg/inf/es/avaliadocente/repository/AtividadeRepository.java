package br.ufg.inf.es.avaliadocente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link Atividade}.
 * 
 * @author Danilo Guimarães
 *
 */
public interface AtividadeRepository extends GenericRepository<Atividade, Long> {
	
	@Query(value="select a from Atividade a where a.grupoAtividade = ?1")
	List<Atividade> findByGrupoAtividade(GrupoAtividade grupoAtividade);

	Atividade findByIndice(Long indice);

	@Query(value = "select a from Atividade a where a.indice = ?2 and a.grupoAtividade = (select g from GrupoAtividade g where g.id=?1)")
	Atividade findByGrupoAtividadeAndIndice(Long gprId, Long atividadeIndice);

}
