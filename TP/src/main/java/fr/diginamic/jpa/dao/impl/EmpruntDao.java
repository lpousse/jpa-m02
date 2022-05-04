package fr.diginamic.jpa.dao.impl;

import fr.diginamic.jpa.entities.Emprunt;

public class EmpruntDao extends Dao<Emprunt> {

	public EmpruntDao(EmfManager emfm) {
		super(emfm, Emprunt.class);
	}

}
