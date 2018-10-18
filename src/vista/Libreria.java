package vista;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;

public class Libreria extends JFrame {

	protected JPanel contentPane;
	protected JSplitPane splitPane;
	protected JTextField txtIsbn;
	protected JTextField txtTitulo;
	protected JTextField txtAutor;
	protected JTextField txtNumPaginas;
	protected JTextField txtEjemplares;
	protected JComboBox comboTemas;
	protected JButton btnNuevo;
	protected JButton btnBaja;
	protected JButton btnAlta;
	protected JButton btnSalir;
	protected JButton btnEditar;
	protected JLabel lblLibreria;
	protected JLabel lblIsbn;
	protected JLabel lblTitulo;
	protected JLabel lblTema;
	protected JLabel lblAutor;
	protected JLabel lblNumPaginas;
	protected JLabel lblEjemplares;
	protected JLabel lblLibros;
	protected JLabel lblMensaje;
	protected JRadioButton optNovedad;
	protected JRadioButton optReedicion;
	protected JCheckBox chkTapaDura;
	protected JCheckBox chkRustica;
	protected JCheckBox chkCartone;
	protected JList listLibros;
	
	protected DefaultListModel<String> dlmNombres = new DefaultListModel<>();
	protected int fuenteTitulo = 12;
	protected int fuenteComponente = 11;
	protected int fuenteMensaje = 11;

	
	public Libreria() {
		addWindowListener(exitListener);
		setTitle("Gestor de libros");
		MigLayout layout = new MigLayout("insets 10");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		setBounds(100, 100, 575, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 5, 0, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[229px,grow][92px,grow]", "[16px][155.00px,grow,shrink 10,fill][238px,grow,fill][156px,grow,fill][16px]"));
		
		lblLibreria = new JLabel("Librer\u00EDa");
		lblLibreria.setFont(new Font("Tahoma", Font.BOLD, fuenteTitulo));
		contentPane.add(lblLibreria, "cell 0 0 2 1,alignx center,aligny center");
		
		JPanel panelDatos = new JPanel();
		contentPane.add(panelDatos, "cell 0 1 1 2,grow");
		panelDatos.setLayout(new MigLayout("", "[45px,grow]", "[grow 10,shrink 1][grow 10,shrink 1][grow 10,shrink 1][grow 10,shrink 1][grow 10,shrink 1]"));
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblIsbn, "flowx,cell 0 0,alignx left,aligny center");
		
		lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblTitulo, "flowx,cell 0 1,alignx right,aligny center");
		
