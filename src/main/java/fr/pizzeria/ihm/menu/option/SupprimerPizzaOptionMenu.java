package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.DeletePizzaException;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);
	
	private IPizzaDao dao;
	private Scanner sc;
	
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		super();
		this.dao = pizzaDao;
		this.sc = scanner;
	}

	@Override
	public String getLibelle() {
		return "4. Supprimer une pizza";
	}

	@Override
	public boolean execute() {

		do {
			LOG.info("Veuillez choisir le code de la pizza à supprimer (en majuscules)");
			userChoice = sc.nextLine();

			trouve = dao.findByCode(userChoice);

			if (!trouve) {
				LOG.info("Le code " + userChoice + " n'existe pas");
			}
		} while (!trouve);

		try {
			dao.deletePizza(userChoice);
		} catch (DeletePizzaException e) {
			LOG.info(e.getMessage());
		}

		return false;
	}
}
