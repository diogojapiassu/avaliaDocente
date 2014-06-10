package br.ufg.inf.es.avaliadocente.repository.support.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Implementação padrão do {@link GenericRepository}.
 * 
 * <p>
 * Extende recursos do Spring Data JPA, para ações mais sofisticadas.
 * </p>
 * 
 * @author Danilo Guimarães
 * @see SimpleJpaRepository
 */
@Transactional(readOnly = true)
public class GenericRepositoryImpl<E extends AbstractEntity<E>, PK extends Serializable> extends SimpleJpaRepository<E, PK>
        implements GenericRepository<E, PK> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<E, ?> entityInformation;

    public GenericRepositoryImpl(final JpaEntityInformation<E, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    @Transactional
    public <S extends E> S save(final S entity) {
        Assert.notNull(entity, "A entidade não pode ser nula!");
        final Date dataInsercaoAlteracao = Calendar.getInstance().getTime();
        entity.setDataInsercaoAlteracao(dataInsercaoAlteracao);
        if (this.getEntityInformation().isNew(entity)) {
            this.getEntityManager().persist(entity);
        } else {
            this.getEntityManager().merge(entity);
        }
        return entity;
    }
    
    @Override
    @Transactional
    public void delete(final E entity) {
        Assert.notNull(entity, "A entidade não pode ser nula!");
        this.getEntityManager().remove(entity);
    }

    @Override
    public List<E> findAll() {
        return super.findAll();
    }

    @Override
    public E findOne(final PK pk) {
        return super.findOne(pk);
    }
   
    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected JpaEntityInformation<E, ?> getEntityInformation() {
        return this.entityInformation;
    }

}
