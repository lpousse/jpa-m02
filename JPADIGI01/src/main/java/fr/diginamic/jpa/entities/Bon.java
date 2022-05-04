package fr.diginamic.jpa.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bon")
public class Bon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int numero;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_CMDE", nullable = true)
	private Date dateCmde;

	private int delai;

	@ManyToOne
	@JoinColumn(name = "ID_FOU", nullable = false)
	private Fournisseur foBon;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "REL_BON_ART",
			joinColumns = @JoinColumn(name = "ID_BON", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_ART", referencedColumnName = "ID"))
	private Set<Article> bonArticles;
	
	public Bon() {
		bonArticles = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getDateCmde() {
		return dateCmde;
	}

	public void setDateCmde(Date dateCmde) {
		this.dateCmde = dateCmde;
	}

	public int getDelai() {
		return delai;
	}

	public void setDelai(int delai) {
		this.delai = delai;
	}

	public Fournisseur getFoBon() {
		return foBon;
	}

	public void setFoBon(Fournisseur foBon) {
		this.foBon = foBon;
	}

	public Set<Article> getBonArticles() {
		return bonArticles;
	}

	public void setBonArticles(Set<Article> bonArticles) {
		this.bonArticles = bonArticles;
	}

	@Override
	public String toString() {
		return "Bon [id=" + id + ", numero=" + numero + ", dateCmde=" + dateCmde
				+ ", delai=" + delai + ", foBon=" + foBon + "]";
	}
}
