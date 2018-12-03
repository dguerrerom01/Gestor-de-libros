package modelo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Libro implements Serializable {

	private String isbn;
	private String titulo;
	private String autor;
	private String tema;
	private String editorial;
	private int paginas;
	private int ejemplares;
	private String formato;
	private String estado;
	
	// CONSTRUCTOR
	
	public Libro() {
	}
	
	public Libro(HashMap<String, Object> parametros) {
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

	// GETTERS AND SETTERS
	
	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}
	
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
