package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionJDBC {

	private String usuario;
	private String password;
	private String url;
	private String driver;
	
	private final ResourceBundle accesoPropiedades = ResourceBundle.getBundle("cfg");
	
	private static Connection connection = null;

	public static Connection getConexion() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			new ConexionJDBC();
		}
		return connection;
	}

	private ConexionJDBC() throws ClassNotFoundException, SQLException {
		Runtime.getRuntime().addShutdownHook(new CerrarConnection());
		usuario = accesoPropiedades.getString("usuario");
		password = accesoPropiedades.getString("password");
		url = accesoPropiedades.getString("url");
		driver = accesoPropiedades.getString("driver");
		Class.forName(driver);
		connection = DriverManager.getConnection(url, usuario, password);
		System.out.println("Abriendo conexion...");
	}

	/**
	 * Cierra la conexion al cerrar el programa
	 * 
	 * @author david
	 *
	 */
	public class CerrarConnection extends Thread {
		public void run() {
			try {
				if(connection != null)
					connection.close();
					System.out.println("...cerrando conexion");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
	}

}
