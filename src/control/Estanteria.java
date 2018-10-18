package control;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.Libro;

public class Estanteria {

	private ArrayList<Libro> libros;
	
	public ArrayList<Libro> getLibros() {
		return libros;
	}

//	private int maxReg = 5;
//	private int reg = 0;

	public Estanteria() {
		libros = new ArrayList<>();
	}

	// Metodos
	
	/**
	 * Inserta el libro en la coleccion de libros
	 * @param libro el libro a insertar
	 * @return TRUE si se ha agregado, FALSE si no lo ha hecho
	 */
	public boolean insertarLibro(Libro libro) {
		return libros.add(libro);
	}
	
	public boolean editarLibro(Libro libro) {
		for (int i = 0; i < libros.size(); i++) {
			if (libros.get(i).getIsbn().equals(libro.getIsbn())) {
				libros.set(i, libro);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina el libro de la coleccion
	 * @param libro el libro a eliminar
	 * @return TRUE si se ha eliminado, FALSE si no lo ha hecho
	 */
	public boolean borrarLibro(Libro libro) {
		return libros.remove(libro);
	}
	
	/**
	 * Envia los datos del libro requerido
	 * @param isbn El ISBN del libro requerido
	 * @return LIBRO encontrado, NULL si no existe
	 */
	public Libro buscarLibro(String isbn) {
		for (Libro libro : libros) {
			if (libro.getIsbn().equals(isbn)) {
				return libro;
			}
		}
		return null;
	}
	
	@Deprecated
	/**
	 * Edita el contenido de libro
	 * @param libroNuevo
	 * @param isbn
	 */
	public void editarLibro(Libro libroNuevo, String isbn) {
		// TODO simplificar
		int posicion = 0;
		for (Libro libro : libros) {
			if (libro.getIsbn().equals(isbn)) {
				libros.set(posicion, libroNuevo);
			}
			posicion++;
		}
	}

	/**
	 * Elimina el libro de la coleccion
	 * @param libro el libro a eliminar
	 * @return TRUE si se ha eliminado, FALSE si no lo ha hecho
	 */
	@Deprecated
	public boolean borrarLibro(String isbn) {
		return libros.remove(buscarLibro(isbn));
	}
	
	
	@Deprecated
	/**
	 * Comprueba si el libro que va a insertarse ya existe
	 * @param isbn El ISBN del libro a comprobar
	 * @return FALSE si no lo encuent
	 */
	public boolean comprobarLibro(String isbn) {
		for (Libro libro : libros) {
			if (libro.getIsbn().equals(isbn)) {
				return true;
			}
		}
		return false;
	}
	
}

