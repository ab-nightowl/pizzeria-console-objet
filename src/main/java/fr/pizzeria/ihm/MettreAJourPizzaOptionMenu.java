package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
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
			try {
				LOG.info("Veuillez saisir le code");
				nouveauCode = sc.nextLine();
				
				if (nouveauCode.isEmpty() || nouveauCode.length() != 3 || !nouveauCode.matches("^[A-Z]{3}$")) {
					throw new UpdatePizzaException("Veuillez saisir un code valide");
				}
				
				saisieCorrecte = true;
			} catch (UpdatePizzaException e) {
				LOG.info(e.getMessage());
			}
			
		} while (!saisieCorrecte);

		
		String nouveauNom = "";
		do {
			try {
				LOG.info("Veuillez saisir le nom (sans espace)");
				nouveauNom = sc.nextLine();
				
				if (nouveauNom.isEmpty()) {
					throw new UpdatePizzaException("Veuillez saisir un nom valide");
				}
				
				saisieCorrecte = true;
			} catch (UpdatePizzaException e) {
				LOG.info(e.getMessage());
			}
			
		} while (!saisieCorrecte);

		
		Integer nouveauPrix = 0;
		do {
			try {
				LOG.info("Veuillez saisir le prix");
				String saisie = sc.nextLine().trim();
				
				if (saisie.isEmpty()){
					throw new SavePizzaException("Veuillez saisir un prix valide");
				}
				nouveauPrix = Integer.parseInt(saisie);
				
				saisieCorrecte = true;
			} catch (NumberFormatException e) {
				LOG.info(e.getMessage());
			} catch (SavePizzaException e) {
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
