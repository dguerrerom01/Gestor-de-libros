package controlador;

import java.util.ArrayList;

import modelo.Libro;

public class Fachada {
	
	AccionesLibro estanteria;
	
	public Fachada() {
		estanteria = new AccionesLibro();
	}
	
	public boolean insertarLibro(Libro libro) {
		return estanteria.insertarLibro(libro);
	}
	
	public boolean borrarLibro(Libro libro) {
		return estanteria.borrarLibro(libro);
	}
	
	public boolean editarLibro(Libro libro) {
		return estanteria.editarLibro(libro);
	}
	
	public Libro buscarLibro(String isbn) {
		return estanteria.buscarLibro(isbn);
	}
	
	public ArrayList<Libro> obtenerLibros() {
		return estanteria.getLibros();
	}
	
}
