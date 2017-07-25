package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {

	private static final Logger LOG = LoggerFactory.getLogger(ClientDaoJpa.class);

	@Override
	public void saveNewClient(Client client) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Client findByEmail(String userChoice) {
		EntityManager em = emf.createEntityManager();
		String sql = "select c from Client c where c.email='userChoice'";
		TypedQuery<Client> query = em.createQuery(sql, Client.class);
		Client client = query.getResultList().get(0);
		em.close();

		return client;
	}

	
}