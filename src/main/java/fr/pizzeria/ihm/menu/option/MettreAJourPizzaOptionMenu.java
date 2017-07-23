package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class MettreAJourPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	
	private IPizzaDao dao;
	private Scanner sc;
	
	public MettreAJourPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		super();
		this.dao = pizzaDao;
		this.sc = scanner;
	}

	@Override
	public String getLibelle() {
		return "3. Mettre à jour une pizza";
	}

	@Override
	public boolean execute() {

		boolean saisieCorrecte = false;

		do {
			LOG.info("Veuillez choisir le code de la pizza à modifier (en majuscules)");
			userChoice = sc.nextLine();

			trouve = dao.findByCode(userChoice);
			if (!trouve) {
				LOG.info("Le code " + userChoice + " n'existe pas");
			}

		} while (!trouve);

		String nouveauCode = "";
		do {
			LOG.info("Veuillez saisir le code");
			nouveauCode = sc.nextLine();

			if (!nouveauCode.matches("^[A-Z]{3}$")) {
				LOG.info("Veuillez saisir un code valide");
			}

			saisieCorrecte = true;

		} while (!saisieCorrecte);

		String nouveauNom = "";
		do {
			LOG.info("Veuillez saisir le nom (sans espace)");
			nouveauNom = sc.nextLine();

			if (nouveauNom.isEmpty()) {
				LOG.info("Veuillez saisir un nom valide");
			}

			saisieCorrecte = true;

		} while (!saisieCorrecte);

		Integer nouveauPrix = 0;
		do {
			try {
				LOG.info("Veuillez saisir le prix");
				String saisie = sc.nextLine().trim();

				if (saisie.isEmpty()) {
					LOG.info("Veuillez saisir un prix valide");
				}
				nouveauPrix = Integer.parseInt(saisie);

				saisieCorrecte = true;
			} catch (NumberFormatException e) {
				LOG.info(e.getMessage());
			}
		} while (!saisieCorrecte);

		Pizza nouvellePizza = new Pizza(nouveauCode, nouveauNom, nouveauPrix);

		try {
			dao.updatePizza(userChoice, nouvellePizza);
		} catch (UpdatePizzaException e) {
			LOG.info(e.getMessage());
		}

		return false;
	}
}
