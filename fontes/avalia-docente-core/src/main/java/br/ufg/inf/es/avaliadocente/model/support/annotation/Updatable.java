package br.ufg.inf.es.avaliadocente.model.support.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotação para verificar se a entidade do modelo pode ser atualizada.
 * 
 * <p>
 * Quando não, verifica se irá fazer um novo {@code insert} ou lançar exceção
 * </p>
 * 
 * @author Danilo Guimarães
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Updatable {

    /**
	 * (Opcional) Se a entidade pode ter seus valores atualizados.
	 * <br>Default: {@code true}.
	 */
    boolean updatable() default true;

    /**
	 * (Opcional) Se a entidade, mesmo não sendo atualizável, deverá ter um novo
	 * insert para os valores.
	 * <br>Default: {@code false}.
	 */
    boolean newinsert() default false;

}
