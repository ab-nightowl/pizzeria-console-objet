package fr.pizzeria.ihm.client.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.ihm.client.menu.MenuClient;
import fr.pizzeria.ihm.client.menu.MenuClientConnecte;
import fr.pizzeria.model.Client;

public class SeConnecterOptionMenuClient extends OptionMenuClient {

	private static final Logger LOG = LoggerFactory.getLogger(SeConnecterOptionMenuClient.class);
	
	private IClientDao clientDao;
	private Scanner sc;
	private Client client;

	public SeConnecterOptionMenuClient(IClientDao clientDao, Scanner scanner, Client client) {
		super();
		this.clientDao = clientDao;
		this.sc = scanner;
		this.client = client;
	}

	@Override
	public String getLibelle() {
		return "Se connecter";
	}

	@Override
	public boolean execute() {
		boolean saisieCorrecte = false;

		String email = "";
		do {
			LOG.info("Veuillez saisir votre email");
			email = sc.next().trim();

			if (email.isEmpty()) {
				LOG.info("Veuillez saisir un email svp");
			}

			saisieCorrecte = true;
		} while (!saisieCorrecte);

		String motDePasse = "";
		do {
			LOG.info("Veuillez saisir votre mot de passe");
			motDePasse = sc.next().trim();

			if (motDePasse.isEmpty()) {
				LOG.info("Veuillez saisir un mot de passe svp");
			}

			saisieCorrecte = true;
		} while (!saisieCorrecte);

		try {
			clientDao.findByEmail(email);
			CommandeDaoJpa commandeDao = new CommandeDaoJpa();
			MenuClient menuConnecte = new MenuClientConnecte(commandeDao, sc, client);
			menuConnecte.manage();
			
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}

		return false;
	}

}
