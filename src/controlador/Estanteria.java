package controlador;

import java.lang.reflect.Array;
import java.util.*;

import modelo.Libro;

public class Estanteria {
	
	AccesoBD accesoBD = new AccesoBD();

	private ArrayList<Libro> libros;
	
	public ArrayList<Libro> getLibros() {
		cargarBD();
		return libros;
	}
	
	public Estanteria() {
		cargarBD();
	}

	private void cargarBD() {
		try {
			this.libros = accesoBD.recuperar("SELECT * FROM libro ORDER BY ISBN");
		} catch (Exception e) {
			this.libros = new ArrayList<>();
		}
	}
	
	/**
	 * Inserta el libro en la coleccion de libros
	 * @param libro el libro a insertar
	 * @return TRUE si se ha agregado, FALSE si no lo ha hecho
	 */
	public boolean insertarLibro(Libro libro) {
		try {
			return accesoBD.almacena(
					"INSERT INTO `libro`(`ISBN`,`Titulo`,`Autor`,`Editorial`,`Paginas`,`Ejemplares`,`Tema`,`Formato`,`Estado`) "
							+ "VALUES('" + libro.getIsbn() + "','" + libro.getTitulo() + "','" + libro.getAutor()
							+ "','" + libro.getEditorial() + "','" + libro.getNumPaginas() + "','"
							+ libro.getEjemplares() + "','"+libro.getTema()+"','"+libro.getFormato()+"','"+libro.getEstado()+"')");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina el libro de la coleccion
	 * @param libro el libro a eliminar
	 * @return TRUE si se ha eliminado, FALSE si no lo ha hecho
	 */
	public boolean borrarLibro(Libro libro) {
		try {
			return accesoBD.almacena(
					"DELETE FROM libro WHERE ISBN='"+libro.getIsbn()+"'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * Edita el contenido del libro
	 * @param libroNuevo
	 */
	public boolean editarLibro(Libro libro) {
		try {
			return accesoBD.almacena(
					"UPDATE libro SET Titulo='" + libro.getTitulo() + "', Autor='" + libro.getAutor() + "', Editorial='"
							+ libro.getEditorial() + "', Paginas='" + libro.getNumPaginas() + "',Ejemplares='"
							+ libro.getEjemplares() + "', Tema='" + libro.getTema()
							+ "',Formato='"+libro.getFormato()+"',Estado='"+libro.getEstado()+"' WHERE ISBN='" + libro.getIsbn() + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Envia los datos del libro requerido
	 * @param isbn El ISBN del libro requerido
	 * @return LIBRO encontrado, NULL si no existe
	 */
	public Libro buscarLibro(String isbn) {
		try {
			ArrayList libro = accesoBD.recuperar("SELECT * FROM libro WHERE ISBN='"+isbn+"'");
			if (!libro.isEmpty()) {
				return (Libro) libro.get(0);
			} else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}

