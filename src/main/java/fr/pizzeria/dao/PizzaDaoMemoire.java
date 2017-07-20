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

	private List<Pizza> pizzas = new ArrayList<>();

	/**
	 * initPizzas:
	 * méthode implémentée de l'interface IPizzaDao
	 * @param listPizzas
	 */
	public void initPizzas(List<Pizza> listPizzas) {}

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

	@Override
	public List<Pizza> findAllPizzas() {
		LOG.debug("Recherche de toutes les pizzas");
		return pizzas;
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
