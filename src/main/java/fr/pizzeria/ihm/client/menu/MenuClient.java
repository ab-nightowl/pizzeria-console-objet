package fr.pizzeria.ihm.client.menu;

public abstract class MenuClient {

	protected String userChoice;
	boolean trouve;
	
	public MenuClient() {
		this.userChoice = "";
		this.trouve = false;
	}
	
	public abstract void manage();

	public abstract void afficher();

}
