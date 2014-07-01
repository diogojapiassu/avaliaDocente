package br.ufg.inf.es.avaliadocente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Modifying
	@Transactional
	@Query(value = "delete from atividade where id = ?1", nativeQuery=true)
	void excluirAtividade(Long idAtividade);
	
	@Modifying
	@Transactional
	@Query(value = "update Atividade a set a.descricao = ?1, a.indice = ?2 where id = ?3")
	void atualizarAtividade(String descricao, Long indice, Long idAtividade);
}
