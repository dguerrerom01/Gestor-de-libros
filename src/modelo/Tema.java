package modelo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Tema implements Serializable {
	
	
	private String tema;
	
	public Tema() {
	}
	
	public Tema(HashMap<String, Object> parametros) {
		Field[] atributos = this.getClass().getDeclaredFields();
		for (int i = 0; i < atributos.length; i++) {
			if (parametros.get(atributos[i].getName()) != null) {
				try {
					atributos[i].set(this, parametros.get(atributos[i].getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
				}
			}
		}
	}

	// GETTERS & SETTERS
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
