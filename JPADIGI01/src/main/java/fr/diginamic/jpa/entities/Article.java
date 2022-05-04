package fr.diginamic.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@NamedQueries(
		@NamedQuery(name = "Article.getBons", query = "select b from Bon b where :article MEMBER OF b.bonArticles")
)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "REF", length = 13, nullable = false)
	private String reference;

	@Column(name = "DESIGNATION", length = 255, nullable = false)
	private String designation;

	@Column(name = "PRIX", nullable = false)
	private Double prix;

	@ManyToOne
	@JoinColumn(name = "ID_FOU", nullable = false)
	private Fournisseur foArticle;

	public Article() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Fournisseur getFoArticle() {
		return foArticle;
	}

	public void setFoArticle(Fournisseur foArticle) {
		this.foArticle = foArticle;
	}
}
