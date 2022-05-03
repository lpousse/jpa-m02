package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Bon;

public class BonDao extends Dao implements Idao<Bon> {

	public BonDao(FactoryDao fd) {
		super(fd);
	}

	@Override
	public boolean add(Bon e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			return true;
		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean update(Bon e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Bon etrans = em.find(Bon.class, e.getId());
			if (etrans != null) {
				etrans.setDateCmde(e.getDateCmde());
				etrans.setDelai(e.getDelai());
				etrans.setNumero(e.getNumero());
				etrans.setFoBon(e.getFoBon());
				//em.merge(etrans);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean delete(Bon e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Bon.class, e.getId());
			if (e != null) {
				em.remove(e);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public Bon getOne(Bon e) throws Exception {
		TypedQuery<Bon> tqb = fd.getEm().createQuery("select bon from Bon bon where bon.id = :id", Bon.class);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList().get(0);
	}

	@Override
	public List<Bon> getAll() throws Exception {
		TypedQuery<Bon> tqb = fd.getEm().createQuery("select bon from Bon bon",Bon.class);
		return tqb.getResultList();
	}

}
