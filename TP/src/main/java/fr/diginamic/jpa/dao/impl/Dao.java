package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.BaseEntity;

public class Dao<T extends BaseEntity> implements Idao<T> {

	protected final EmfManager fd;
	private final Class<T> type;
	
	public Dao(EmfManager fd, Class<T> type) {
		this.fd = fd;
		this.type = type;
	}
	
	public boolean add(T e) throws Exception {
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
	
	public boolean update(T e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			if (em.find(type, e.getId()) != null) {
				em.merge(e);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}
	
	public boolean delete(T e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(type, e.getId());
			if (e != null) {
				em.remove(e);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}
	
	public T getOne(T e) throws Exception {
		TypedQuery<T> tqb = fd.getEm().createQuery("select obj from " + type.getName() + " obj where obj.id = :id", type);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList().get(0);
	}
	
	public T getOne(int id) throws Exception {
		TypedQuery<T> tqb = fd.getEm().createQuery("select obj from " + type.getName() + " obj where obj.id = :id", type);
		tqb.setParameter("id", id);
		return tqb.getResultList().get(0);
	}
	
	public List<T> getAll() throws Exception {
		TypedQuery<T> tqb = fd.getEm().createQuery("select obj from " + type.getName() + " obj", type);
		return tqb.getResultList();
	}
}
