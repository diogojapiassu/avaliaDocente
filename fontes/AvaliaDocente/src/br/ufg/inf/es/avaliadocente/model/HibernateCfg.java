package br.ufg.inf.es.avaliadocente.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.ufg.inf.es.avaliadocente.model.bean.QuadroSumario;

@SuppressWarnings("deprecation")
public class HibernateCfg {
	private static Session session;
	
	public HibernateCfg() {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure();
		annotationConfiguration.addAnnotatedClass(QuadroSumario.class);
		SessionFactory sessionFactory = annotationConfiguration.buildSessionFactory(); 
		setSession(sessionFactory.openSession());
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		HibernateCfg.session = session;
	}

}
