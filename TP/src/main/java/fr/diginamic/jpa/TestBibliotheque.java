package fr.diginamic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;
import fr.diginamic.jpa.entities.Livre;

public class TestBibliotheque {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("bddBiblio");
			EntityManager em = emf.createEntityManager();
			//EntityTransaction et = em.getTransaction();
			
			Emprunt e1 = em.find(Emprunt.class, 2);
			System.out.println(e1);
			
			Client c1 = em.find(Client.class, 1);
			c1.getEmprunts().forEach(e -> System.out.println(e));
			
			em.close();
			emf.close();
			
			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}

}
