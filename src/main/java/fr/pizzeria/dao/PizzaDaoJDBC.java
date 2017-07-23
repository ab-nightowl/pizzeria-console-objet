package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);
	
	private List<Pizza> pizzas;
	
	private String driverClass;
	private String url;
	private String user;
	private String password;

	public PizzaDaoJDBC(String driverClass, String url, String user, String password) {
		super();
		this.driverClass = driverClass;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public void initPizzas() {
		LOG.info("Initialisation des pizzas...");
		
		LOG.debug("Création d'une liste de 8 pizzas");
		pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REI", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sql = "INSERT INTO pizza(code, nom, prix) VALUES (?, ?, ?);";
			
			for (Pizza pizza : pizzas) {
				PreparedStatement insertPizzaSt = conn.prepareStatement(sql);
				insertPizzaSt.setString(1, pizza.getCode());
				insertPizzaSt.setString(2, pizza.getNom());
				insertPizzaSt.setDouble(3, pizza.getPrix());
				insertPizzaSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
		LOG.info("...pizzas initialisées");
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT * FROM pizza";
			PreparedStatement findAllPizzaSt = conn.prepareStatement(sql);
			ResultSet result = findAllPizzaSt.executeQuery();

			while (result.next()) {
				String code = result.getString("code");
				String nom = result.getString("nom");
				Double prix = result.getDouble("prix");
				pizzas.add(new Pizza(code, nom, prix));
			}
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
		return new ArrayList<Pizza>(pizzas);
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sql = "INSERT INTO pizza (code, nom, prix) VALUES (?, ?, ?)";
			
			PreparedStatement savePizzaSt = conn.prepareStatement(sql);
			savePizzaSt.setString(1, pizza.getCode());
			savePizzaSt.setString(2, pizza.getNom());
			savePizzaSt.setDouble(3, pizza.getPrix());
			savePizzaSt.executeUpdate();
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlSelect = "SELECT * FROM pizza WHERE code = ?";
			PreparedStatement findByCodeSt = conn.prepareStatement(sqlSelect);
			findByCodeSt.setString(1, codePizza);
			findByCodeSt.executeQuery();
			
			String sqlUpdate = "UPDATE pizza SET code = 'PIZ', nom = 'Pizza', prix = 10 WHERE code = ?";
			PreparedStatement updatePizzaSt = conn.prepareStatement(sqlUpdate);
			updatePizzaSt.setString(1, pizza.getCode());
			updatePizzaSt.setString(2, pizza.getNom());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		Connection conn;

		try {
			conn = DriverManager.getConnection(url, user, password);

			String sql = "DELETE FROM WHERE code = ?";
			PreparedStatement deletePizzaSt = conn.prepareStatement(sql);
			deletePizzaSt.setString(1, codePizza);
			deletePizzaSt.executeUpdate();
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
	}

	@Override
	public boolean findByCode(String codePizza) {
		Connection conn;
		
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlSelect = "SELECT * FROM pizza WHERE code = ?";
			PreparedStatement findByCodeSt = conn.prepareStatement(sqlSelect);
			findByCodeSt.setString(1, codePizza);
			ResultSet result = findByCodeSt.executeQuery();
			
			while (result.next()) {
				Integer id = result.getInt("id");
				String code = result.getString("code");
				String nom = result.getString("nom");
				Double prix = result.getDouble("prix");
				LOG.info("[id=" + id + " code=" + code + " nom=" + nom + " prix=" + prix + "]");
			}
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
		return false;
	}

}
