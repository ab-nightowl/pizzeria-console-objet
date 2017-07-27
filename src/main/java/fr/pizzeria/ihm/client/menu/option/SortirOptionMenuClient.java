package fr.pizzeria.ihm.client.menu.option;

public class SortirOptionMenuClient extends OptionMenuClient {

	public SortirOptionMenuClient() {
		super();
	}

	@Override
	public String getLibelle() {
		return "Sortir";
	}

	@Override
	public boolean execute() {
		System.exit(0);
		return false;
	}

}
