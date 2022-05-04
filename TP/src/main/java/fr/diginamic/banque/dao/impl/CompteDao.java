package fr.diginamic.banque.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import fr.diginamic.banque.entities.Client;
import fr.diginamic.banque.entities.Compte;
import fr.diginamic.banque.entities.Operation;

public class CompteDao extends Dao<Compte> {

	public CompteDao(EmfManager emfm) {
		super(emfm, Compte.class);
	}

	public List<Client> getClients(Compte co) throws Exception {
		TypedQuery<Client> tqb = emfm.getEm().createNamedQuery("Compte.getClients", Client.class);
		tqb.setParameter("compte", co);
		return tqb.getResultList();
	}
	
	public List<Operation> getOperations(Compte co) throws Exception {
		TypedQuery<Operation> tqb = emfm.getEm().createNamedQuery("Compte.getOperations", Operation.class);
		tqb.setParameter("id", co.getId());
		return tqb.getResultList();
	}
}
