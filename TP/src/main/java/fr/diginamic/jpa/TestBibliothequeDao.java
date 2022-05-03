package fr.diginamic.jpa;

import fr.diginamic.jpa.dao.impl.Dao;
import fr.diginamic.jpa.dao.impl.EmfManager;
import fr.diginamic.jpa.dao.impl.EmpruntDao;
import fr.diginamic.jpa.dao.impl.LivreDao;
import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;

public class TestBibliothequeDao {

	public static EmfManager BIBLIO;
	
	public static void main(String[] args) {
		try {
			TestBibliothequeDao.BIBLIO = new EmfManager("bddBiblio");
			LivreDao ldao = new LivreDao(TestBibliothequeDao.BIBLIO);
			EmpruntDao edao = new EmpruntDao(BIBLIO);
			Dao<Client> cdao = new Dao<Client>(BIBLIO, Client.class);
			
			Emprunt e1 = edao.getOne(2);
			System.out.println(e1);
			
			Client c1 = cdao.getOne(1);
			System.out.println(c1 + " a fait les emprunts suivants : ");
			c1.getEmprunts().forEach(e -> System.out.println(e));
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
