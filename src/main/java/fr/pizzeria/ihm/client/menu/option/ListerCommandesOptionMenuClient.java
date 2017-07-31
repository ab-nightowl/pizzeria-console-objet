package fr.pizzeria.ihm.client.menu.option;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class ListerCommandesOptionMenuClient extends OptionMenuClient {

	private static final Logger LOG = LoggerFactory.getLogger(ListerCommandesOptionMenuClient.class);

	private ICommandeDao commandeDao;
	private Scanner sc;
	private Client currentClient;

	public ListerCommandesOptionMenuClient(ICommandeDao commandeDao, Scanner scanner, Client currentClient) {
		super();
		this.commandeDao = commandeDao;
		this.sc = scanner;
		this.currentClient = currentClient;
	}

	@Override
	public String getLibelle() {
		return "Lister ses commandes";
	}

	@Override
	public boolean execute() {
		List<Commande> commandes = commandeDao.findAllCommandes(currentClient);
		ListerCommandesOptionMenuClient.afficher(commandes);
		return false;
	}

	public static void afficher(List<Commande> commandes) {
		Integer i = 0;
		for (Commande cmd : commandes) {
			if (cmd != null) {
				i += 1 ;
				String nb = String.valueOf(i);
				
//				TO DO: récupérer le nom des pizzas commandées à partir de la table commandes_pizzas
				
				String statut = cmd.getStatut();
				String ref = cmd.getNumeroCommande();
				
				StringBuilder stringBuilder = new StringBuilder();
				String commande = stringBuilder.append(nb).append(". ").append("nom pizza").append(" - Statut: ").append(statut).append(" - Ref. ").append(ref).toString();
				
				LOG.info(commande);
			}
		}
	}

}
