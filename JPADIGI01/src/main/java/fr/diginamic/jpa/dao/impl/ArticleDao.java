package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Article;
import fr.diginamic.jpa.entities.Bon;

public class ArticleDao extends Dao implements Idao<Article> {

	public ArticleDao(FactoryDao fd) {
		super(fd);
	}

	@Override
	public boolean add(Article e) throws Exception {
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
	public boolean update(Article e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Article etrans = em.find(Article.class, e.getId());
			if (etrans != null) {
				etrans.setReference(e.getReference());
				etrans.setDesignation(e.getDesignation());
				etrans.setPrix(e.getPrix());
				etrans.setFoArticle(etrans.getFoArticle());
				em.merge(etrans);
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

	@Override
	public boolean delete(Article e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Article.class, e.getId());
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

	@Override
	public Article getOne(Article e) throws Exception {
		TypedQuery<Article> tqb = fd.getEm().createQuery("select fo from Article fo where fo.id = :id", Article.class);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList().get(0);
	}

	@Override
	public List<Article> getAll() throws Exception {
		TypedQuery<Article> tqb = fd.getEm().createQuery("select art from Article art", Article.class);
		return tqb.getResultList();
	}

	public List<Bon> getBons(Article e) throws Exception {
		TypedQuery<Bon> tqb = fd.getEm().createNamedQuery("Article.getBons", Bon.class);
		tqb.setParameter("article", e);
		return tqb.getResultList();
	}
	
}
