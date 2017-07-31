package fr.pizzeria.ihm.client.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.model.Client;

public class SinscrireOptionMenuClient extends OptionMenuClient {

	private static final Logger LOG = LoggerFactory.getLogger(SinscrireOptionMenuClient.class);
	
	private IClientDao dao;
	private Scanner sc;

	public SinscrireOptionMenuClient(IClientDao clientDao, Scanner scanner) {
		super();
		this.dao = clientDao;
		this.sc = scanner;
	}

	@Override
	public String getLibelle() {
		return "S'inscrire";
	}

	@Override
	public boolean execute() {
		boolean saisieCorrecte = false;

		String nom = "";
		do {
			LOG.info("Veuillez saisir votre nom");
			nom = sc.next().trim();

			if (nom.isEmpty()) {
				LOG.info("Veuillez saisir un nom svp");
			}

			saisieCorrecte = true;
		} while (!saisieCorrecte);

		String prenom = "";
		do {
			LOG.info("Veuillez saisir votre prénom");
			prenom = sc.next().trim();

			if (prenom.isEmpty()) {
				LOG.info("Veuillez saisir un prénom svp");
			}

			saisieCorrecte = true;
		} while (!saisieCorrecte);


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

		Client client = new Client(nom, prenom, email, motDePasse);

		try {
			dao.saveNewClient(client);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return false;
	}

	
}
