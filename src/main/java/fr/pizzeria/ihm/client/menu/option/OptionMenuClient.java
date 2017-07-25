package fr.pizzeria.ihm.client.menu.option;

public abstract class OptionMenuClient {

	protected String userChoice;
	boolean trouve;
	
	public OptionMenuClient() {
		this.userChoice = "";
		this.trouve = false;
	}
	
	public abstract String getLibelle();
	
	public abstract boolean execute();
	
}
