package fr.pizzeria.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="pizzas")
public class Pizza {

	@ManyToMany
	@JoinTable(name = "commandes_pizzas", 
	joinColumns = @JoinColumn(name="id_pizza", referencedColumnName="id"), 
	inverseJoinColumns = @JoinColumn(name="id_commande", referencedColumnName="id"))
	private Set<Commande> commandes;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String nom;
	double prix;
	
	@Column(nullable=true)
	private String urlImage;
	
	public Pizza() {
	}

	public Pizza(String code, String nom, double prix) {
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.urlImage = urlImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

}
