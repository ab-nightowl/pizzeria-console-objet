package fr.pizzeria.ihm.menu.option;

public abstract class OptionMenu {

	protected String userChoice;
	boolean trouve;
	
	public OptionMenu() {
		this.userChoice = "";
		this.trouve = false;
	}
	
	public abstract String getLibelle();
	
	public abstract boolean execute();
	
}
