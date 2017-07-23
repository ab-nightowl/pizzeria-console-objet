package fr.pizzeria.ihm.menu.option;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	
	private IPizzaDao dao;
	
	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		super();
		this.dao = pizzaDao;
	}

	@Override
	public String getLibelle() {
		return "1. Lister les pizzas";
	}

	@Override
	public boolean execute() {
		List<Pizza> pizzas = dao.findAllPizzas();
		ListerPizzasOptionMenu.afficherCarte(pizzas);
		return false;
	}

	public static void afficherCarte(List<Pizza> pizzas) {
		for (Pizza pizza : pizzas) {
			if (pizza != null) {
				LOG.info(pizza.getCode() + " -> " + pizza.getNom() + " (" + pizza.getPrix() + ")");
			}
		}
	}
}
