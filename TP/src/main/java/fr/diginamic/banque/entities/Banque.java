package fr.diginamic.banque.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Banque.getClients", query = "select cl from Client cl where cl.banque.id = :id")
})
public class Banque extends BaseEntity {

	private String nom;
	
	//@OneToMany(mappedBy = "client")
	//private Set<Client> clients;

	public Banque() {
		super();
		//clients = new HashSet<>();
	}

	public Banque(String nom) {
		this();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Banque [nom=" + nom + "]";
	}
	
}