		lblTema = new JLabel("Tema:");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblTema, "flowx,cell 0 2,alignx right,aligny center");
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblAutor, "flowx,cell 0 3,alignx right,aligny center");
		
		lblNumPaginas = new JLabel("<html>N\u00FAmero<br>p\u00E1ginas:</html>");
		lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblNumPaginas, "flowx,cell 0 4,alignx left,aligny center");
		
		txtIsbn = new JTextField();
		txtIsbn.setMaximumSize(new Dimension(100, 2147483647));
		txtIsbn.setMinimumSize(new Dimension(80, 5));
		txtIsbn.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(txtIsbn, "cell 0 0,alignx left,aligny center");
		txtIsbn.setColumns(10);
		
		txtNumPaginas = new JTextField();
		txtNumPaginas.setMaximumSize(new Dimension(65, 2147483647));
		txtNumPaginas.setMinimumSize(new Dimension(65, 5));
		txtNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(txtNumPaginas, "cell 0 4,alignx left,aligny center");
		txtNumPaginas.setColumns(10);
		
		txtAutor = new JTextField();
		txtAutor.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(txtAutor, "cell 0 3,growx,aligny center");
		txtAutor.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(txtTitulo, "cell 0 1,growx,aligny center");
		txtTitulo.setColumns(10);
		
		comboTemas = new JComboBox();
		comboTemas.setModel(new DefaultComboBoxModel(new String[] {"Acci\u00F3n", "Aventuras", "Biograf\u00EDa", "Ciencia", "Ciencia Ficci\u00F3n", "Cine", "Econom\u00EDa", "Gastronom\u00EDa", "Historia", "Inform\u00E1tica", "Medicina", "Misterio", "Naturaleza", "Polic\u00EDaco", "Pol\u00EDtica", "Rom\u00E1ntica", "Teatro", "Terror"}));
		comboTemas.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(comboTemas, "cell 0 2,growx,aligny center");
		
		lblEjemplares = new JLabel("Ejemplares:");
		lblEjemplares.setVisible(false);
		lblEjemplares.setEnabled(false);
		lblEjemplares.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(lblEjemplares, "cell 0 4,alignx left,aligny center");
		
		txtEjemplares = new JTextField();
		txtEjemplares.setVisible(false);
		txtEjemplares.setMinimumSize(new Dimension(65, 5));
		txtEjemplares.setEnabled(false);
		txtEjemplares.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		txtEjemplares.setEditable(false);
		txtEjemplares.setMaximumSize(new Dimension(65, 2147483647));
		panelDatos.add(txtEjemplares, "cell 0 4,alignx left,aligny center");
		txtEjemplares.setColumns(10);
		
		JLabel dummy = new JLabel(" ");
		panelDatos.add(dummy, "cell 0 0,growx");
		
		btnEditar = new JButton("Editar");
		btnEditar.setVisible(false);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelDatos.add(btnEditar, "cell 0 0,alignx right,aligny center");
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, "cell 1 1 1 3,grow");
		
		JPanel panelCaracteristicas = new JPanel();
		splitPane.setLeftComponent(panelCaracteristicas);
		panelCaracteristicas.setLayout(new MigLayout("", "[grow]", "[grow 10,shrink 1,fill][grow 10,shrink 1,fill]"));
		
		JPanel panelFormato = new JPanel();
		panelFormato.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelFormato.setBorder(new TitledBorder(null, "Formato", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCaracteristicas.add(panelFormato, "cell 0 0,grow");
		panelFormato.setLayout(new MigLayout("", "[]", "[grow 10,shrink 1,fill][grow 10,shrink 1,fill][grow 10,shrink 1,fill]"));
		
		chkCartone = new JCheckBox("Carton\u00E9");
		chkCartone.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelFormato.add(chkCartone, "cell 0 0");
		
		chkRustica = new JCheckBox("R\u00FAstica");
		chkRustica.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelFormato.add(chkRustica, "cell 0 1");
		
		chkTapaDura = new JCheckBox("Tapa dura");
		chkTapaDura.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelFormato.add(chkTapaDura, "cell 0 2");
		
		JPanel panelEsado = new JPanel();
		panelEsado.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		panelCaracteristicas.add(panelEsado, "cell 0 1,grow");
		panelEsado.setLayout(new MigLayout("", "[]", "[grow,fill][grow,fill]"));
		
		optNovedad = new JRadioButton("Novedad");
		optNovedad.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelEsado.add(optNovedad, "cell 0 0");
		
		optReedicion = new JRadioButton("Reedici\u00F3n");
		optReedicion.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelEsado.add(optReedicion, "cell 0 1");
		
		JPanel panelLibros = new JPanel();
		splitPane.setRightComponent(panelLibros);
		panelLibros.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		lblLibros = new JLabel("Libros:");
		lblLibros.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelLibros.add(lblLibros, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibros.add(scrollPane, "cell 0 1,grow");
		
		listLibros = new JList(dlmNombres);
		listLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listLibros);
		listLibros.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		listLibros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panelControl = new JPanel();
		contentPane.add(panelControl, "cell 0 3,grow");
		panelControl.setLayout(new MigLayout("", "[grow,right][grow,left]", "[grow,bottom][grow,top]"));
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelControl.add(btnNuevo, "flowy,cell 0 0,growx");
		
		btnBaja = new JButton("Baja");
		btnBaja.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelControl.add(btnBaja, "flowx,cell 1 0,growx");
		
		btnAlta = new JButton("Alta");
		btnAlta.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelControl.add(btnAlta, "cell 0 1,growx");
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		panelControl.add(btnSalir, "cell 1 1,growx");
		
		lblMensaje = new JLabel("mensaje");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, fuenteComponente));
		contentPane.add(lblMensaje, "flowx,cell 0 4 2 1,growx,aligny center");
		
	}
	
	WindowListener exitListener = new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
	    	int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (seleccion == JOptionPane.YES_OPTION){
	        	System.exit(EXIT_ON_CLOSE);
			}
	    }
	};

}
