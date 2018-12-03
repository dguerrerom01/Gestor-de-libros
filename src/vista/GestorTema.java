package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class GestorTema extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestorTema dialog = new GestorTema();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestorTema() {
		setTitle("Gestor de temas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		{
			JLabel lblTema = new JLabel("Tema:");
			contentPanel.add(lblTema, "flowx,cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				JList list = new JList();
				scrollPane.setViewportView(list);
			}
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 0 0,growx");
			textField.setColumns(10);
		}
		{
			JButton btnGuardar = new JButton("Guardar");
			contentPanel.add(btnGuardar, "cell 0 0,alignx right");
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "[28px,grow][49px][71px]", "[25px]"));
			{
				JLabel lblMensaje = new JLabel("Seleccione un tema o creelo");
				lblMensaje.setHorizontalAlignment(SwingConstants.RIGHT);
				buttonPane.add(lblMensaje, "cell 0 0,alignx left,aligny center");
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, "cell 1 0,alignx left,aligny top");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "cell 2 0,alignx left,aligny top");
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnArchivo = new JMenu("Archivo");
				menuBar.add(mnArchivo);
				{
					JMenuItem mntmAgregarTema = new JMenuItem("Agregar tema");
					mnArchivo.add(mntmAgregarTema);
				}
				{
					JMenuItem mntmEditarTema = new JMenuItem("Editar tema");
					mnArchivo.add(mntmEditarTema);
				}
				{
					JMenuItem mntmEliminarTema = new JMenuItem("Eliminar tema");
					mnArchivo.add(mntmEliminarTema);
				}
				{
					JMenuItem mntmCerrar = new JMenuItem("Cerrar");
					mnArchivo.add(mntmCerrar);
				}
			}
		}
	}

}
