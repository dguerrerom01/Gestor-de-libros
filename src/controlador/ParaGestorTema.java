package controlador;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import modelo.Libro;
import modelo.Tema;
import utiles.Mensajes;
import vista.GestorTema;

public class ParaGestorTema extends GestorTema {

	Fachada fachada = new Fachada();

	public ParaGestorTema(Frame parent, boolean modal) {
		super(parent, modal);
		cargaDlmTemas();
		
		mntmAgregarTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtTema.getText().isEmpty()) {
					boolean duplicado = false;
					for (int i = 0; i < dlmTemas.size() && !duplicado; i++) {
						if (dlmTemas.getElementAt(i).toString().toLowerCase().equals(txtTema.getText().toLowerCase())) {
							duplicado = true;
						}
					}
					if (!duplicado) {
						Tema tema = new Tema();
						tema.setTema(txtTema.getText());
						if (fachada.insertar(tema)) {
							cargaDlmTemas();
							escribirMensaje(Mensajes.TEMAAGREGADO);
						} else escribirMensaje(Mensajes.ERRORAGREGARTEMA);
					} else escribirMensaje(Mensajes.ERRORTEMAEXISTENTE);
				}
			}
		});
		
		mntmEditarTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		mntmEliminarTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int borrarDisponible = listTemas.getSelectedIndex();
				if (borrarDisponible != -1) {
					ArrayList<Libro> libros = fachada.obtenerLibros();
					boolean bloqueado = false;
					for (Libro libro : libros) {
						if (libro.getTema().equals(listTemas.getSelectedValue().toString())) {
							bloqueado = true;
						}
					}
					if (!bloqueado) {
						int seleccion = JOptionPane.showConfirmDialog(null,
								"¿Desea eliminar el tema?",
								"Dar de baja", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (seleccion == JOptionPane.YES_OPTION) {
							Tema tema = new Tema();
							tema.setTema(listTemas.getSelectedValue().toString());
							fachada.borrar(tema);
							txtTema.setText("");
							cargaDlmTemas();
							escribirMensaje(Mensajes.TEMAELIMINADO);
						}
					}
					else escribirMensaje(Mensajes.ERRORTEMAENUSO);
				} else
					escribirMensaje(Mensajes.ERRORINDICE);
				cargaDlmTemas();
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		listTemas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});

		txtTema.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < dlmTemas.size(); i++) {
					if (dlmTemas.getElementAt(i).toString().toLowerCase().equals(txtTema.getText().toLowerCase())) {
						escribirMensaje(Mensajes.AVISOTEMAEXISTENTE);
						listTemas.setSelectedIndex(i);
					}
				}
			}
		});
		
		txtTema.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (String.valueOf(arg0.getKeyChar()).matches("[0-9]")) {
					arg0.consume();
				}
			}
		});
		
	}

	private void cargaDlmTemas() {
		dlmTemas.clear();
		for (Tema temas : fachada.obtenerTemas()) {
			dlmTemas.addElement(temas.getTema());
		}
	}
	
	private void escribirMensaje(String mensaje) {
		lblMensaje.setText(mensaje);
	}
	
}
