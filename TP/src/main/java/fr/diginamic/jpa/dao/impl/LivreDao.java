package fr.diginamic.jpa.dao.impl;

import fr.diginamic.jpa.entities.Livre;

public class LivreDao extends Dao<Livre> {

	public LivreDao(EmfManager emfm) {
		super(emfm, Livre.class);
	}
	
}
