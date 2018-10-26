package controlador;

import java.util.*;

import modelo.Libro;

public class Estanteria {

	private ArrayList<Libro> libros;
	private AlmacenUnico almacen;
	
	public ArrayList<Libro> getLibros() {
		return libros;
	}
	
	public Estanteria() {
		this.almacen = new AlmacenUnico("libros.dat");
		iniciarFichero(); 
	}
	
	private void iniciarFichero() {
		try{
			leerEstanteria();
		}catch (Exception e) {
			this.libros = new ArrayList<>();
			almacen.almacena(this.libros);
		}
	}

	private void leerEstanteria() {
		this.libros = (ArrayList<Libro>) almacen.recuperar();
	}
	
	/**
	 * Inserta el libro en la coleccion de libros
	 * @param libro el libro a insertar
	 * @return TRUE si se ha agregado, FALSE si no lo ha hecho
	 */
	public boolean insertarLibro(Libro libro) {
		leerEstanteria();
		if (libros.add(libro)) {
			almacen.almacena(libros);
			return true;
		}
		return false;
	}

	/**
	 * Elimina el libro de la coleccion
	 * @param libro el libro a eliminar
	 * @return TRUE si se ha eliminado, FALSE si no lo ha hecho
	 */
	public boolean borrarLibro(Libro libro) {
		if (libros.remove(libro)) {
			almacen.almacena(this.libros);
			return true;
		}
		return false;
	}
	
	/**
	 * Edita el contenido del libro
	 * @param libroNuevo
	 */
	public boolean editarLibro(Libro libro) {
		for (int i = 0; i < libros.size(); i++) {
			if (libros.get(i).getIsbn().equals(libro.getIsbn())) {
				libros.set(i, libro);
				almacen.almacena(this.libros);
				return true;
			}
		}
		return false;
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
	
}

