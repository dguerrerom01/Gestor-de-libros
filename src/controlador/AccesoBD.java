package controlador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import modelo.Libro;

public class AccesoBD {
	private String usuario = "root";
	private String password = "";
	private String nombreDB = "jdbc:mysql://localhost/libreria";
	private String driver = "com.mysql.jdbc.Driver";
	
	private static Connection connection = null;
	
	private Connection abrirConnection(){
		if (connection == null) {
			try {
				Runtime.getRuntime().addShutdownHook(new CerrarConexion());
				Class.forName(driver);
				connection = DriverManager.getConnection(nombreDB, usuario, password);
				connection.setAutoCommit(false);
				System.out.println("Abriendo conexion...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public boolean almacena(String sql) {
		abrirConnection();
		int resultado;
		try {
			resultado = connection.prepareStatement(sql).executeUpdate();
			return commit(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public HashMap<String, Object> recuperar(String sql)
			throws IllegalArgumentException, IllegalAccessException, SecurityException, SQLException {
		abrirConnection();
		CachedRowSet cachedRowSet = null;
		cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(AccesoBD.connection.prepareStatement(sql).executeQuery());
		cachedRowSet.next();
		return crearLibro(cachedRowSet);
	}
	
	private HashMap<String, Object> crearLibro(ResultSet resultSet)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		abrirConnection();
		HashMap<String, Object> datosLibro = new HashMap<>();
		try {
			for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
				datosLibro.put(resultSet.getMetaData().getColumnName(i).toString(), resultSet.getObject(i));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return datosLibro;
	}
	
	public ArrayList<Libro> obtenerLibros(String sql){
		abrirConnection();
		try {
			ArrayList<Libro> resultado = new ArrayList<>();
			CachedRowSet cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.populate(connection.prepareStatement(sql).executeQuery());
			while(cachedRowSet.next()) {
				Libro libro = new Libro();
				libro.setIsbn(cachedRowSet.getString("isbn"));
				libro.setTitulo(cachedRowSet.getString("titulo"));
				libro.setAutor(cachedRowSet.getString("autor"));
				libro.setEditorial(cachedRowSet.getString("editorial"));
				libro.setNumPaginas(Integer.valueOf(cachedRowSet.getString("numPaginas")));
				libro.setEjemplares(Integer.valueOf(cachedRowSet.getString("ejemplares")));
				libro.setTema(cachedRowSet.getString("tema"));
				libro.setFormato(cachedRowSet.getString("formato"));
				libro.setEstado(cachedRowSet.getString("estado"));
				resultado.add(libro);
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean commit(int resultado) {
		if (resultado == 1) {
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
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


