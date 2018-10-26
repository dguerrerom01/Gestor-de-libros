package modelo;

import java.io.Serializable;

public class Libro implements Serializable {

	private String isbn;
	private String titulo;
	private String autor;
	private String tema;
	private String editorial;
	private int posicionTema;
	private int numPaginas;
	private int ejemplares;
	private Boolean[] formato = new Boolean[4];
	private Boolean[] estado = new Boolean[2];

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

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Boolean getFormato(int posicion) {
		return formato[posicion];
	}

	public void setFormato(int posicion, Boolean formato) {
		this.formato[posicion] = formato;
	}

	public Boolean getEstado(int posicion) {
		return estado[posicion];
	}

	public void setEstado(int posicion, Boolean estado) {
		this.estado[posicion] = estado;
	}

	public int getPosicionTema() {
		return posicionTema;
	}

	public void setPosicionTema(int locTema) {
		this.posicionTema = locTema;
	}
	
}
