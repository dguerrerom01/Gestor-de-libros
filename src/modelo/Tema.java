package modelo;

import java.io.Serializable;

public class Tema implements Serializable {
	String tema;
	
	public Tema() {
		
	}

	// GETTERS & SETTERS
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
