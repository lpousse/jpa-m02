package fr.diginamic.banque;

import fr.diginamic.banque.dao.impl.BanqueDao;
import fr.diginamic.banque.dao.impl.CompteDao;
import fr.diginamic.banque.dao.impl.Dao;
import fr.diginamic.banque.dao.impl.EmfManager;
import fr.diginamic.banque.entities.Banque;
import fr.diginamic.banque.entities.Client;
import fr.diginamic.banque.entities.Compte;
import fr.diginamic.banque.entities.Operation;

public class TestBanque {

	public static EmfManager BANQUE;
	
	public static void main(String[] args) {
		try {
			BANQUE = new EmfManager("bddBanque");
			BanqueDao bdao = new BanqueDao(BANQUE);
			Dao<Client> cldao = new Dao<Client>(BANQUE, Client.class);
			CompteDao codao = new CompteDao(BANQUE);
			Dao<Operation> odao = new Dao<Operation>(BANQUE, Operation.class);

			Banque b1 = new Banque("CIC");
			bdao.add(b1);
			Banque b2 = new Banque ("LCL");
			bdao.add(b2);
			Banque b3 = new Banque ("BNP");
			bdao.add(b3);
			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
