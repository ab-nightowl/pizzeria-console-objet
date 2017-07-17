package fr.pizzeria.ihm;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;

public class Menu {

	PizzaDaoMemoire dao;
	Scanner sc;
	String userInput;

	ListerPizzasOptionMenu listerPizza;
	NouvellePizzaOptionMenu nouvellePizza;
	MettreAJourPizzaOptionMenu mettreAJourPizza;
	SupprimerPizzaOptionMenu supprimerPizza;
	SortirOptionMenu sortir;

	OptionMenu[] options;

	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);

	public Menu() {
		this.dao = new PizzaDaoMemoire();
		this.sc = new Scanner(System.in);
		this.userInput = "";

		this.listerPizza = new ListerPizzasOptionMenu(dao);
		this.nouvellePizza = new NouvellePizzaOptionMenu(dao);
		this.mettreAJourPizza = new MettreAJourPizzaOptionMenu(dao);
		this.supprimerPizza = new SupprimerPizzaOptionMenu(dao);
		this.sortir = new SortirOptionMenu(dao);

		this.options = new OptionMenu[5];
		this.options[0] = listerPizza;
		this.options[1] = nouvellePizza;
		this.options[2] = mettreAJourPizza;
		this.options[3] = supprimerPizza;
		this.options[4] = sortir;

	}

	public void manage() {
		while (!(userInput.equals("99"))) {
			LOG.info("***** Pizzeria Administration *****");
			Menu.afficher(options);
			userInput = sc.nextLine();

			switch (userInput) {
			case ("1"):
				listerPizza.execute();
				break;

			case ("2"):
				listerPizza.execute();
				nouvellePizza.execute();
				break;

			case ("3"):
				listerPizza.execute();
				mettreAJourPizza.execute();
				break;

			case ("4"):
				listerPizza.execute();
				supprimerPizza.execute();
				break;

			case ("99"):
				sortir.execute();
				break;

			default:
				Menu.afficher(options);
			}
		}
	}

	public static void afficher(OptionMenu[] menu) {
		for (OptionMenu option : menu) {
			LOG.info(option.getLibelle());
		}
	}

}
