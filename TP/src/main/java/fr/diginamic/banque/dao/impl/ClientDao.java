package fr.diginamic.banque.dao.impl;

import fr.diginamic.banque.entities.Client;

public class ClientDao extends Dao<Client> {

	public ClientDao(EmfManager emfm) {
		super(emfm, Client.class);
	}

}
