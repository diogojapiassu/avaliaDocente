package br.ufg.inf.es.avaliadocente.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Modifying
	@Transactional
	@Query(value = "delete from grupoatividade where id = ?1", nativeQuery=true)
	void excluirGrupoAtividade(Long idGrupoAtividade);
	
	@Modifying
	@Transactional
	@Query(value = "update GrupoAtividade g set g.descricao = ?1 where g.id = ?2")
	void atualizarGrupoAtividade(String descricao, Long idGrupoAtividade);
}