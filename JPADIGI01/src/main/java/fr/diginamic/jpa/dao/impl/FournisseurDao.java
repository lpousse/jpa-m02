package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Bon;
import fr.diginamic.jpa.entities.Fournisseur;

public class FournisseurDao extends Dao implements Idao<Fournisseur> {

	public FournisseurDao(FactoryDao fd) {
		super(fd);
	}

	@Override
	public boolean add(Fournisseur e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean update(Fournisseur e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Fournisseur etrans = em.find(Fournisseur.class, e.getId());
			if (etrans != null) {
				etrans.setNom(e.getNom());
				em.merge(etrans);
				em.getTransaction().commit();
				return true;
			}
			// em.getTransaction().rollback(); // pas nécessaire car fait
			// automatiquement dans le close du FactoryDao
			return false;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean delete(Fournisseur e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Fournisseur.class, e.getId());
			if (e != null) {
				em.remove(e);
				em.getTransaction().commit();
				return true;
			}
			// em.getTransaction().rollback(); // pas nécessaire car fait
			// automatiquement dans le close du FactoryDao
			return false;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public Fournisseur getOne(Fournisseur e) throws Exception {
		TypedQuery<Fournisseur> tqb = fd.getEm().createQuery("select fo from Fournisseur fo where fo.id = :id", Fournisseur.class);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList().get(0);
	}

	@Override
	public List<Fournisseur> getAll() throws Exception {
		TypedQuery<Fournisseur> tqb = fd.getEm().createQuery("select fo from Fournisseur fo", Fournisseur.class);
		return tqb.getResultList();
	}
	
	public List<Bon> getBons(Fournisseur e) throws Exception {
		TypedQuery<Bon> tqb = fd.getEm().createNamedQuery("Fournisseur.getBons", Bon.class);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList();
	}

}
