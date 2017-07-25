package fr.pizzeria.ihm.client.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.ICommandeDao;

public class ListerCommandesOptionMenuClient extends OptionMenuClient {

	public ListerCommandesOptionMenuClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListerCommandesOptionMenuClient(ICommandeDao commandeDao, Scanner scanner) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return "Lister ses commandes";
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
