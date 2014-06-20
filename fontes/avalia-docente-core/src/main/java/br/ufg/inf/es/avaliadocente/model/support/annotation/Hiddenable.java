package br.ufg.inf.es.avaliadocente.model.support.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotação que permite que o framework de persistência exclua logicamente um
 * objeto. Um objeto que é anotado como {@link Hiddenable} sigfica o mesmo que,
 * no bom e velho goianês, "mocozável".
 * 
 * <p>
 * As entidades que por ventura possuirem essa annotation, terão seus registros
 * "mocozados" quando for solicitado uma exclusão. Já durante uma busca, os
 * registros com o atributo <code>hidden == true</code> não serão consultados.
 * </p>
 * 
 * <p>
 * Um outro cenário é quando um objeto que já está persistido é atualizado
 * <b>E</b> a entidade em questão é {@link Updatable #newinsert()}. Nesse caso,
 * há um versionamento natural dos registros. <br>
 * Segue um exemplo prático:
 * 
 * <ol>
 * <li>um novo registro <tt>A</tt> é persistido (logo, sua versao anterior é nula),</li>
 * <li>o objeto <tt>A</tt> é alterado e vira <tt>B</tt>,</li>
 * <li>é solicitado que <tt>B</tt> seja atualizado/salvo novamente,</li>
 * <li><tt>A</tt> é setado como hidden,</li>
 * <li>a versão anterior de <tt>B</tt> é setado como sendo <tt>A</tt>,</li>
 * <li><tt>B</tt> é persistido.</li>
 * </ol>
 * 
 * <br>
 * No final das contas, para um mesmo objeto tem-se dois registros: um ativo (B)
 * e outro não (A).
 * </p>
 * 
 * @author Danilo Guimarães
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Hiddenable { }