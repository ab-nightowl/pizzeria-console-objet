package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJpa.class);

	@Override
	public void initPizzas() {
		LOG.info("Initialisation des pizzas...");

		LOG.debug("Création d'une liste de 8 pizzas");
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REI", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
		
		EntityManager em1 = emf.createEntityManager();
		String sql = "select p from Pizza p";
		TypedQuery<Pizza> query = em1.createQuery(sql, Pizza.class);
		List<Pizza> resultList = query.getResultList();
		em1.close();
		
		if (resultList.isEmpty()) {
			EntityManager em2 = emf.createEntityManager();
			em2.getTransaction().begin();
			for (Pizza pizza : pizzas) {
				em2.persist(pizza);
			}
			em2.getTransaction().commit();
			em2.close();
		}
		
		LOG.info("...pizzas initialisées");
	}

	@Override
	public List<Pizza> findAllPizzas() {
		EntityManager em = emf.createEntityManager();
		String sql = "select p from Pizza p";
		TypedQuery<Pizza> query = em.createQuery(sql, Pizza.class);
		List<Pizza> pizzas = query.getResultList();
		em.close();

		return pizzas;
	}

	@Override
	public boolean findByCode(String userChoice) {
		EntityManager em = emf.createEntityManager();
		String sql = "select p from Pizza p where p.code='userChoice'";
		TypedQuery<Pizza> query = em.createQuery(sql, Pizza.class);
		query.getResultList().get(0);
		em.close();

		return true;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		EntityManager em = emf.createEntityManager();
		String sql = "select p from Pizza p where p.code='codePizza'";
		TypedQuery<Pizza> query = em.createQuery(sql, Pizza.class);
		
		Pizza result = query.getSingleResult();
		
		if (result != null) {
			result.setCode(pizza.getCode());
			result.setNom(pizza.getNom());
			result.setPrix(pizza.getPrix());
			em.merge(pizza);
		}
		em.close();
		
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		// TODO Auto-generated method stub

	}
}
