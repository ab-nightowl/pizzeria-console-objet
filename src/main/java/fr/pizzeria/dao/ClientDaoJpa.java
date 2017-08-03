package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {

	@Override
	public void saveNewClient(Client client) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Client findByEmail(String userChoice) throws Exception {
		EntityManager em = emf.createEntityManager();
		String sql = "select c from Client c where c.email=:userChoice";
		TypedQuery<Client> query = em.createQuery(sql, Client.class)
				.setParameter("userChoice", userChoice);
		Client client = query.getResultList().get(0);
		em.close();

		return client;
	}

	
}
