package fr.diginamic.banque.dao.impl;

import fr.diginamic.banque.entities.Operation;

public class OperationDao extends Dao<Operation> {

	public OperationDao(EmfManager emfm) {
		super(emfm, Operation.class);
	}

}
