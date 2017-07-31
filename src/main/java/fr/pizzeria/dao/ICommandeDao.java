package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public interface ICommandeDao extends IPizzeriaDB {

	List<Commande> findAllCommandes(Client currentClient);
	
	void saveNewCommande(Commande commande) throws Exception;
	
}
