package br.ufg.inf.es.avaliadocente.repository.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import br.ufg.inf.es.avaliadocente.model.bean.Atividade;
import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;

/**
 * Repositório genérico para extender recursos do Spring Data, para queries ou
 * métodos mais elaborados.
 * 
 * <p>
 * Para consultar a documentação de como criar queries específicas do modelo de
 * domínio, <a href=
 * "http://docs.spring.io/spring-data/jpa/docs/1.4.2.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation"
 * >consulte aqui.</a>
 * 
 * @author Danilo Guimarães
 * @see JpaRepository
 */
@NoRepositoryBean
public interface GenericRepository<E extends AbstractEntity<E>, PK extends Serializable> extends JpaRepository<E, PK>,
        JpaSpecificationExecutor<E> {

    /**
     * Recupera o {@link EntityManager}
     * 
     * @return {@link EntityManager}
     */
    EntityManager getEntityManager();
    
    List<E> findAllOrdenadoPorId();

}
