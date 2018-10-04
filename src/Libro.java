
public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private String tema;
	private int numPaginas;
	private String[] formato = new String[3];
	private String[] estado = new String[2];
	private int locTema;

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

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getFormato(int posicion) {
		return formato[posicion];
	}

	public void setFormato(int posicion, String formato) {
		this.formato[posicion] = formato;
	}

	public String getEstado(int posicion) {
		return estado[posicion];
	}

	public void setEstado(int posicion, String estado) {
		this.estado[posicion] = estado;
	}

	public int getLocTema() {
		return locTema;
	}

	public void setLocTema(int locTema) {
		this.locTema = locTema;
	}
}
