package fr.diginamic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.entities.Fournisseur;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("bddCompta");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Fournisseur fo = new Fournisseur();
			fo.setNom("FOUR DIGI 01");
			em.persist(fo);
			em.getTransaction().commit();
			System.out.println("ID AUTOINCR : " + fo.getId());

			em.getTransaction().begin();
			fo = em.find(Fournisseur.class, fo.getId());
			if (fo != null) {
				fo.setNom("FOUR DIGI 01 UPDATE");
				em.merge(fo);
				em.getTransaction().commit();
			} else
				em.getTransaction().rollback();

			em.getTransaction().begin();
			fo = em.find(Fournisseur.class, fo.getId());
			if (fo != null) {
				em.remove(fo);
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}

			TypedQuery<Fournisseur> qf = em.createQuery("SELECT fo FROM Fournisseur fo", Fournisseur.class);
			List<Fournisseur> lfo = qf.getResultList();
			lfo.stream().forEach(f -> System.out.println(f.getNom()));

			em.close();
			emf.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
