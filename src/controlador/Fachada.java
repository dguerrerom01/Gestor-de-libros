package controlador;

import java.util.ArrayList;

import modelo.Libro;
import modelo.Tema;

public class Fachada {
	
	AccionesLibro accionesLibro;
	AccionesTema accionesTema;
	
	public Fachada() {
		accionesLibro = new AccionesLibro();
		accionesTema = new AccionesTema();
	}
	
	public boolean insertar(Libro libro) {
		return accionesLibro.insertarLibro(libro);
	}
	
	public boolean insertar(Tema tema) {
		return accionesTema.insertarTema(tema);
	}
	
	public boolean borrar(Libro libro) {
		return accionesLibro.borrarLibro(libro);
	}
	
	public boolean borrar(Tema tema) {
		return accionesTema.borrarTema(tema);
	}
	
	public boolean editar(Libro libro) {
		return accionesLibro.editarLibro(libro);
	}
	
	public Libro buscar(String isbn) {
		return accionesLibro.buscarLibro(isbn);
	}
	
	public ArrayList<Libro> obtenerLibros() {
		return accionesLibro.getLibros();
	}
	
	public ArrayList<Tema> obtenerTemas() {
		return accionesTema.getTemas();
	}
	
}
