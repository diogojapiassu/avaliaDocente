package br.ufg.inf.es.avaliadocente.repository;

import br.ufg.inf.es.avaliadocente.model.bean.GrupoAtividade;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link GrupoAtividade}.
 * 
 * @author Danilo Guimarães
 *
 */
public interface GrupoAtividadeRepository extends GenericRepository<GrupoAtividade, Long> {

	/**
	 * 
	 * @param indice
	 * @return
	 */
	GrupoAtividade findByIndice(Integer indice);

}
