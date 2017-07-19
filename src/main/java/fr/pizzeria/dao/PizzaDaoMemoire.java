package fr.pizzeria.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoire implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);

	private Pizza[] carteDesPizzas;

	public PizzaDaoMemoire() {
		carteDesPizzas = new Pizza[100];
		carteDesPizzas[0] = new Pizza("PEP", "Pépéroni", 12.50);
		carteDesPizzas[1] = new Pizza("MAR", "Margherita", 14.00);
		carteDesPizzas[2] = new Pizza("REI", "La Reine", 11.50);
		carteDesPizzas[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		carteDesPizzas[4] = new Pizza("CAN", "La Cannibale", 12.50);
		carteDesPizzas[5] = new Pizza("SAV", "La Savoyarde", 13.00);
		carteDesPizzas[6] = new Pizza("ORI", "L'Orientale", 13.50);
		carteDesPizzas[7] = new Pizza("IND", "L'Indienne", 14.00);

	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		LOG.debug("Sauvegarde de la pizza {}", pizza);
		int nouvelIndex = this.findNewId();
		pizza.setId(nouvelIndex);
		carteDesPizzas[nouvelIndex] = pizza;
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizzaAJour) throws UpdatePizzaException {
		LOG.debug("Mise à jour de la pizza {}", pizzaAJour);
		for (Integer i = 0; i < carteDesPizzas.length; i++) {
			if (carteDesPizzas[i] != null) {
				String codeCourant = carteDesPizzas[i].getCode();

				if (codePizza.equals(codeCourant)) {
					pizzaAJour.setId(i);
					carteDesPizzas[i] = pizzaAJour;
				}
			}
		}

		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		LOG.debug("Suppression de la pizza {}", codePizza);
		for (Integer i = 0; i < carteDesPizzas.length; i++) {
			if (carteDesPizzas[i] != null) {
				String codeCourant = carteDesPizzas[i].getCode();

				if (codePizza.equals(codeCourant)) {
					carteDesPizzas[i] = null;
				}
			}
		}

		return false;
	}

	@Override
	public Pizza[] findAllPizzas() {
		LOG.debug("Recherche de toutes les pizzas");
		return carteDesPizzas;
	}

	public int findNewId() {
		LOG.debug("Recherche d'un nouvel id (premier id disponible");

		for (int i = 0; i < carteDesPizzas.length; i++) {
			if (carteDesPizzas[i] == null) {
				return i;
			}
		}

		return 0;
	}

	public boolean findByCode(String codePizza) {
		LOG.debug("Recherche de la pizza avec le code {}", codePizza);
		for (Integer i = 0; i < carteDesPizzas.length; i++) {
			if (carteDesPizzas[i] != null) {
				String codeCourant = carteDesPizzas[i].getCode();

				if (codePizza.equals(codeCourant)) {
					return true;
				}
			}
		}
		return false;
	}
}
