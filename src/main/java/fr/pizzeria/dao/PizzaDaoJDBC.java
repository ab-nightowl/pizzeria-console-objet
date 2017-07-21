package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoireTest.class);

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		String allPizzas = "SELECT * FROM pizza";
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			PreparedStatement findAllPizzaSt = conn.prepareStatement(allPizzas);
			ResultSet result = findAllPizzaSt.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				String code = result.getString("code");
				String nom = result.getString("nom");
				Double prix = result.getDouble("prix");
				pizzas.add(new Pizza(code, nom, prix));
			}
		} catch (SQLException e) {
			// TODO retourner une exception pizza
			LOG.error("Une erreur s'est produite.", e);
		}
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		String sql = "INSERT INTO pizza (code, nom, prix) VALUES (?, ?, ?)";
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			PreparedStatement savePizzaSt = conn.prepareStatement(sql);
			savePizzaSt.setString(1, "PEP");
			savePizzaSt.setString(2, "Pépéroni");
			savePizzaSt.setDouble(3, 12.50f);
			savePizzaSt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error("Une erreur s'est produite.", e);
		}
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		// TODO Auto-generated method stub

	}

}
