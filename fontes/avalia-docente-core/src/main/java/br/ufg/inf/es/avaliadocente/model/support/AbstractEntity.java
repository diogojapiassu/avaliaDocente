package br.ufg.inf.es.avaliadocente.model.support;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.domain.Persistable;

/**
 * Entidade abstrata para objetos persistíveis.
 * 
 * <p>
 * Todas as outras entidades persistíveis do modelo de dados devem extender esta
 * entidade ou uma sub-classe dela
 * </p>
 * 
 * @author Danilo Guimarães
 */
@MappedSuperclass
public abstract class AbstractEntity<E extends Serializable> implements Persistable<Long> {

    private static final long serialVersionUID = -2187928984731943693L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Versão utilizada pelo provider JPA para solução em concorrência
     */
    @Version
    private Long versao;

    @Column
    private Boolean hidden;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_insercao_alteracao")
    private Date dataInsercaoAlteracao;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersao() {
        return this.versao;
    }

    public void setVersao(final Long versao) {
        this.versao = versao;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(final Boolean hidden) {
        this.hidden = hidden;
    }

    public Date getDataInsercaoAlteracao() {
        return this.dataInsercaoAlteracao;
    }

    public void setDataInsercaoAlteracao(final Date dataInsercaoAlteracao) {
        this.dataInsercaoAlteracao = dataInsercaoAlteracao;
    }

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return String.format("%s id: %s", this.getClass().getSimpleName(), this.getId());
    }

}
