package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;

public class CommandeDaoJpa implements ICommandeDao {

	@Override
	public List<Commande> findAllCommandes(Client currentClient) {
		EntityManager em = emf.createEntityManager();
		String sql = "select c from Commande c where c.client_id=:currentClient";
		TypedQuery<Commande> query = em.createQuery(sql, Commande.class)
				.setParameter("currentClient", currentClient);
		List<Commande> commandes = query.getResultList();
		em.close();

		return commandes;
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
