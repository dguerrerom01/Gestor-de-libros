
public class Estanteria {

	private int max_Reg = 5;
	Libro[] libros = new Libro[max_Reg];
	private int reg = 0;
	
	/**
	 * Introducimos por parametros un libro previamente creado.
	 * Comprobamos que no exista o que el array no est� lleno y si se cumple todo lo introducimos
	 * @param libro
	 * @return Devolvera valores de : 1 en caso de libro introducido
	 * 								 -1 en caso de que el libro ya exista
	 * 								  0 en caso de que el array este lleno
	 */
	public int insertarLibro(Libro libro) {
		int estado;
		if (reg < max_Reg) {
			if (posicionLibro(libro.getIsbn()) == -1) {
				libros[reg] = (Libro) libro;
				estado = 1;
				reg++;
			}
			else {
				estado = -1;
			} 
		} 
		else {
			estado = 0;
		}
		return estado;
	}

	/**
	 * Entra por parametros una String con un titulo, y te devuelve el objeto libro exacto
	 * @param posicion
	 * @return
	 */
	public Libro buscarLibro(int posicion) {
		if (posicion != -1) return libros[posicion];
		else return null;
	}
	
	
	/**
	 * 
	 * @param posicion
	 * @return 1 en caso de borrar el libro, 0 en caso de error
	 */
	/*
	 * Puesto que para borrar necesito una posici�n exacta, llamo al metodo buscarLibro que me la devuelve a 
	 * trav�s de un nombre
	 */
	public int borrarLibro(int posicion) {
		libros[posicion] = libros[reg - 1];
		reg--;
		return 1;
	}
	
	/**
	 * Introduce un nombre de un libro por parametros y lo busca
	 * @param nombre
	 * @return La posici�n del libro, en caso de que est�, o -1 si no est�
	 */
//	private int posicionLibro(String nombre) {
//		int loc = 0;
//		while (loc < max_Reg) {
//			if (libros[loc] != null) {
//				if (nombre.equals(libros[loc].getTitulo())) {
//					return loc;
//				} 
//				else {
//					loc++;
//				} 
//			} 
//			else
//				loc++;
//		} 
//		return -1;
//	}
	
	private int posicionLibro(int Isbn) {
		int loc = 0;
		while (loc < max_Reg) {
			if (libros[loc] != null) {
				if (Isbn == libros[loc].getIsbn()) {
					return loc;
				} 
				else {
					loc++;
				} 
			} 
			else
				loc++;
		} 
		return -1;
	}

}

