package fr.pizzeria.ihm.admin;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.admin.menu.Menu;

public class PizzeriaAdminConsoleApp {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	public static void main(String[] args) {
		
		LOG.debug("Création d'une instance de PizzaDaoJpa de type IPizzaDao");
		IPizzaDao pizzaDao = new PizzaDaoJpa();
		
		LOG.debug("La methode initPizza est invoquée");
		pizzaDao.initPizzas();
		
		try (Scanner scanner = new Scanner(System.in)) {
			LOG.debug("Création d'une instance de Menu");
			Menu menu = new Menu(pizzaDao, scanner);
			
			LOG.debug("La methode manage est invoquée");
			menu.manage();
		}
	}

}
