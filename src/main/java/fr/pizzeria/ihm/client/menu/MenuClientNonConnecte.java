package fr.pizzeria.ihm.client.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.ihm.client.menu.option.OptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.SeConnecterOptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.SinscrireOptionMenuClient;
import fr.pizzeria.ihm.client.menu.option.SortirOptionMenuClient;
import fr.pizzeria.model.Client;

public class MenuClientNonConnecte extends MenuClient {

	private static final Logger LOG = LoggerFactory.getLogger(MenuClient.class);
	private static final int NUMERO_OPTION_SORTIE = 99;
	
	private Scanner sc;
	private Client client;
	
	private SinscrireOptionMenuClient sInscrire;
	private SeConnecterOptionMenuClient seConnecter;
	private SortirOptionMenuClient sortir;

	private Map<Integer, OptionMenuClient> actions = new HashMap<>();
	
	public MenuClientNonConnecte(IClientDao clientDao, Scanner scanner, ICommandeDao commandeDao, Client client) {
		super();
		this.sc = scanner;
		this.client = client;
		
		this.sInscrire = new SinscrireOptionMenuClient(clientDao, scanner);
		this.seConnecter = new SeConnecterOptionMenuClient(clientDao, scanner, commandeDao);
		this.sortir = new SortirOptionMenuClient();

		actions.put(1, sInscrire);
		actions.put(2, seConnecter);
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
