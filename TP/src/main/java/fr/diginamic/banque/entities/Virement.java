package fr.diginamic.banque.entities;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Virement extends Operation {

	private String beneficiaire;

	public Virement() {
		super();
	}

	public Virement(LocalDateTime date, double montant, String motif, String beneficiaire) {
		super(date, montant, motif);
		this.beneficiaire = beneficiaire;
	}

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
		
	@Override
	public String toString() {
		return "Virement [date=" + getDate() + ", montant=" + getMontant() + ", motif=" + getMotif() + ", beneficiaire=" + beneficiaire + "]";
	}
	
}
