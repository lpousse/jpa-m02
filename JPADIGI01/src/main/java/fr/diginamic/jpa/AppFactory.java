package fr.diginamic.jpa;

import java.util.Date;

import fr.diginamic.jpa.dao.impl.BonDao;
import fr.diginamic.jpa.dao.impl.FactoryDao;
import fr.diginamic.jpa.dao.impl.FournisseurDao;
import fr.diginamic.jpa.entities.Bon;
import fr.diginamic.jpa.entities.Fournisseur;

public class AppFactory {

	public static FactoryDao COMPTA;
	
	public static void main(String[] args) {
		try {
			AppFactory.COMPTA = new FactoryDao("bddCompta");
			FournisseurDao fdo = new FournisseurDao(AppFactory.COMPTA);
			
			BonDao bdo = new BonDao(AppFactory.COMPTA);

			Fournisseur fo = new Fournisseur();
			fo.setNom("FACTORY 01");
			fdo.add(fo);

			Bon bo = new Bon();
			bo.setDateCmde(new Date());
			bo.setDelai(10);
			bo.setFoBon(fo);
			bo.setNumero(125);
			bdo.add(bo);
			bdo.getAll().forEach(boi->System.out.println(boi));
			
			fdo.getAll().forEach(foi -> {
				System.out.println(foi);
				//foi.getBons().forEach(boi -> System.out.println(boi));
				try {
					fdo.getBons(foi).forEach(boi -> System.out.println(boi));
				}
				catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			});
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
