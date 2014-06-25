package br.ufg.inf.es.avaliadocente.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;

/**
 * Listener de inicialização do contexto do Spring Framework.
 * 
 * @author Danilo Guimarães
 */
public class SpringContextStartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) { }

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CustomApplicationContext.getInstance();
	}

}
