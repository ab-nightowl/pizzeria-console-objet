package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class CommandeDaoJpa implements ICommandeDao {

	@Override
	public List<Commande> findAllCommandes(Client currentClient) {
		return currentClient.getCommandes();
	}

	@Override
	public void saveNewCommande(Commande commande) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(commande);
		em.getTransaction().commit();
		em.close();
	}

	
}
