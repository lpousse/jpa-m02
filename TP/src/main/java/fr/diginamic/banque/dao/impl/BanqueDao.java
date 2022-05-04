package fr.diginamic.banque.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import fr.diginamic.banque.entities.Banque;
import fr.diginamic.banque.entities.Client;

public class BanqueDao extends Dao<Banque> {

	public BanqueDao(EmfManager emfm) {
		super(emfm, Banque.class);
	}

	public List<Client> getClients(Banque b) throws Exception {
		TypedQuery<Client> tqb = emfm.getEm().createNamedQuery("Banque.getClients", Client.class);
		tqb.setParameter("id", b.getId());
		return tqb.getResultList();
	}
}
