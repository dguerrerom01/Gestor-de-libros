package controlador;

import java.sql.SQLException;
import java.util.*;

import modelo.Libro;

public class AccionesLibro {
	
	AccesoBD accesoBD = new AccesoBD();

	private ArrayList<Libro> libros;
	
	public ArrayList<Libro> getLibros() {
		cargarBD();
		return libros;
	}
	
	public AccionesLibro() {
		cargarBD();
	}

	private void cargarBD() {
		try {
			ArrayList<HashMap<String, Object>> datosLibro = null;
			datosLibro = accesoBD.recuperar("SELECT * FROM libro ORDER BY ISBN");
			libros.clear();
			for (HashMap<String, Object> hashMap : datosLibro) {
				System.out.println(hashMap);
				libros.add(new Libro(hashMap));
			}
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
					"INSERT INTO `libro`(`isbn`,`titulo`,`autor`,`editorial`,`paginas`,`ejemplares`,`tema`,`formato`,`estado`) "
							+ "VALUES('" + libro.getIsbn() + "','" + libro.getTitulo() + "','" + libro.getAutor()
							+ "','" + libro.getEditorial() + "','" + libro.getPaginas() + "','"
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
							+ libro.getEditorial() + "', Paginas='" + libro.getPaginas() + "',Ejemplares='"
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
		assert (isbn != null && !isbn.isEmpty());
		Libro libro = null;
		ArrayList<HashMap<String, Object>> datosLibro = null;
		try {
			datosLibro = new AccesoBD().recuperar("SELECT * FROM libro WHERE ISBN='"+isbn+"'");
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | SQLException e) {
			e.printStackTrace();
		}
		if (!datosLibro.isEmpty()) {
			libro = new Libro(datosLibro.get(0));
		}
		return libro;
	}
	
}

