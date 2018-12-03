package controlador;

import java.util.ArrayList;

import modelo.Tema;

public class AccionesTema {

	AccesoBD accesoBD;
	
	public ArrayList<Tema> temas;
	
	public ArrayList<Tema> getTemas() {
		cargarBD();
		return temas;
	}
	
	private void cargarBD() {
		this.temas = new ArrayList<>();
	}
	
	public boolean insertarTema(Tema tema) {
		
		return false;
	}
	
	public boolean borrarTema(Tema tema) {
		return false;
	}
	
}
