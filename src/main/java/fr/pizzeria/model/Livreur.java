package fr.pizzeria.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="livreurs")
public class Livreur {
	
	@OneToMany(mappedBy="livreur")
	private Set<Commande> commandes;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	
	public Livreur() {
	}
}
