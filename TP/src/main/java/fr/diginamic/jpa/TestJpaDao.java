package fr.diginamic.jpa;

import fr.diginamic.jpa.dao.impl.EmfManager;
import fr.diginamic.jpa.dao.impl.LivreDao;
import fr.diginamic.jpa.entities.Livre;

public class TestJpaDao {
	
	public static EmfManager BIBLIO;
	
	public static void main(String[] args) {
		try {
			TestJpaDao.BIBLIO = new EmfManager("bddBiblio");
			LivreDao ldao = new LivreDao(TestJpaDao.BIBLIO);
			
			Livre l1 = new Livre();
			l1.setId(2);
			l1 = ldao.getOne(l1);
			System.out.println(l1);
			
			Livre l2 = new Livre("Blame!", "Tsutomu Nihei");
			//ldao.add(l2);
			
			Livre l3 = new Livre();
			l3.setId(5);
			l3 = ldao.getOne(l3);
			if (l3 != null)
			{
				l3.setTitre("Du plaisir dans la cuisine");
				ldao.update(l3);
			}
			
			Livre l4 = new Livre();
			l4.setId(3);
			ldao.getOne(l4);
			if (l4 != null) {
				ldao.delete(l4);
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
