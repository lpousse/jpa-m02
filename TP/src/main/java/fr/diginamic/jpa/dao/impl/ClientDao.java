package fr.diginamic.jpa.dao.impl;

import fr.diginamic.jpa.entities.Client;

public class ClientDao extends Dao<Client> {

	public ClientDao(EmfManager fd) {
		super(fd, Client.class);
	}

}
