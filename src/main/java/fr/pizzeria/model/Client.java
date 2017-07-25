package fr.pizzeria.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {
	
	@OneToMany(mappedBy="client")
	private Set<Commande> commandes;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	
	public Client() {
		super();
	}

	public Client(String nom, String prenom, String email, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
	}

}
