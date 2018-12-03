package controlador;

import java.sql.*;
import java.util.*;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class AccesoBD {
	
	private Connection connection = null;
	
	public AccesoBD() {
		try {
			
			connection = ConexionJDBC.getConexion();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarTransaccion() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean aceptarTransaccion() {
		try {
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void cancelarTransaccion() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean almacena(String sql) {
		iniciarTransaccion();
		try {
			connection.prepareStatement(sql).executeUpdate();
			return aceptarTransaccion();
		} catch (Exception e) {
			e.printStackTrace();
			cancelarTransaccion();
			return false;
		}
	}

	public ArrayList<HashMap<String, Object>> recuperar(String sql)
			throws IllegalArgumentException, IllegalAccessException, SecurityException, SQLException {
		CachedRowSet cachedRowSet = null;
		cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(connection.prepareStatement(sql).executeQuery());
		return leerCachedRowSet(cachedRowSet);
	}
	
	private ArrayList<HashMap<String, Object>> leerCachedRowSet(CachedRowSet cachedRowSet)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		ArrayList<HashMap<String, Object>> datos = new ArrayList<HashMap<String, Object>>();
		try {
			while(cachedRowSet.next()) {
				HashMap<String, Object> hashMap = new HashMap<>();
				for (int i = 1; i < cachedRowSet.getMetaData().getColumnCount() + 1; i++) {
					hashMap.put(cachedRowSet.getMetaData().getColumnName(i).toString(), cachedRowSet.getObject(i));
				}
				datos.add(hashMap);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return datos;
	}
}


