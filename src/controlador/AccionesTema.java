package controlador;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Tema;

public class AccionesTema {
	
	AccesoBD accesoBD = new AccesoBD();
	
	private ArrayList<Tema> temas;
	
	public ArrayList<Tema> getTemas() {
		cargarBD();
		return temas;
	}
	
	public AccionesTema() {
		cargarBD();
	}
	
	private void cargarBD() {
		try {
			ArrayList<HashMap<String, Object>> datos = null;
			datos = accesoBD.recuperar("SELECT * FROM tema ORDER BY tema");
			temas.clear();
			for (HashMap<String, Object> hashMap : datos) {
				temas.add(new Tema(hashMap));
			}
		} catch (Exception e) {
			this.temas = new ArrayList<>();
		}
	}
	
	public boolean insertarTema(Tema tema) {
		try {
			return accesoBD.almacena(
					"INSERT INTO `tema`(`tema`) "
							+ "VALUES('" + tema.getTema() + "')");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean borrarTema(Tema tema) {
		try {
			return accesoBD.almacena(
					"DELETE FROM tema WHERE tema='"+tema.getTema()+"'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
