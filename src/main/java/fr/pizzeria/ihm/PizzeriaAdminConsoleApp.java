package fr.pizzeria.ihm;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	public static void main(String[] args) {
		List<Pizza> pizzas = creerListPizzas();
		
		LOG.debug("Création d'une instance de PizzaDaoMémoire");
		PizzaDaoMemoire dao = new PizzaDaoMemoire();
		
		LOG.info("Initialisation des pizzas...");
		dao.initPizzas(pizzas);
		LOG.info("...pizzas initialisées");
		
		LOG.debug("Création du menu de bienvenue");
		Menu menu = new Menu(dao);
		
		menu.manage();
	}

	private static List<Pizza> creerListPizzas() {
		LOG.debug("Création d'une liste de pizzas");
		
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REI", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
		return pizzas;
	}

}
