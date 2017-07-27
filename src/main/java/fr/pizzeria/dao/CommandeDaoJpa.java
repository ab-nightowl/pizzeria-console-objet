package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Commande;

public class CommandeDaoJpa implements ICommandeDao {

	private static final Logger LOG = LoggerFactory.getLogger(CommandeDaoJpa.class);

	@Override
	public List<Commande> findAllCommandes() {
		// TODO Auto-generated method stub
		return null;
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
