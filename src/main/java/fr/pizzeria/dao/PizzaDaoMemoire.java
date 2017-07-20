package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoire implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);

	private List<Pizza> carteDesPizzas = new ArrayList<>();

	public PizzaDaoMemoire() {
		carteDesPizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		carteDesPizzas.add(new Pizza("MAR", "Margherita", 14.00));
		carteDesPizzas.add(new Pizza("REI", "La Reine", 11.50));
		carteDesPizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		carteDesPizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		carteDesPizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		carteDesPizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		carteDesPizzas.add(new Pizza("IND", "L'Indienne", 14.00));
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		LOG.debug("Sauvegarde de la pizza {}", pizza);
		carteDesPizzas.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizzaAJour) throws UpdatePizzaException {
		LOG.debug("Mise à jour de la pizza {}", pizzaAJour);

		Optional<Pizza> pizzaTrouve = carteDesPizzas.stream().filter(p -> p.getCode().equals(codePizza)).findAny();
		if (pizzaTrouve.isPresent()) {
			carteDesPizzas.remove(pizzaTrouve.get());
			carteDesPizzas.add(pizzaAJour);
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		LOG.debug("Suppression de la pizza {}", codePizza);

		Optional<Pizza> pizzaTrouve = carteDesPizzas.stream().filter(p -> p.getCode().equals(codePizza)).findAny();
		if (pizzaTrouve.isPresent()) {
			carteDesPizzas.remove(pizzaTrouve.get());
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		LOG.debug("Recherche de toutes les pizzas");
		return carteDesPizzas;
	}

	public boolean findByCode(String codePizza) {
		LOG.debug("Recherche de la pizza avec le code {}", codePizza);
		for (Pizza pizza : carteDesPizzas) {
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
