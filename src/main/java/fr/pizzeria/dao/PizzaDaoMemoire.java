package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoire implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);
	
	private List<Pizza> pizzas;
	
	/**
	 * initPizzas:
	 * initialise une liste de pizzas en mémoire cache
	 */
	@Override
	public void initPizzas() {
		LOG.info("Initialisation des pizzas...");
		
		LOG.debug("Création d'une liste de 8 pizzas");
		pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REI", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
		LOG.info("...pizzas initialisées");
	}

	@Override
	public List<Pizza> findAllPizzas() {
		LOG.debug("Récupération des pizzas");
		return new ArrayList<Pizza>(pizzas);
	}
	
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		LOG.debug("Sauvegarde de la pizza {}", pizza);
		pizzas.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizzaAJour) throws UpdatePizzaException {
		LOG.debug("Mise à jour de la pizza {}", pizzaAJour);

		Optional<Pizza> pizzaTrouve = pizzas.stream().filter(p -> p.getCode().equals(codePizza)).findAny();
		if (pizzaTrouve.isPresent()) {
			pizzas.remove(pizzaTrouve.get());
			pizzas.add(pizzaAJour);
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		LOG.debug("Suppression de la pizza {}", codePizza);

		Optional<Pizza> pizzaTrouve = pizzas.stream().filter(p -> p.getCode().equals(codePizza)).findAny();
		if (pizzaTrouve.isPresent()) {
			pizzas.remove(pizzaTrouve.get());
		}
	}

	public boolean findByCode(String codePizza) {
		LOG.debug("Recherche de la pizza avec le code {}", codePizza);
		for (Pizza pizza : pizzas) {
			if (pizza != null) {
				String codeCourant = pizza.getCode();

				if (codePizza.equals(codeCourant)) {
					return true;
				}
			}
		}
		return false;
	}
}
