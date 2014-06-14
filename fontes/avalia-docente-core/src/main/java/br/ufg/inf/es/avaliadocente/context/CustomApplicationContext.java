package br.ufg.inf.es.avaliadocente.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Isola e abstrai o {@link org.springframework.context.ApplicationContext} da aplicação.
 * 
 * <p>
 * Implementa o pattern <tt>Singleton</tt>.
 * </p>
 * 
 * @author Danilo Guimarães
 *
 */
public class CustomApplicationContext {
	
	/**
	 * Instancia singleton.
	 */
	private static CustomApplicationContext instance;
	
	/**
	 * Application Context de verdade...
	 */
	private org.springframework.context.ApplicationContext context;
	
	/**
	 * Diretório do arquivo {@code applicationContext.xml} do Spring Framework.
	 */
	private static final String APPLICATION_CONTEXT_LOCATION = "META-INF/spring/applicationContext.xml";
	
	private CustomApplicationContext() {
		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_LOCATION);
	}
	
	public static CustomApplicationContext getInstance() {
		if (instance == null) {
			instance = new CustomApplicationContext();
		}
		return instance;
	}
	
	/**
	 * Retorna o {@link org.springframework.context.ApplicationContext} da
	 * aplicação.
	 * @return {@link org.springframework.context.ApplicationContext} da
	 * aplicação.
	 */
	public org.springframework.context.ApplicationContext getContext() {
		return this.context;
	}

}
