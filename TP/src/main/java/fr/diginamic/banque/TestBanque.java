package fr.diginamic.banque;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.diginamic.banque.dao.impl.BanqueDao;
import fr.diginamic.banque.dao.impl.ClientDao;
import fr.diginamic.banque.dao.impl.CompteDao;
import fr.diginamic.banque.dao.impl.Dao;
import fr.diginamic.banque.dao.impl.EmfManager;
import fr.diginamic.banque.dao.impl.OperationDao;
import fr.diginamic.banque.entities.Adresse;
import fr.diginamic.banque.entities.AssuranceVie;
import fr.diginamic.banque.entities.Banque;
import fr.diginamic.banque.entities.Client;
import fr.diginamic.banque.entities.Compte;
import fr.diginamic.banque.entities.LivretA;
import fr.diginamic.banque.entities.Operation;
import fr.diginamic.banque.entities.Virement;

public class TestBanque {

	public static EmfManager BANQUE;
	
	public static void main(String[] args) {
		try {
			BANQUE = new EmfManager("bddBanque");
			BanqueDao bdao = new BanqueDao(BANQUE);
			ClientDao cldao = new ClientDao(BANQUE);
			CompteDao codao = new CompteDao(BANQUE);
			OperationDao odao = new OperationDao(BANQUE);

			Banque cic = new Banque("CIC");
			bdao.add(cic);
			Banque bnp = new Banque ("BNP");
			bdao.add(bnp);
			
			// Insérer un Compte associé à deux Client
			Compte co1 = new LivretA("8494651", 500, 1.5);
			codao.add(co1);

			Adresse a1 = new Adresse(11, "rue des coquelicots", 12056, "Fleuret-sur-Saone");
			Client hugo = new Client("Hugo", "Victor", LocalDate.parse("1802-02-26"), a1);
			hugo.setBanque(cic);
			hugo.getComptes().add(co1);
			cldao.add(hugo);

			Client christie = new Client("Christie", "Agatha", LocalDate.parse("1890-09-15"), a1);
			christie.setBanque(cic);
			christie.getComptes().add(co1);
			cldao.add(christie);
			
			
			// Insérer un Client avec un LivretA et une AssuranceVie
			Adresse a2 = new Adresse(5, "avenue du Père", 87116, "Partition");
			Client conanDoyle = new Client("Conan Doyle", "Arthur", LocalDate.parse("1859-05-22"), a2);
			conanDoyle.setBanque(bnp);
			cldao.add(conanDoyle);

			Compte co2 = new AssuranceVie("5494651", 25000, LocalDate.parse("2022-05-30"), 4.5);
			codao.add(co2);
			Compte co3 = new LivretA("5494525", 2500, 1.6);
			codao.add(co3);
			
			conanDoyle.getComptes().add(co2);
			conanDoyle.getComptes().add(co3);
			cldao.update(conanDoyle);
			
			// Insérer des opérations de type virement sur un compte
			Operation o1 = new Virement(LocalDateTime.parse("2021-03-25T12:30:00"), 50, "virement 1", "Victor Hugo");
			o1.setCompte(co3);
			odao.add(o1);
			Operation o2 = new Virement(LocalDateTime.parse("2021-03-26T16:48:26"), 150, "virement 2", "Agatha Christie");
			o2.setCompte(co3);
			odao.add(o2);
			
			// Insérer des opérations de type virement sur un compte
			Operation o3 = new Operation(LocalDateTime.parse("2021-03-26T08:54:16"), 200, "operation 1");
			o3.setCompte(co1);
			odao.add(o3);
			Operation o4 = new Operation(LocalDateTime.parse("2021-05-02T10:46:24"), 5, "operation 2");
			o4.setCompte(co1);
			odao.add(o4);
			
			bdao.getAll().forEach(b -> {
				System.out.println(b);
				try {
					bdao.getClients(b).forEach(cl -> {
						System.out.println(cl);
						cl.getComptes().forEach(co -> {
							System.out.println(co);
							try {
								codao.getOperations(co).forEach(o -> System.out.println(o));
							}
							catch (Exception e) {
								System.err.println(e.getMessage());
							}
						});
					});
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}
			});
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
