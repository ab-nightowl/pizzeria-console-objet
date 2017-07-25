package fr.pizzeria.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="commandes")
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer numeroCommande;
	private String statut;
	private LocalDateTime dateCommande;
	
	@ManyToOne
	@JoinColumn(name="livreur_id")
	private Livreur livreur;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	public Commande() {
	}
	
	public Commande(Integer numeroCommande, LocalDateTime dateCommande, Client client) {
		super();
		this.numeroCommande = numeroCommande;
		this.statut = "Non traité";
		this.dateCommande = dateCommande;
		this.client = client;
	}

}
