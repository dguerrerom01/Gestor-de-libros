package control;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import modelo.Libro;
import utiles.Mensajes;
import vista.Libreria;

public class ParaLibreria extends Libreria {

	Estanteria estanteria = new Estanteria();

	public ParaLibreria() {
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
							if (dlmNombres.getElementAt(i).equals(txtTitulo.getText() + " - " + txtIsbn.getText())) {
								listLibros.setSelectedIndex(i);
							}
						}
						escribirMensaje(Mensajes.AVISOISBNEXISTENTE);
						deshabilitarText(false, txtTitulo, txtAutor, txtNumPaginas);
						deshabilitarCombo(false, comboTemas);
						deshabilitarCheck(false, chkCartone, chkRustica, chkGrapado, chkEspiral);
						deshabilitarOption(false, optNovedad, optReedicion);
						esconderLabel(true, lblEjemplares);
						esconderText(true, txtEjemplares);
						esconderButton(true, btnEditar);
						cambiarNombreButton(btnAlta, "Actualizar");
						if (Integer.valueOf(txtEjemplares.getText()) > 1) {
							cambiarNombreButton(btnBaja, "Reducir");
						} else cambiarNombreButton(btnBaja, "Baja");
					} else {
						escribirMensaje(Mensajes.MENSAJEVACIO);
						deshabilitarText(true, txtTitulo, txtAutor, txtNumPaginas);
						deshabilitarCombo(true, comboTemas);
						deshabilitarCheck(true, chkCartone, chkRustica, chkGrapado, chkEspiral);
						deshabilitarOption(true, optNovedad, optReedicion);
						esconderLabel(false, lblEjemplares);
						esconderText(false, txtEjemplares);
						esconderButton(false, btnEditar);
						cambiarNombreButton(btnAlta, "Alta");
						cambiarNombreButton(btnBaja, "Baja");
					}
				}
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
					deshabilitarText(false, txtIsbn, txtTitulo, txtAutor, txtNumPaginas);
					deshabilitarCombo(false, comboTemas);
					deshabilitarCheck(false, chkCartone, chkRustica, chkGrapado, chkEspiral);
					deshabilitarOption(false, optNovedad, optReedicion);
					esconderLabel(true, lblEjemplares);
					esconderText(true, txtEjemplares);
					esconderButton(true, btnEditar);
					cambiarNombreButton(btnAlta, "Actualizar");
					if (Integer.valueOf(txtEjemplares.getText()) > 1) {
						cambiarNombreButton(btnBaja, "Reducir");
					} else cambiarNombreButton(btnBaja, "Baja");
				}
			}
		});

		/**
		 * Accion al seleccionar el boton Nuevo
		 */
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
				deshabilitarText(true, txtIsbn, txtTitulo, txtAutor, txtNumPaginas);
				deshabilitarCombo(true, comboTemas);
				deshabilitarCheck(true, chkCartone, chkRustica, chkGrapado, chkEspiral);
				deshabilitarOption(true, optNovedad, optReedicion);
				esconderLabel(false, lblEjemplares);
				esconderText(false, txtEjemplares);
				esconderButton(false, btnEditar);
				cambiarNombreButton(btnAlta, "Alta");
				cambiarNombreButton(btnBaja, "Baja");
			}
		});

		/**
		 * Accion al seleccionar el boton Baja
		 */
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Integer.valueOf(txtEjemplares.getText()) > 1) {
					int cantidad = Integer.parseInt(txtEjemplares.getText()) - 1;
					txtEjemplares.setText(cantidad + "");
					estanteria.editarLibro(recogerDatosPantalla());
					escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
					if (cantidad < 2) {
						cambiarNombreButton(btnBaja, "Baja");
					}
				} else {
					int borrarDisponible = listLibros.getSelectedIndex();
					if (borrarDisponible != -1) {
						estanteria.borrarLibro(estanteria.getLibros().get(listLibros.getSelectedIndex()));
						dlmNombres.remove(borrarDisponible);
						listLibros.repaint();
						limpiarPantalla();
						escribirMensaje(Mensajes.LIBROELIMINADO);
					} else escribirMensaje(Mensajes.ERRORINDICE);
				}
			}
		});

		/**
		 * Accion al seleccionar el boton Alta
		 */
		btnAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Refactorizar y mejorar
				if (txtIsbn.getText().matches("\\d{13}") &&
						!txtTitulo.getText().isEmpty() &&
						!txtAutor.getText().isEmpty() &&
						txtNumPaginas.getText().matches("\\d{1,9}") &&
						(chkCartone.isSelected() || chkRustica.isSelected() || chkGrapado.isSelected() || chkEspiral.isSelected()) &&
						(optNovedad.isSelected() || optReedicion.isSelected())) {
					if (estanteria.buscarLibro(txtIsbn.getText()) == null) {
						if (estanteria.insertarLibro(recogerDatosPantalla())) {
							escribirMensaje(Mensajes.LIBROAGREGADO);
							dlmNombres.addElement(txtTitulo.getText() + " - " + txtIsbn.getText());
							listLibros.setModel(dlmNombres);
						} else escribirMensaje(Mensajes.ERRORAGREGARLIBRO);
					} else {
						btnBaja.setText("Reducir");
						int cantidad = Integer.parseInt(txtEjemplares.getText()) + 1;
						txtEjemplares.setText(cantidad + "");
						estanteria.editarLibro(recogerDatosPantalla());
						escribirMensaje(Mensajes.CANTIDADACTUALIZADA);
					}
				} else escribirMensaje(Mensajes.ERRORVALIDACION);
			}
		});
		
		/**
		 * Accion al seleccionar el boton Salir
		 */
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (seleccion == JOptionPane.YES_OPTION){
		        	System.exit(EXIT_ON_CLOSE);
				}
			}
		});
		
		/**
		 * Accion al seleccionar el boton Editar
		 */
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEditar.getText().equals("Editar")) {
					cambiarNombreButton(btnEditar, "Guardar");
					btnEditar.setText("Guardar");
					deshabilitarList(false, listLibros);
					deshabilitarButton(false, btnAlta, btnBaja);
					deshabilitarText(false, txtIsbn);
					deshabilitarText(true, txtTitulo, txtAutor, txtNumPaginas);
					deshabilitarCombo(true, comboTemas);
					deshabilitarCheck(true, chkCartone, chkRustica, chkGrapado, chkEspiral);
					deshabilitarOption(true, optNovedad, optReedicion);
					escribirMensaje(Mensajes.AVISOEDICION);
				} else {
					if (txtIsbn.getText().matches("\\d{13}") &&
							!txtTitulo.getText().isEmpty() &&
							!txtAutor.getText().isEmpty() &&
							txtNumPaginas.getText().matches("\\d{1,9}") &&
							(chkCartone.isSelected() || chkRustica.isSelected() || chkGrapado.isSelected() || chkEspiral.isSelected()) &&
							(optNovedad.isSelected() || optReedicion.isSelected())) {
						cambiarNombreButton(btnEditar, "Editar");
						estanteria.editarLibro(recogerDatosPantalla());
						dlmNombres.set(listLibros.getSelectedIndex(), (txtTitulo.getText() + " - " + txtIsbn.getText()));
						listLibros.repaint();
						deshabilitarList(true, listLibros);
						deshabilitarButton(true, btnAlta, btnBaja);
						deshabilitarText(false, txtTitulo, txtAutor, txtNumPaginas);
						deshabilitarCombo(false, comboTemas);
						deshabilitarCheck(false, chkCartone, chkRustica, chkGrapado, chkEspiral);
						deshabilitarOption(false, optNovedad, optReedicion);
						escribirMensaje(Mensajes.EDICIONCORRECTA);
					} else escribirMensaje(Mensajes.ERRORVALIDACION);
					
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
					Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 18);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 15);
					Font fuenteMensaje = new Font("Tahoma", Font.PLAIN, 12);
					cambiarFuente(fuenteTitulo, fuenteComponente, fuenteMensaje);
					txtIsbn.setMaximumSize(new Dimension(120, 200000000));
					txtIsbn.setMinimumSize(new Dimension(120, 5));
					txtNumPaginas.setMaximumSize(new Dimension(85, 200000000));
					txtNumPaginas.setMinimumSize(new Dimension(85, 5));
					txtEjemplares.setMaximumSize(new Dimension(85, 200000000));
					txtEjemplares.setMinimumSize(new Dimension(85, 5));
				} else {
					Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 12);
					Font fuenteComponente = new Font("Tahoma", Font.PLAIN, 11);
					Font fuenteMensaje = new Font("Tahoma", Font.PLAIN, 11);
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
				chkCartone.setFont(fuenteComponente);
				chkRustica.setFont(fuenteComponente);
				chkGrapado.setFont(fuenteComponente);
				chkEspiral.setFont(fuenteComponente);
				optNovedad.setFont(fuenteComponente);
				optReedicion.setFont(fuenteComponente);
				btnEditar.setFont(fuenteComponente);
				btnNuevo.setFont(fuenteComponente);
				btnBaja.setFont(fuenteComponente);
				btnAlta.setFont(fuenteComponente);
				btnSalir.setFont(fuenteComponente);
			}
		});
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
		libro.setFormato(0, chkCartone.isSelected() ? true : false);
		libro.setFormato(1, chkRustica.isSelected() ? true : false);
		libro.setFormato(2, chkGrapado.isSelected() ? true: false);
		libro.setFormato(3, chkEspiral.isSelected() ? true: false);
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
		chkCartone.setSelected(false);
		chkRustica.setSelected(false);
		chkGrapado.setSelected(false);
		chkEspiral.setSelected(false);
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
		chkCartone.setSelected(libro.getFormato(0));
		chkRustica.setSelected(libro.getFormato(1));
		chkGrapado.setSelected(libro.getFormato(2));
		chkEspiral.setSelected(libro.getFormato(2));
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

	
	
	private void cambiarNombreButton(JButton button, String nombre) {
		button.setText(nombre);
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

	private void deshabilitarButton(Boolean activado, JButton... jLists) {
		for (JButton jButton : jLists) {
			jButton.setEnabled(activado);
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
