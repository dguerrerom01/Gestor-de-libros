import java.awt.event.*;

public class ParaLibreria extends Libreria {

	Estanteria estanteria = new Estanteria();

	public ParaLibreria() {
		
		listLibros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listLibros.getSelectedIndex();
				enviarDatosAPantalla(estanteria.buscarLibro(listLibros.getSelectedIndex()));
			}
		});

		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
				comboTemas.setSelectedIndex(0);
			}
		});

		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int borrarDisponible = listLibros.getSelectedIndex();
				if (borrarDisponible != -1) {
					estanteria.borrarLibro(listLibros.getSelectedIndex());
					dlmNombres.remove(borrarDisponible);
					listLibros.repaint();
					limpiarPantalla();
				} else lblMensaje.setText("Debe seleccionar un libro");
			}
		});

		btnAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtIsbn.getText().matches("\\d{13}") &&
						!txtTitulo.getText().isEmpty() &&
						!txtAutor.getText().isEmpty() &&
						txtNumPaginas.getText().matches("\\d{1,9}") &&
						(chkCartone.isSelected() || chkRustica.isSelected() || chkTapaDura.isSelected()) &&
						(optNovedad.isSelected() || optReedicion.isSelected())) {
					int estado = 0;
					estado = estanteria.insertarLibro(recogerDatosPantalla());
					if (estado == 1) {
						lblMensaje.setText("Libro almacenado correctamente");
						dlmNombres.addElement(txtTitulo.getText());
						listLibros.setModel(dlmNombres);
					} else if (estado == -1)
						lblMensaje.setText("El libro ya existe");
					else if (estado == 0)
						lblMensaje.setText("No hay espacio para mas libros");
				} else lblMensaje.setText("Ha ocurrido un error");
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		
		txtIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (txtIsbn.getText().length() == 13) {
					arg0.consume();
				}
			}
		});
		
		txtNumPaginas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (txtNumPaginas.getText().length() == 9) {
					arg0.consume();
				}
			}
		});
	}

	
	private Libro recogerDatosPantalla() {
		Libro libro;
		libro = new Libro();
		libro.setIsbn(txtIsbn.getText());
		libro.setTitulo(txtTitulo.getText());
		libro.setAutor(txtAutor.getText());
		try {
			libro.setNumPaginas(Integer.valueOf((txtNumPaginas.getText())));
		} catch (NumberFormatException e) {
			libro.setNumPaginas(0);
		}
		String temaSeleccionado = comboTemas.getSelectedItem().toString();
		libro.setTema(temaSeleccionado);
		libro.setLocTema(comboTemas.getSelectedIndex());
		libro.setFormato(0, chkCartone.isSelected() ? "Carton" : ".");
		libro.setFormato(1, chkRustica.isSelected() ? "Rustico" : ".");
		libro.setFormato(2, chkTapaDura.isSelected() ? "Tapa Dura" : ".");
		libro.setEstado(0, optNovedad.isSelected() ? "Novedad" : ".");
		libro.setEstado(1, optReedicion.isSelected() ? "Reedicion" : ".");
		return libro;
	}

	private void limpiarPantalla() {
		txtIsbn.setText(null);
		txtTitulo.setText(null);
		txtAutor.setText(null);
		txtNumPaginas.setText(null);
		chkCartone.setSelected(false);
		chkRustica.setSelected(false);
		chkTapaDura.setSelected(false);
		optNovedad.setSelected(false);
		optReedicion.setSelected(false);
		lblMensaje.setText(" ");
	}
	
	protected void enviarDatosAPantalla(Libro libro) {
		txtIsbn.setText(String.valueOf(libro.getIsbn()));
		txtTitulo.setText(libro.getTitulo());
		txtAutor.setText(libro.getAutor());
		txtNumPaginas.setText(String.valueOf(libro.getNumPaginas()));
		comboTemas.setSelectedIndex(libro.getLocTema());
		chkCartone.setSelected(libro.getFormato(0)!=".");
		chkRustica.setSelected(libro.getFormato(1)!=".");
		chkTapaDura.setSelected(libro.getFormato(2)!=".");
		optNovedad.setSelected(libro.getEstado(0)!=".");
		optReedicion.setSelected(libro.getEstado(1)!=".");
	}


}
