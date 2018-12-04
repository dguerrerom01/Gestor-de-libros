package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import utiles.Mensajes;

public class GestorTema extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtTema;
	protected JButton btnGuardar;
	protected JList listTemas;
	protected JMenuItem mntmAgregarTema;
	protected JMenuItem mntmEditarTema;
	protected JMenuItem mntmEliminarTema;
	protected DefaultListModel<String> dlmTemas = new DefaultListModel<>();
	protected JLabel lblMensaje;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			GestorTema dialog = new ParaGestorTema(null, true);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GestorTema(Frame parent, boolean modal) {
		super(parent, modal);
		setTitle("Gestor de temas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		{
			JLabel lblTema = new JLabel("Tema:");
			contentPanel.add(lblTema, "flowx,cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				listTemas = new JList(dlmTemas);
				scrollPane.setViewportView(listTemas);
			}
		}
		{
			txtTema = new JTextField();
			contentPanel.add(txtTema, "cell 0 0,growx");
			txtTema.setColumns(10);
		}
		{
			btnGuardar = new JButton("Guardar");
			btnGuardar.setVisible(false);
			contentPanel.add(btnGuardar, "cell 0 0,alignx right");
		}
		{
			lblMensaje = new JLabel(Mensajes.MENSAJEGESTORTEMAS);
			contentPanel.add(lblMensaje, "cell 0 2");
			lblMensaje.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnArchivo = new JMenu("Archivo");
				menuBar.add(mnArchivo);
				{
					mntmAgregarTema = new JMenuItem("Agregar tema");
					mnArchivo.add(mntmAgregarTema);
				}
				{
					mntmEditarTema = new JMenuItem("Editar tema");
					mntmEditarTema.setEnabled(false);
					mnArchivo.add(mntmEditarTema);
				}
				{
					mntmEliminarTema = new JMenuItem("Eliminar tema");
					mnArchivo.add(mntmEliminarTema);
				}
			}
		}
	}

}
