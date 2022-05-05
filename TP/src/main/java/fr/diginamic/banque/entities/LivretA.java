package fr.diginamic.banque.entities;

import javax.persistence.Entity;

@Entity
public class LivretA extends Compte {

	private double taux;

	public LivretA() {
		super();
	}

	public LivretA(String numero, double solde, double taux) {
		super(numero, solde);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	@Override
	public String toString() {
		return "LivretA [numero=" + getNumero() + ", solde=" + getSolde() + ", taux=" + taux + "]";
	}
}
