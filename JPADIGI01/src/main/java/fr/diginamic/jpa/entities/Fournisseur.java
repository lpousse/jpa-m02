package fr.diginamic.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fournisseur")
@NamedQueries({
	@NamedQuery(name = "Fournisseur.getBons", query = "select b from Bon b where b.foBon.id = :id")
})
public class Fournisseur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nom", length = 25, nullable = false)
	private String nom;

	@OneToMany(mappedBy = "foBon")
	private Set<Bon> bons;

	public Fournisseur() {
		bons = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Bon> getBons() {
		return bons;
	}

	public void setBons(Set<Bon> bons) {
		this.bons = bons;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}
}
