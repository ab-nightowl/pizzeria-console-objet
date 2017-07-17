package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);

	public ListerPizzasOptionMenu(PizzaDaoMemoire dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return "1. Lister les pizzas";
	}

	@Override
	public boolean execute() {
		Pizza[] carteDesPizzas = dao.findAllPizzas();
		ListerPizzasOptionMenu.afficherCarte(carteDesPizzas);
		return false;
	}

	public static void afficherCarte(Pizza[] carteDesPizzas) {
		for (Pizza pizza : carteDesPizzas) {
			if (pizza != null) {
				LOG.info(pizza.getCode() + " -> " + pizza.getNom() + " (" + pizza.getPrix() + ")");
			}
		}
	}
}
