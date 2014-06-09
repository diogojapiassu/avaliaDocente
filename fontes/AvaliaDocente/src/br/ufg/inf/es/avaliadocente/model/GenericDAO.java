package br.ufg.inf.es.avaliadocente.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class GenericDAO {
	private static Session session;

	public void teste() {
		HibernateCfg hibernateCfg = new HibernateCfg();
		session = hibernateCfg.getSession();

		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

}
