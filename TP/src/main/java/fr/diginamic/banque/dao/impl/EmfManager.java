package fr.diginamic.banque.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfManager {
	
	private final EntityManagerFactory emf;
	
	public EmfManager(String unitepersistence) throws Exception {
		emf = Persistence.createEntityManagerFactory(unitepersistence);
	}
	
	public EntityManager getEm() throws Exception {
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em) throws Exception {
		if (em.isOpen()) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
	
	private void close() throws Exception {
		if (emf.isOpen()) {
			emf.close();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		close();
	}
}