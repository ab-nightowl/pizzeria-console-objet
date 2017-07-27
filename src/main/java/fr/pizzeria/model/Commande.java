package fr.pizzeria.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="commandes")
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String numeroCommande;
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
	
	public Commande(String numeroCommande, LocalDateTime dateCommande, Client client) {
		super();
		this.numeroCommande = numeroCommande;
		this.statut = "Non traité";
		this.dateCommande = dateCommande;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public LocalDateTime getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
