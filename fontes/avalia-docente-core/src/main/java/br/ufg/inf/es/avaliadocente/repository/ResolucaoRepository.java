package br.ufg.inf.es.avaliadocente.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.inf.es.avaliadocente.model.bean.Resolucao;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link Resolucao}.
 * 
 * @author Danilo Guimarães
 *
 */
public interface ResolucaoRepository extends GenericRepository<Resolucao, Long> {
	
	/*@Modifying
	@Transactional
	@Query(value = "delete from resolucao where id = ?1", nativeQuery=true)
	void excluirResolucao(Long idResolucao);
	
	@Modifying
	@Transactional
	@Query(value = "update Resolucao r set r.descricao = ?1, r.numeronotas = ?2 where id = ?3")
	void atualizarResolucao(String descricao, int numeroNotas, Long idResolucao);*/
}