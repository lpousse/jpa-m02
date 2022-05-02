package fr.diginamic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.entities.Livre;

public class TestJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("bddBiblio");
			EntityManager em = emf.createEntityManager();
			
			Livre l1 = em.find(Livre.class, 2);
			System.out.println(l1);

			/*
			em.getTransaction().begin();
			Livre l2 = new Livre("Blame!", "Tsutomu Nihei");
			em.persist(l2);
			em.getTransaction().commit();
			*/
			
			em.getTransaction().begin();
			Livre l3 = em.find(Livre.class, 5);
			if (l3 != null)
			{
				l3.setTitre("Du plaisir dans la cuisine");
				em.getTransaction().commit();
			}
			else
				em.getTransaction().rollback();
			
			TypedQuery<Livre> qf = em.createQuery("SELECT l FROM Livre l WHERE l.titre = 'Germinal'", Livre.class);
			List<Livre> ll = qf.getResultList();
			ll.stream().forEach(l4 -> System.out.println(l4));
			
			qf = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = 'Tsutomu Nihei'", Livre.class);
			ll = qf.getResultList();
			ll.stream().forEach(l5 -> System.out.println(l5));
			
			em.getTransaction().begin();
			Livre l6 = em.find(Livre.class, 3);
			if (l6 != null)
			{
				em.remove(l6);
				em.getTransaction().commit();
			}
			else
				em.getTransaction().rollback();
			
			em.close();
			emf.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
