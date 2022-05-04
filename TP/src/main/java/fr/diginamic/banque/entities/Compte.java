package fr.diginamic.banque.entities;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Compte.getClients", query = "select cl from Client cl where :compte MEMBER OF cl.comptes"),
	@NamedQuery(name = "Compte.getOperations", query = "select o from Operation o where o.compte.id = :id")
})
public class Compte extends BaseEntity {
	
	private String numero;
	private double solde;
	
	//@ManyToMany(mappedBy = "comptes")
	//private Set<Client> clients;
	
	//@OneToMany(mappedBy = "compte")
	//private Set<Operation> operations;

	public Compte() {
		super();
		//clients = new HashSet<>();
		//operations = new HashSet<>();
	}

	public Compte(String numero, double solde) {
		this();
		this.numero = numero;
		this.solde = solde;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", solde=" + solde + "]";
	}

}
