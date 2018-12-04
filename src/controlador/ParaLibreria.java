package controlador;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.*;

import modelo.Libro;
import modelo.Tema;
import utiles.Mensajes;
import vista.GestorTema;
import vista.Libreria;

public class ParaLibreria extends Libreria {

	Fachada fachada = new Fachada();

	public ParaLibreria() {
		
		cargaDlmNombres();
		cargaDlmTemas();
		
		/**
		 * Accion al seleccionar el boton Nuevo libro
		 */
		mntmNuevoLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla(true);
				deshabilitarText(true, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
				deshabilitarCombo(true, comboTemas);
				deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
				deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
						mntmModificarLibro);
				deshabilitarMenuItem(true, mntmDarDeAlta);
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
				int seleccion = JOptionPane.showConfirmDialog(null,
						"<html>&#191;Desea salir de la aplicaci&#243;n?</html>", "Salir", JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (seleccion == JOptionPane.YES_OPTION) {
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
					if (fachada.buscar(txtIsbn.getText()) == null) {
						if (fachada.insertar(recogerDatosPantalla())) {
							cargaDlmNombres();
							limpiarPantalla(true);
							escribirMensaje(Mensajes.LIBROAGREGADO);
						} else
							escribirMensaje(Mensajes.ERRORAGREGARLIBRO);
					} else
						escribirMensaje(Mensajes.ERRORISBNDUPLICADO);
				} else
					escribirMensaje(Mensajes.ERRORVALIDACION);
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Agregar ejemplares
		 */
		mntmAgregarEjemplares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cantidad = input("Inserte la cantidad deseada. Actualmente dispone de " + txtEjemplares.getText()
						+ " ejemplares:", "Agregar ejemplares");
				cantidad += Integer.valueOf(txtEjemplares.getText());
				if (cantidad < 999999999) {
					txtEjemplares.setText(String.valueOf(cantidad));
					fachada.editar(recogerDatosPantalla());
					escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
				} else
					escribirMensaje(Mensajes.FUERADELIMITES);
				cargaDlmNombres();
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
							"�Desea dar de baja el libro? Le quedan " + txtEjemplares.getText()
									+ " ejemplares disponibles.",
							"Dar de baja", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (seleccion == JOptionPane.YES_OPTION) {
						fachada.borrar(fachada.obtenerLibros().get(listLibros.getSelectedIndex()));
						cargaDlmNombres();
						limpiarPantalla(true);
						deshabilitarText(true, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
						deshabilitarCombo(true, comboTemas);
						deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad,
								optReedicion);
						deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
								mntmModificarLibro);
						deshabilitarMenuItem(true, mntmDarDeAlta);
						esconderLabel(false, lblEjemplares);
						esconderText(false, txtEjemplares);
						esconderButton(false, btnGuardar);
						escribirMensaje(Mensajes.LIBROELIMINADO);
					}
				} else
					escribirMensaje(Mensajes.ERRORINDICE);
				cargaDlmNombres();
			}
		});
		
		
		/**
		 * Accion al seleccionar el boton Retirar ejemplares
		 */
		mntmRetirarEjemplares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cantidad = input("Inserte la cantidad deseada. Actualmente dispone de " + txtEjemplares.getText()
						+ " ejemplares:", "Retirar ejemplares");
				cantidad = Integer.valueOf(txtEjemplares.getText()) - cantidad;
				if (cantidad >= 0) {
					txtEjemplares.setText(String.valueOf(cantidad));
					fachada.editar(recogerDatosPantalla());
					escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
				} else
					escribirMensaje(Mensajes.FUERADELIMITES);
				cargaDlmNombres();
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
				deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
						mntmModificarLibro);
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
					fachada.editar(recogerDatosPantalla());
					cargaDlmNombres();
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
		
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorTema dialog = new ParaGestorTema(new Libreria(), true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				cargaDlmTemas();
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
					enviarDatosAPantalla(fachada.buscar(listLibros.getSelectedValue().toString().substring(
							listLibros.getSelectedValue().toString().indexOf("ISBN") + 10,
							listLibros.getSelectedValue().toString().indexOf("ISBN") + 23)));
					deshabilitarText(false, txtIsbn, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
					deshabilitarCombo(false, comboTemas);
					deshabilitarOption(false, optCartone, optRustica, optGrapado, optEspiral, optNovedad, optReedicion);
					deshabilitarMenuItem(false, mntmDarDeAlta);
					deshabilitarMenuItem(true, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
							mntmModificarLibro);
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
					if (fachada.buscar(txtIsbn.getText()) != null) {
						enviarDatosAPantalla(fachada.buscar(txtIsbn.getText()));
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
					}
				} else {
					txtEjemplares.setText("1");
					deshabilitarText(true, txtTitulo, txtAutor, txtEditorial, txtNumPaginas);
					deshabilitarCombo(true, comboTemas);
					deshabilitarOption(true, optCartone, optRustica, optGrapado, optEspiral, optNovedad,
							optReedicion);
					deshabilitarMenuItem(true, mntmDarDeAlta);
					deshabilitarMenuItem(false, mntmAgregarEjemplares, mntmDarDeBaja, mntmRetirarEjemplares,
							mntmModificarLibro);
					esconderLabel(false, lblEjemplares);
					esconderText(false, txtEjemplares);
					esconderButton(false, btnGuardar);
					if (listLibros.getSelectedIndex() != -1) {
						limpiarPantalla(false);
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
				if (String.valueOf(arg0.getKeyChar()).matches("[^0-9]") || txtIsbn.getText().length() == 13) {
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
				if (String.valueOf(arg0.getKeyChar()).matches("[^0-9]") || txtNumPaginas.getText().length() == 9) {
					arg0.consume();
				}

			}
		});

		txtAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (String.valueOf(arg0.getKeyChar()).matches("[0-9]")) {
					arg0.consume();
				}
			}
		});

		txtEditorial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (String.valueOf(arg0.getKeyChar()).matches("[0-9]")) {
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
				Component c = (Component) e.getSource();
				double w = c.getWidth();
				double h = c.getHeight();
				if ((h / w) * 16 <= 10) {
					splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
				} else
					splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				if (w > 1000 && h > 600) {
					Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 20);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 15);
					Font fuenteMensaje = new Font("Tahoma", Font.BOLD, 18);
					cambiarFuente(fuenteTitulo, fuenteComponente, fuenteMensaje);
					txtIsbn.setMaximumSize(new Dimension(120, 200000000));
					txtIsbn.setMinimumSize(new Dimension(120, 5));
					txtNumPaginas.setMaximumSize(new Dimension(85, 200000000));
					txtNumPaginas.setMinimumSize(new Dimension(85, 5));
					txtEjemplares.setMaximumSize(new Dimension(85, 200000000));
					txtEjemplares.setMinimumSize(new Dimension(85, 5));
				} else {
					Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 16);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 12);
					Font fuenteMensaje = new Font("Tahoma", Font.BOLD, 15);
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
			JOptionPane.showMessageDialog(null, "<html>Cantidad incorrecta o fuera de l&#237;mites.</html>", "Error",
					JOptionPane.ERROR_MESSAGE);
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
		for (Libro libros : fachada.obtenerLibros()) {
			dlmNombres.addElement("<html><b>Titulo:</b> " + libros.getTitulo() + " - <b>ISBN:</b> " + libros.getIsbn()
					+ " - <b>Ejemplares:</b> " + libros.getEjemplares() + "</html>");
		}
	}
	
	private void cargaDlmTemas() {
		dlmTemas.removeAllElements();
		for (Tema temas : fachada.obtenerTemas()) {
			dlmTemas.addElement(temas.getTema());
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
			libro.setPaginas(Integer.valueOf((txtNumPaginas.getText())));
		} catch (NumberFormatException e) {
			libro.setPaginas(0);
		}
		if (!txtEjemplares.getText().isEmpty()) {
			libro.setEjemplares(Integer.valueOf(txtEjemplares.getText()));
		} else
			libro.setEjemplares(1);
		String temaSeleccionado = comboTemas.getSelectedItem().toString();
		libro.setTema(temaSeleccionado);
		for (Enumeration<AbstractButton> buttons = groupFormato.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
            	libro.setFormato(button.getText());
			}
        }
		for (Enumeration<AbstractButton> buttons = groupEstado.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
            	libro.setEstado(button.getText());
			}
        }
		return libro;
	}

	/**
	 * Borra la informaci�n insertada en pantalla
	 */
	private void limpiarPantalla(Boolean isbn) {
		if (isbn) {
			txtIsbn.setText(null);	
		}
		txtTitulo.setText(null);
		txtAutor.setText(null);
		txtEditorial.setText(null);
		txtNumPaginas.setText(null);
		txtEjemplares.setText(null);
		comboTemas.setSelectedIndex(0);
		optCartone.setSelected(true);
		optRustica.setSelected(false);
		optGrapado.setSelected(false);
		optEspiral.setSelected(false);
		optNovedad.setSelected(true);
		optReedicion.setSelected(false);
		listLibros.clearSelection();
		escribirMensaje(Mensajes.MENSAJEVACIO);
	}
	
	/**
	 * Rellena los campos con la informaci�n de Libro
	 * @param libro El libro al que tomar la informaci�n
	 */
	private void enviarDatosAPantalla(Libro libro) {
		txtIsbn.setText(String.valueOf(libro.getIsbn()));
		txtTitulo.setText(libro.getTitulo());
		txtAutor.setText(libro.getAutor());
		txtEditorial.setText(libro.getEditorial());
		txtNumPaginas.setText(String.valueOf(libro.getPaginas()));
		txtEjemplares.setText(String.valueOf(libro.getEjemplares()));
		for (int i = 0; i < comboTemas.getItemCount(); i++) {
			if (comboTemas.getItemAt(i).equals(libro.getTema())) {
				comboTemas.setSelectedIndex(i);
			}
		}
		for (Enumeration<AbstractButton> buttons = groupFormato.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.getText().equals(libro.getFormato())) {
            	button.setSelected(true);
			}
        }
		for (Enumeration<AbstractButton> buttons = groupEstado.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.getText().equals(libro.getEstado())) {
            	button.setSelected(true);
			}
        }
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
