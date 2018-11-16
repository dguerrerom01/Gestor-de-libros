package controlador;

import java.sql.*;
import java.util.*;
import modelo.Libro;

public class AccesoBD {
	private String usuario = "root";
	private String password = "";
	private String nombreDB = "jdbc:mysql://localhost/libreria";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection connection = null;
	
	private Connection abrirConnection(){
		if (connection == null) {
			try {
				Runtime.getRuntime().addShutdownHook(new CerrarConexion());
				Class.forName(driver);
				connection = DriverManager.getConnection(nombreDB, usuario, password);
//				connection.setAutoCommit(false);
				System.out.println("Abriendo conexion...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public boolean almacena(String sql) {
		abrirConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Libro> recuperar(String sql){
		abrirConnection();
		try {
			Statement statement = connection.createStatement();
			ArrayList<Libro> resultado = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Libro libro = new Libro();
				libro.setIsbn(resultSet.getString("ISBN"));
				libro.setTitulo(resultSet.getString("Titulo"));
				libro.setAutor(resultSet.getString("Autor"));
				libro.setEditorial(resultSet.getString("Editorial"));
				libro.setNumPaginas(Integer.valueOf(resultSet.getString("Paginas")));
				libro.setEjemplares(Integer.valueOf(resultSet.getString("Ejemplares")));
				libro.setTema(resultSet.getString("Tema"));
				libro.setFormato(resultSet.getString("Formato"));
				libro.setEstado(resultSet.getString("Estado"));
				resultado.add(libro);
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	class CerrarConexion extends Thread {
		@Override
		public void run() {
			try {
				abrirConnection().close();
				System.out.println("... cerrando conexion");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


