package controlador;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modelo.Libro;
import utiles.Mensajes;
import vista.Libreria;

public class ParaLibreria extends Libreria {

	Estanteria estanteria = new Estanteria();

	public ParaLibreria() {
		
		cargaDlmNombres();
		
		/**
		 * Accion al seleccionar el boton Nuevo libro
		 */
		mntmNuevoLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
				deshabilitarText(true, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
				deshabilitarCombo(true, comboTemas);
				deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
				deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares, mntmModificarLibro);
				esconderLabel(false, lblEjemplares);
				esconderText(false, txtEjemplares);
				esconderButton(false, btnGuardar);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Salir
		 */
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (seleccion == JOptionPane.YES_OPTION){
		        	System.exit(EXIT_ON_CLOSE);
				}
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Dar de alta
		 */
		mntmDarDeAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobarLibro()) {
					if (estanteria.buscarLibro(txtIsbn.getText()) == null) {
						if (estanteria.insertarLibro(recogerDatosPantalla())) {
							dlmNombres.addElement("<html><b>Titulo:</b> " + txtTitulo.getText() + 
									" - <b>ISBN:</b> " + txtIsbn.getText()
							+ " - <b>Ejemplares:</b> " + txtEjemplares.getText() + "</html>");
							listLibros.setModel(dlmNombres);
							limpiarPantalla();
							escribirMensaje(Mensajes.LIBROAGREGADO);
						} else escribirMensaje(Mensajes.ERRORAGREGARLIBRO);
					} else escribirMensaje(Mensajes.ERRORISBNDUPLICADO);
				} else escribirMensaje(Mensajes.ERRORVALIDACION);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Agregar ejemplares
		 */
		mntmAgregarEjemplares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cantidad = input("Inserte la cantidad deseada. Actualmente dispone de " + txtEjemplares.getText() + " ejemplares", "Agregar ejemplares");
				System.out.println("Cantidad : " + cantidad);
				cantidad += Integer.valueOf(txtEjemplares.getText());
				System.out.println("total : " + cantidad);
				if (cantidad < 999999999) {
					txtEjemplares.setText(String.valueOf(cantidad));
					estanteria.editarLibro(recogerDatosPantalla());
					escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
				} else escribirMensaje(Mensajes.FUERADELIMITES);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Dar de baja
		 */
		mntmDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int borrarDisponible = listLibros.getSelectedIndex();
				if (borrarDisponible != -1) {
					int seleccion = JOptionPane.showConfirmDialog(null,
							"¿Desea dar de baja el libro? Le quedan " + txtEjemplares.getText()
									+ " ejemplares disponibles.",
							"Dar de baja", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (seleccion == JOptionPane.YES_OPTION) {
						estanteria.borrarLibro(estanteria.getLibros().get(listLibros.getSelectedIndex()));
						dlmNombres.remove(borrarDisponible);
						listLibros.repaint();
						limpiarPantalla();
						deshabilitarText(true, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
						deshabilitarCombo(true, comboTemas);
						deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
						deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares, mntmModificarLibro);
						esconderLabel(false, lblEjemplares);
						esconderText(false, txtEjemplares);
						esconderButton(false, btnGuardar);
						escribirMensaje(Mensajes.LIBROELIMINADO);
					}
				} else
					escribirMensaje(Mensajes.ERRORINDICE);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Retirar ejemplares
		 */
		mntmRetirarEjemplares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cantidad = input("Inserte la cantidad deseada. Actualmente dispone de " + txtEjemplares.getText() + " ejemplares", "Retirar ejemplares");
				cantidad = Integer.valueOf(txtEjemplares.getText()) - cantidad;
				if (cantidad >= 0) {
					txtEjemplares.setText(String.valueOf(cantidad));
					estanteria.editarLibro(recogerDatosPantalla());
					escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
				} else escribirMensaje(Mensajes.FUERADELIMITES);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Modificar libro
		 */
		mntmModificarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					deshabilitarText(false, txtIsbn);
					deshabilitarText(true, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
					deshabilitarCombo(true, comboTemas);
					deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
					deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares, mntmModificarLibro);
					esconderButton(true, btnGuardar);
					escribirMensaje(Mensajes.AVISOEDICION);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Editar (guardar)
		 */
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarLibro()) {
					estanteria.editarLibro(recogerDatosPantalla());
					dlmNombres.set(listLibros.getSelectedIndex(),
							("<html><b>Titulo:</b> " + txtTitulo.getText() + " - <b>ISBN:</b> " + txtIsbn.getText()
									+ " - <b>Ejemplares:</b> " + txtEjemplares.getText() + "</html>"));
					listLibros.repaint();
					deshabilitarText(false, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
					deshabilitarCombo(false, comboTemas);
					deshabilitarOption(false, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
					deshabilitarMenuItem(true, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
							mntmModificarLibro);
					esconderButton(false, btnGuardar);
					escribirMensaje(Mensajes.EDICIONCORRECTA);
				} else
					escribirMensaje(Mensajes.ERRORVALIDACION);
			}
		});
		
		
		/**
		 * Accion al seleccionar un libro de la lista
		 */
		listLibros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (listLibros.getSelectedIndex() != -1) {
					escribirMensaje(Mensajes.AVISOLIBROSELECCIONADO);
					enviarDatosAPantalla(estanteria.getLibros().get(listLibros.getSelectedIndex()));
					deshabilitarText(false, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
					deshabilitarCombo(false, comboTemas);
					deshabilitarOption(false, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
					deshabilitarMenuItem(false, mntmDarDeAlta);
					deshabilitarMenuItem(true, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares, mntmModificarLibro);
					esconderLabel(true, lblEjemplares);
					esconderText(true, txtEjemplares);
					esconderButton(false, btnGuardar);
				}
			}
		});
		
		
		/**
		 * Accion al escribir en el campo ISBN
		 */
		txtIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtIsbn.getText().length() == 13) {
					if (estanteria.buscarLibro(txtIsbn.getText()) != null) {
						enviarDatosAPantalla(estanteria.buscarLibro(txtIsbn.getText()));
						for (int i = 0; i < dlmNombres.size(); i++) {
							if (dlmNombres.getElementAt(i)
									.equals("<html><b>Titulo:</b> " + txtTitulo.getText() + " - <b>ISBN:</b> "
											+ txtIsbn.getText() + " - <b>Ejemplares:</b> " + txtEjemplares.getText()
											+ "</html>")) {

								listLibros.setSelectedIndex(i);
							}
						}
						escribirMensaje(Mensajes.AVISOISBNEXISTENTE);
						deshabilitarText(false, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
						deshabilitarCombo(false, comboTemas);
						deshabilitarOption(false, optCartone, optRustica, optGrapado, optEspiral, optNovedad,
								optReedicion);
						deshabilitarMenuItem(false, mntmDarDeAlta);
						deshabilitarMenuItem(true, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
								mntmModificarLibro);
						esconderLabel(true, lblEjemplares);
						esconderText(true, txtEjemplares);
						esconderButton(false, btnGuardar);
					} else {
						escribirMensaje(Mensajes.MENSAJEVACIO);
						deshabilitarText(true, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
						deshabilitarCombo(true, comboTemas);
						deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
						deshabilitarMenuItem(true, mntmDarDeAlta);
						deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares, mntmModificarLibro);
						esconderLabel(false, lblEjemplares);
						esconderText(false, txtEjemplares);
						esconderButton(false, btnGuardar);
					}
				}
			}
		});
		
		/**
		 * Accion al escribir en el campo de texto ISBN
		 */
		txtIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (txtIsbn.getText().length() == 13) {
					arg0.consume();
				}
			}
		});
		
		/**
		 * Accion al escribir en el campo de texto Numero paginas
		 */
		txtNumPaginas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (txtNumPaginas.getText().length() == 9) {
					arg0.consume();
				}
			}
		});
		
		/**
		 * Accion al redimensionar la ventana
		 */
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				double w = c.getWidth();
				double h = c.getHeight();
				if ((h / w) * 16 <= 10) {
					splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
				} else splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				
				if (w > 1000 && h > 600) {
					Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 20);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 15);
					Font fuenteMensaje = new Font("Tahoma", Font.BOLD, 16);
					cambiarFuente(fuenteTitulo, fuenteComponente, fuenteMensaje);
					txtIsbn.setMaximumSize(new Dimension(120, 200000000));
					txtIsbn.setMinimumSize(new Dimension(120, 5));
					txtNumPaginas.setMaximumSize(new Dimension(85, 200000000));
					txtNumPaginas.setMinimumSize(new Dimension(85, 5));
					txtEjemplares.setMaximumSize(new Dimension(85, 200000000));
					txtEjemplares.setMinimumSize(new Dimension(85, 5));
				} else {
					Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 14);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 12);
					Font fuenteMensaje = new Font("Tahoma", Font.BOLD, 13);
					cambiarFuente(fuenteTitulo, fuenteComponente, fuenteMensaje);
					txtIsbn.setMaximumSize(new Dimension(100, 200000000));
					txtIsbn.setMinimumSize(new Dimension(80, 5));
					txtNumPaginas.setMaximumSize(new Dimension(65, 200000000));
					txtNumPaginas.setMinimumSize(new Dimension(65, 5));
					txtEjemplares.setMaximumSize(new Dimension(65, 200000000));
					txtEjemplares.setMinimumSize(new Dimension(65, 5));
				}
			}

			private void cambiarFuente(Font fuenteTitulo, Font fuenteComponente, Font fuenteMensaje) {
				lblLibreria.setFont(fuenteTitulo);
				lblIsbn.setFont(fuenteComponente);
				lblTitulo.setFont(fuenteComponente);
				lblTema.setFont(fuenteComponente);
				lblAutor.setFont(fuenteComponente);
				lblEditorial.setFont(fuenteComponente);
				lblNumPaginas.setFont(fuenteComponente);
				lblEjemplares.setFont(fuenteComponente);
				lblLibros.setFont(fuenteComponente);
				lblMensaje.setFont(fuenteMensaje);
				listLibros.setFont(fuenteComponente);
				txtIsbn.setFont(fuenteComponente);
				txtTitulo.setFont(fuenteComponente);
				txtAutor.setFont(fuenteComponente);
				txtEditorial.setFont(fuenteComponente);
				txtNumPaginas.setFont(fuenteComponente);
				txtEjemplares.setFont(fuenteComponente);
				comboTemas.setFont(fuenteComponente);
				optCartone.setFont(fuenteComponente);
				optRustica.setFont(fuenteComponente);
				optGrapado.setFont(fuenteComponente);
				optEspiral.setFont(fuenteComponente);
				optNovedad.setFont(fuenteComponente);
				optReedicion.setFont(fuenteComponente);
				btnGuardar.setFont(fuenteComponente);
			}
		});
	}

	private int input(String mensaje, String titulo) {
		String opcion = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
		if (opcion == null || opcion.matches("\\d{0,9}")) {
			if (opcion == null || opcion.isEmpty()) {
				return 0;
			} 
		} else {
			JOptionPane.showMessageDialog(null, "Cantidad incorrecta o fuera de limites", "Error", JOptionPane.ERROR_MESSAGE);
			opcion = "0";
			input(mensaje, titulo);
		}
		return Integer.valueOf(opcion);
	}

	private boolean comprobarLibro() {
		return txtIsbn.getText().matches("\\d{13}")
				&& !txtTitulo.getText().isEmpty() && !txtAutor.getText().isEmpty() && !txtEditorial.getText().isEmpty()
				&& txtNumPaginas.getText().matches("\\d{1,9}") && (optCartone.isSelected() || optRustica.isSelected()
						|| optGrapado.isSelected() || optEspiral.isSelected())
				&& (optNovedad.isSelected() || optReedicion.isSelected());
	}
	
	/*
	 * Listado
	 */
	private void cargaDlmNombres() {
		dlmNombres.clear();
		for (Libro libros : estanteria.getLibros()) {
			dlmNombres.addElement("<html><b>Titulo:</b> " + libros.getTitulo() + " - <b>ISBN:</b> " + libros.getIsbn()
					+ " - <b>Ejemplares:</b> " + libros.getEjemplares() + "</html>");
		}
	}


	/**
	 * Toma los datos que hay en pantala y los almacena en un Libro
	 * @return libro El libro creado a partir de los datos
	 */
	private Libro recogerDatosPantalla() {
		Libro libro;
		libro = new Libro();
		libro.setIsbn(txtIsbn.getText());
		libro.setTitulo(txtTitulo.getText());
		libro.setAutor(txtAutor.getText());
		libro.setEditorial(txtEditorial.getText());
		try {
			libro.setNumPaginas(Integer.valueOf((txtNumPaginas.getText())));
		} catch (NumberFormatException e) {
			libro.setNumPaginas(0);
		}
		if (!txtEjemplares.getText().isEmpty()) {
			libro.setEjemplares(Integer.valueOf(txtEjemplares.getText()));
		} else libro.setEjemplares(1);
		String temaSeleccionado = comboTemas.getSelectedItem().toString();
		libro.setTema(temaSeleccionado);
		libro.setPosicionTema(comboTemas.getSelectedIndex());
		libro.setFormato(0, optCartone.isSelected() ? true : false);
		libro.setFormato(1, optRustica.isSelected() ? true : false);
		libro.setFormato(2, optGrapado.isSelected() ? true: false);
		libro.setFormato(3, optEspiral.isSelected() ? true: false);
		libro.setEstado(0, optNovedad.isSelected() ? true : false);
		libro.setEstado(1, optReedicion.isSelected() ? true : false);
		return libro;
	}

	/**
	 * Borra la información insertada en pantalla
	 */
	private void limpiarPantalla() {
		txtIsbn.setText(null);
		txtTitulo.setText(null);
		txtAutor.setText(null);
		txtEditorial.setText(null);
		txtNumPaginas.setText(null);
		txtEjemplares.setText(null);
		comboTemas.setSelectedIndex(0);
		optCartone.setSelected(false);
		optRustica.setSelected(false);
		optGrapado.setSelected(false);
		optEspiral.setSelected(false);
		optNovedad.setSelected(false);
		optReedicion.setSelected(false);
		escribirMensaje(Mensajes.MENSAJEVACIO);
		lblMensaje.setText(" ");
	}
	
	/**
	 * Rellena los campos con la información de Libro
	 * @param libro El libro al que tomar la información
	 */
	private void enviarDatosAPantalla(Libro libro) {
		txtIsbn.setText(String.valueOf(libro.getIsbn()));
		txtTitulo.setText(libro.getTitulo());
		txtAutor.setText(libro.getAutor());
		txtEditorial.setText(libro.getEditorial());
		txtNumPaginas.setText(String.valueOf(libro.getNumPaginas()));
		txtEjemplares.setText(String.valueOf(libro.getEjemplares()));
		comboTemas.setSelectedIndex(libro.getPosicionTema());
		optCartone.setSelected(libro.getFormato(0));
		optRustica.setSelected(libro.getFormato(1));
		optGrapado.setSelected(libro.getFormato(2));
		optEspiral.setSelected(libro.getFormato(2));
		optNovedad.setSelected(libro.getEstado(0));
		optReedicion.setSelected(libro.getEstado(1));
	}
	
	/**
	 * Escribe un mensaje al usuario
	 * @param mensaje
	 */
	private void escribirMensaje(String mensaje) {
		lblMensaje.setText(mensaje);
	}

	
	
	// Metodos para habilitar o deshabilitar componentes en grupo
	
	private void deshabilitarText(Boolean activado, JTextField... jtextfields) {
		for (JTextField jTextField : jtextfields) {
			jTextField.setEnabled(activado);
		}
	}

	private void deshabilitarCombo(Boolean activado, JComboBox... jcheckboxs) {
		for (JComboBox jComboBox : jcheckboxs) {
			jComboBox.setEnabled(activado);
		}
	}

	private void deshabilitarCheck(Boolean activado, JCheckBox... jcheckboxs) {
		for (JCheckBox jCheckBox : jcheckboxs) {
			jCheckBox.setEnabled(activado);
		}
	}

	private void deshabilitarOption(Boolean activado, JRadioButton... radioButtons) {
		for (JRadioButton jRadioButton : radioButtons) {
			jRadioButton.setEnabled(activado);
		}
	}
	
	protected void deshabilitarMenuItem(Boolean activado, JMenuItem... jMenuItems) {
		for (JMenuItem jMenuItem : jMenuItems) {
			jMenuItem.setEnabled(activado);
		}
	}

	private void deshabilitarList(Boolean activado, JList... jLists) {
		for (JList jList : jLists) {
			jList.setEnabled(activado);
		}
	}

	// Metodos para esconder o mostrar componentes en grupo
	
	private void esconderText(Boolean activado, JTextField... jTextFields) {
		for (JTextField jTextField : jTextFields) {
			jTextField.setVisible(activado);
		}
	}

	private void esconderLabel(Boolean activado, JLabel... jLabels) {
		for (JLabel jLabel : jLabels) {
			jLabel.setVisible(activado);
		}
	}

	private void esconderButton(Boolean activado, JButton... buttons) {
		for (JButton jButton : buttons) {
			jButton.setVisible(activado);
		}
	}

}
