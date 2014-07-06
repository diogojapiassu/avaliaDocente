package br.ufg.inf.es.avaliadocente.repository;

import java.util.List;

import br.ufg.inf.es.avaliadocente.model.bean.NotasGrupoAtividade;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link NotasGrupoAtividade}.
 * 
 * @author Danilo Guimarães
 * @author Jhonatan Santos
 *
 */
public interface NotasGrupoAtividadeRepository extends GenericRepository<NotasGrupoAtividade, Long> {

	List<NotasGrupoAtividade> findByQuadroSumario(long idQuadroSumario);
}
