package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.model.Pizza;

public class MettreAJourPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);

	public MettreAJourPizzaOptionMenu(PizzaDaoMemoire dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return "3. Mettre à jour une pizza";
	}

	@Override
	public boolean execute() {

		do {
			LOG.info("Veuillez choisir le code de la pizza à modifier (en majuscules)");
			userChoice = sc.nextLine();

			trouve = dao.findByCode(userChoice);
			if (!trouve) {
				LOG.info("Le code " + userChoice + " n'existe pas");
			}

		} while (!trouve);

		LOG.info("Veuillez saisir le code");
		String nouveauCode = sc.nextLine();

		LOG.info("Veuillez saisir le nom (sans espace)");
		String nouveauNom = sc.nextLine();

		LOG.info("Veuillez saisir le prix");
		Integer nouveauPrix = Integer.parseInt(sc.nextLine());

		Pizza nouvellePizza = new Pizza(nouveauCode, nouveauNom, nouveauPrix);

		dao.updatePizza(userChoice, nouvellePizza);

		return false;
	}
}
