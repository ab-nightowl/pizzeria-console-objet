package fr.pizzeria.ihm.client.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.ihm.client.menu.option.CommanderPizzaOptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.ListerCommandesOptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.OptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.SortirOptionMenuClient;
import fr.pizzeria.model.Client;

public class MenuClientConnecte extends MenuClient {

	private static final Logger LOG = LoggerFactory.getLogger(MenuClient.class);
	private static final int NUMERO_OPTION_SORTIE = 99;
	
	private Scanner sc;
	
	private Client currentClient;
	
	private CommanderPizzaOptionMenuClient commanderPizza;
	private ListerCommandesOptionMenuClient listerPizzas;
	private SortirOptionMenuClient sortir;

	private Map<Integer, OptionMenuClient> actions = new HashMap<>();
	
	public MenuClientConnecte(ICommandeDao commandeDao, Scanner scanner, Client currentClient) {
		super();
		this.sc = scanner;
		this.currentClient = currentClient;
		
		this.commanderPizza = new CommanderPizzaOptionMenuClient(commandeDao, scanner, currentClient);
		this.listerPizzas = new ListerCommandesOptionMenuClient(commandeDao, scanner);
		this.sortir = new SortirOptionMenuClient();

		actions.put(1, commanderPizza);
		actions.put(2, listerPizzas);
		actions.put(99, sortir);
	}

	@Override
	public void manage() {
		this.afficher();
	}

	@Override
	public void afficher() {
		int saisie = NUMERO_OPTION_SORTIE;

		// Afficher le menu tant qu'on ne sort pas (ie : response = 99)
		do {
			// afficher menu
			LOG.info("***** Pizzeria Administration *****");
			actions.forEach((numero, option) -> LOG.info(numero + ". " + option.getLibelle()));

			saisie = sc.nextInt();

			actions.get(saisie).execute();
		} while (saisie != NUMERO_OPTION_SORTIE);
	}
	
}
