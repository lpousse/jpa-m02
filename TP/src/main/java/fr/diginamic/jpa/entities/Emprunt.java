package fr.diginamic.jpa.entities;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Emprunt extends BaseEntity {

	@Column(name = "date_debut")
	private LocalDate dateDebut;
	
	private int	delai;
	
	@Column(name = "date_fin")
	private LocalDate dateFin;
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
	
	@ManyToMany
	@JoinTable(name="compo",
		joinColumns = @JoinColumn(name = "id_emp", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "id_liv", referencedColumnName = "id"))
	private Set<Livre> livres;
	
	public Emprunt() {
	}

	public Emprunt(LocalDate dateDebut, int delai, LocalDate dateFin, Client client, Set<Livre> livres) {
		this.dateDebut = dateDebut;
		this.delai = delai;
		this.dateFin = dateFin;
		this.client = client;
		this.livres = livres;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public int getDelai() {
		return delai;
	}

	public void setDelai(int delai) {
		this.delai = delai;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}
	
	@Override
	public String toString() {
		String s = "Emprunt du " + dateDebut;
		if (dateFin != null)
			s += " au " + dateFin;
		else
			s += " avec un délai de " + delai + " jours";
		s += " par " + client + (livres.size() <= 1 ? " du livre " : " des livres ");
		for (Iterator it = livres.iterator(); it.hasNext();) {
			Livre livre = (Livre) it.next();
			s += livre.getTitre();
			if (it.hasNext())
				s += ", ";
		}
		return s;
	}
}
