package fr.diginamic.jpa;

import java.util.Date;
import java.util.Iterator;

import fr.diginamic.jpa.dao.impl.ArticleDao;
import fr.diginamic.jpa.dao.impl.BonDao;
import fr.diginamic.jpa.dao.impl.FactoryDao;
import fr.diginamic.jpa.dao.impl.FournisseurDao;
import fr.diginamic.jpa.entities.Article;
import fr.diginamic.jpa.entities.Bon;
import fr.diginamic.jpa.entities.Fournisseur;

public class AppFactory {

	public static FactoryDao COMPTA;
	
	public static void main(String[] args) {
		try {
			AppFactory.COMPTA = new FactoryDao("bddCompta");
			FournisseurDao fdo = new FournisseurDao(AppFactory.COMPTA);
			BonDao bdo = new BonDao(AppFactory.COMPTA);
			ArticleDao ado = new ArticleDao(COMPTA);

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
			
			/**
			* Gestion des Articles
			*/
			/**
			* Ajout
			*/
			Article a1 = new Article();
			a1.setDesignation("Art Digi01");
			a1.setFoArticle(fo);
			a1.setPrix(12.36);
			a1.setReference("REFDIGI01");
			ado.add(a1);
			/**
			* Modification de l'article Courant
			*/
			a1.setPrix(12.42);
			ado.update(a1);
			/**
			* Modification d'un article sélectionné
			*/
			a1.setId(7); //MODIFIER A03
			a1 = ado.getOne(a1);
			fo.setId(5); //Selection du Fournisseur 5
			fo = fdo.getOne(fo);
			a1.setFoArticle(fo);
			a1.setPrix(145.23);
			ado.update(a1);
			/**
			* Suppression de l'article 10
			**/
			a1.setId(10);
			ado.delete(a1);
			
			/**
			* Gestion des Bons + Articles
			*/
			bo.setId(7); //Sélection du Bon N° 7
			Bon bo7 = bdo.getOne(bo);
			bo.setId(3); //Sélection du Bon N° 7
			Bon bo3 = bdo.getOne(bo);
			a1.setId(1);//Sélection du Article N° 1
			Article ab1 = ado.getOne(a1);
			a1.setId(5);//Sélection du Article N° 5
			Article ab5 = ado.getOne(a1);
			a1.setId(4);//Sélection du Article N° 4
			Article ab4 = ado.getOne(a1);
			
			/**
			* Affection des articles 4 et 1 au bon 7
			*/
			bo7.getBonArticles().add(ab4);
			bo7.getBonArticles().add(ab1);
			/**
			* Sauvegarde des modifications dans la BDD
			*/
			bdo.update(bo7);
			
			/**
			* Affection des articles 4 et 5 au bon 3
			*/
			bo3.getBonArticles().add(ab4);
			bo3.getBonArticles().add(ab5);
			/**
			* Sauvegarde des modifications dans la BDD
			*/
			bdo.update(bo3);
			
			bdo.getAll().forEach(bon->System.out.println(bon));

			ado.getBons(ab4).forEach(bon->System.out.println(bon));
			
			/**
			* Gérer la suppression des articles du BON bo3
			*/
			bo3.getBonArticles().clear(); //Vide la collection des Articles liés au BON3
			bdo.update(bo3); //BON.JAVA EST LE MASTER DE LA JOINTABLE
			//Déclenchement de la suppression BDD dans la table REL_BON_ART
			
			/**
			* Gérer la suppression de l'article 4 du BON bo7
			*/
			System.out.println("AVANT : "+bo7.getBonArticles());
			//bo7.getBonArticles().remove(ab4);
			Iterator<Article> ia = bo7.getBonArticles().iterator();
			while(ia.hasNext()) {
				Article a = ia.next();
				if(a.getId() == ab4.getId()) {
					ia.remove();
				}
			}
			System.out.println("APRES : "+bo7.getBonArticles());
			bdo.update(bo7);
			//Déclenchement de la suppression de l'article 4 dans la BDD
			// dans la table REL_BON_ART pour le BON 7
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
