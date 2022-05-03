package fr.diginamic.jpa.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre extends BaseEntity {

	@Column(name = "titre")
	private String titre;
	
	@Column(name = "auteur")
	private String auteur;
	
	@ManyToMany(mappedBy = "livres")
	private Set<Emprunt> emprunts;

	public Livre() {
	}

	public Livre(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	@Override
	public String toString() {
		return titre + ", écrit par " + auteur;
	}
}
