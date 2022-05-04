package fr.diginamic.banque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.dao.Idao;
import fr.diginamic.banque.entities.BaseEntity;

public class Dao<T extends BaseEntity> implements Idao<T> {

	protected final EmfManager emfm;
	private final Class<T> type;
	
	public Dao(EmfManager emfm, Class<T> type) {
		this.emfm = emfm;
		this.type = type;
	}
	
	public boolean add(T e) throws Exception {
		EntityManager em = null;
		try {
			em = emfm.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			emfm.close(em);
		}
	}
	
	public boolean update(T e) throws Exception {
		EntityManager em = null;
		try {
			em = emfm.getEm();
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
			emfm.close(em);
		}
	}
	
	public boolean delete(T e) throws Exception {
		EntityManager em = null;
		try {
			em = emfm.getEm();
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
			emfm.close(em);
		}
	}
	
	public T getOne(T e) throws Exception {
		TypedQuery<T> tqb = emfm.getEm().createQuery("select obj from " + type.getName() + " obj where obj.id = :id", type);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList().get(0);
	}
	
	public T getOne(int id) throws Exception {
		TypedQuery<T> tqb = emfm.getEm().createQuery("select obj from " + type.getName() + " obj where obj.id = :id", type);
		tqb.setParameter("id", id);
		return tqb.getResultList().get(0);
	}
	
	public List<T> getAll() throws Exception {
		TypedQuery<T> tqb = emfm.getEm().createQuery("select obj from " + type.getName() + " obj", type);
		return tqb.getResultList();
	}
}