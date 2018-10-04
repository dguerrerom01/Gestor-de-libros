import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Libreria extends JFrame {

	protected JPanel contentPane;
	protected JTextField txtIsbn;
	protected JTextField txtTitulo;
	protected JTextField txtAutor;
	protected JTextField txtNumPaginas;
	protected JComboBox comboTemas;
	protected JLabel lblMensaje;
	protected JList listLibros;
	protected JCheckBox chkCartone;
	protected JCheckBox chkRustica;
	protected JCheckBox chkTapaDura;
	protected JRadioButton optNovedad;
	protected JRadioButton optReedicion;
	protected JButton btnNuevo;
	protected JButton btnBaja;
	protected JButton btnAlta;
	protected JButton btnSalir;

	protected DefaultListModel<String>dlmNombres=new DefaultListModel<>();

	/**
	 * Create the frame.
	 */
	public Libreria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 196};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLibreria = new JLabel("Librer\u00EDa");
		lblLibreria.setFont(new Font("Tahoma", Font.BOLD, 23));
		GridBagConstraints gbc_lblLibreria = new GridBagConstraints();
		gbc_lblLibreria.gridwidth = 3;
		gbc_lblLibreria.insets = new Insets(0, 0, 5, 0);
		gbc_lblLibreria.gridx = 0;
		gbc_lblLibreria.gridy = 0;
		contentPane.add(lblLibreria, gbc_lblLibreria);
		
		JPanel panelLibro = new JPanel();
		GridBagConstraints gbc_panelLibro = new GridBagConstraints();
		gbc_panelLibro.insets = new Insets(0, 0, 5, 5);
		gbc_panelLibro.fill = GridBagConstraints.BOTH;
		gbc_panelLibro.gridx = 0;
		gbc_panelLibro.gridy = 1;
		contentPane.add(panelLibro, gbc_panelLibro);
		GridBagLayout gbl_panelLibro = new GridBagLayout();
		gbl_panelLibro.columnWidths = new int[]{0, 0, 0};
		gbl_panelLibro.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelLibro.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelLibro.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelLibro.setLayout(gbl_panelLibro);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.EAST;
		gbc_lblIsbn.insets = new Insets(0, 5, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		panelLibro.add(lblIsbn, gbc_lblIsbn);
		
		txtIsbn = new JTextField();
		txtIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_txtIsbn = new GridBagConstraints();
		gbc_txtIsbn.insets = new Insets(5, 5, 5, 5);
		gbc_txtIsbn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIsbn.gridx = 1;
		gbc_txtIsbn.gridy = 0;
		panelLibro.add(txtIsbn, gbc_txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.insets = new Insets(0, 5, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panelLibro.add(lblTitulo, gbc_lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 5, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		panelLibro.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTema = new GridBagConstraints();
		gbc_lblTema.anchor = GridBagConstraints.EAST;
		gbc_lblTema.insets = new Insets(0, 5, 5, 5);
		gbc_lblTema.gridx = 0;
		gbc_lblTema.gridy = 2;
		panelLibro.add(lblTema, gbc_lblTema);
		
		comboTemas = new JComboBox();
		comboTemas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboTemas.setModel(new DefaultComboBoxModel(new String[] {"Accion", "Aventuras", "Biograf\u00EDa", "Ciencia", "Ciencia Ficci\u00F3n", "Cine", "Econom\u00EDa", "Gastronom\u00EDa", "Historia", "Inform\u00E1tica", "Medicina", "Misterio", "Naturaleza", "Polic\u00EDaco", "Pol\u00EDtica", "Rom\u00E1ntica", "Teatro", "Terror"}));
		GridBagConstraints gbc_comboTemas = new GridBagConstraints();
		gbc_comboTemas.insets = new Insets(0, 5, 5, 5);
		gbc_comboTemas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTemas.gridx = 1;
		gbc_comboTemas.gridy = 2;
		panelLibro.add(comboTemas, gbc_comboTemas);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.anchor = GridBagConstraints.EAST;
		gbc_lblAutor.insets = new Insets(0, 5, 5, 5);
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 3;
		panelLibro.add(lblAutor, gbc_lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.insets = new Insets(0, 5, 5, 5);
		gbc_txtAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAutor.gridx = 1;
		gbc_txtAutor.gridy = 3;
		panelLibro.add(txtAutor, gbc_txtAutor);
		txtAutor.setColumns(10);
		
		JLabel lblNumPaginas = new JLabel("Num. p\u00E1ginas:");
		lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNumPaginas = new GridBagConstraints();
		gbc_lblNumPaginas.anchor = GridBagConstraints.EAST;
		gbc_lblNumPaginas.insets = new Insets(0, 5, 5, 5);
		gbc_lblNumPaginas.gridx = 0;
		gbc_lblNumPaginas.gridy = 4;
		panelLibro.add(lblNumPaginas, gbc_lblNumPaginas);
		
		txtNumPaginas = new JTextField();
		txtNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_txtNumPaginas = new GridBagConstraints();
		gbc_txtNumPaginas.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumPaginas.insets = new Insets(0, 5, 5, 5);
		gbc_txtNumPaginas.gridx = 1;
		gbc_txtNumPaginas.gridy = 4;
		panelLibro.add(txtNumPaginas, gbc_txtNumPaginas);
		txtNumPaginas.setColumns(10);
		
		JPanel panelCaracteristicas = new JPanel();
		GridBagConstraints gbc_panelCaracteristicas = new GridBagConstraints();
		gbc_panelCaracteristicas.anchor = GridBagConstraints.WEST;
		gbc_panelCaracteristicas.gridheight = 2;
		gbc_panelCaracteristicas.insets = new Insets(0, 0, 5, 5);
		gbc_panelCaracteristicas.fill = GridBagConstraints.VERTICAL;
		gbc_panelCaracteristicas.gridx = 1;
		gbc_panelCaracteristicas.gridy = 1;
		contentPane.add(panelCaracteristicas, gbc_panelCaracteristicas);
		GridBagLayout gbl_panelCaracteristicas = new GridBagLayout();
		gbl_panelCaracteristicas.columnWidths = new int[]{0, 0};
		gbl_panelCaracteristicas.rowHeights = new int[]{0, 0, 0};
		gbl_panelCaracteristicas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelCaracteristicas.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelCaracteristicas.setLayout(gbl_panelCaracteristicas);
		
		JPanel panelFormato = new JPanel();
		GridBagConstraints gbc_panelFormato = new GridBagConstraints();
		gbc_panelFormato.anchor = GridBagConstraints.WEST;
		gbc_panelFormato.fill = GridBagConstraints.VERTICAL;
		gbc_panelFormato.insets = new Insets(0, 0, 5, 0);
		gbc_panelFormato.gridx = 0;
		gbc_panelFormato.gridy = 0;
		panelCaracteristicas.add(panelFormato, gbc_panelFormato);
		panelFormato.setBorder(new TitledBorder(null, "Formato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelFormato = new GridBagLayout();
		gbl_panelFormato.columnWidths = new int[]{0, 0};
		gbl_panelFormato.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelFormato.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelFormato.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelFormato.setLayout(gbl_panelFormato);
		
		chkCartone = new JCheckBox("Carton\u00E9");
		chkCartone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_chkCartone = new GridBagConstraints();
		gbc_chkCartone.anchor = GridBagConstraints.WEST;
		gbc_chkCartone.insets = new Insets(0, 3, 5, 3);
		gbc_chkCartone.gridx = 0;
		gbc_chkCartone.gridy = 0;
		panelFormato.add(chkCartone, gbc_chkCartone);
		
		chkRustica = new JCheckBox("R\u00FAstica");
		chkRustica.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_chkRustica = new GridBagConstraints();
		gbc_chkRustica.anchor = GridBagConstraints.WEST;
		gbc_chkRustica.insets = new Insets(0, 3, 5, 3);
		gbc_chkRustica.gridx = 0;
		gbc_chkRustica.gridy = 1;
		panelFormato.add(chkRustica, gbc_chkRustica);
		
		chkTapaDura = new JCheckBox("Tapa dura");
		chkTapaDura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_chkTapaDura = new GridBagConstraints();
		gbc_chkTapaDura.anchor = GridBagConstraints.WEST;
		gbc_chkTapaDura.insets = new Insets(0, 3, 5, 3);
		gbc_chkTapaDura.gridx = 0;
		gbc_chkTapaDura.gridy = 2;
		panelFormato.add(chkTapaDura, gbc_chkTapaDura);
		
		JPanel panelEstado = new JPanel();
		panelEstado.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.anchor = GridBagConstraints.WEST;
		gbc_panelEstado.fill = GridBagConstraints.VERTICAL;
		gbc_panelEstado.gridx = 0;
		gbc_panelEstado.gridy = 1;
		panelCaracteristicas.add(panelEstado, gbc_panelEstado);
		GridBagLayout gbl_panelEstado = new GridBagLayout();
		gbl_panelEstado.columnWidths = new int[]{0, 0};
		gbl_panelEstado.rowHeights = new int[]{0, 0, 0};
		gbl_panelEstado.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelEstado.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelEstado.setLayout(gbl_panelEstado);
		
		optNovedad = new JRadioButton("Novedad");
		optNovedad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		optNovedad.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_optNovedad = new GridBagConstraints();
		gbc_optNovedad.anchor = GridBagConstraints.WEST;
		gbc_optNovedad.insets = new Insets(0, 3, 5, 3);
		gbc_optNovedad.gridx = 0;
		gbc_optNovedad.gridy = 0;
		panelEstado.add(optNovedad, gbc_optNovedad);
		
		optReedicion = new JRadioButton("Reedici\u00F3n");
		optReedicion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		optReedicion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_optReedicion = new GridBagConstraints();
		gbc_optReedicion.anchor = GridBagConstraints.WEST;
		gbc_optReedicion.insets = new Insets(0, 3, 5, 3);
		gbc_optReedicion.gridx = 0;
		gbc_optReedicion.gridy = 1;
		panelEstado.add(optReedicion, gbc_optReedicion);
		
		ButtonGroup group = new ButtonGroup();
		group.add(optReedicion);
		group.add(optNovedad);
		
		JPanel panelLibros = new JPanel();
		GridBagConstraints gbc_panelLibros = new GridBagConstraints();
		gbc_panelLibros.gridheight = 2;
		gbc_panelLibros.insets = new Insets(0, 0, 5, 0);
		gbc_panelLibros.fill = GridBagConstraints.BOTH;
		gbc_panelLibros.gridx = 2;
		gbc_panelLibros.gridy = 1;
		contentPane.add(panelLibros, gbc_panelLibros);
		GridBagLayout gbl_panelLibros = new GridBagLayout();
		gbl_panelLibros.columnWidths = new int[]{0, 0};
		gbl_panelLibros.rowHeights = new int[]{0, 0, 0};
		gbl_panelLibros.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelLibros.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelLibros.setLayout(gbl_panelLibros);
		
		JLabel lblLibros = new JLabel("Libros:");
		lblLibros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblLibros = new GridBagConstraints();
		gbc_lblLibros.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLibros.insets = new Insets(0, 0, 5, 0);
		gbc_lblLibros.gridx = 0;
		gbc_lblLibros.gridy = 0;
		panelLibros.add(lblLibros, gbc_lblLibros);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelLibros.add(scrollPane, gbc_scrollPane);
		
		listLibros = new JList();
		listLibros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listLibros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(listLibros);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setPreferredSize(new Dimension(80, 25));
		btnNuevo.setMinimumSize(new Dimension(80, 25));
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
		gbc_btnNuevo.anchor = GridBagConstraints.EAST;
		gbc_btnNuevo.insets = new Insets(0, 5, 5, 5);
		gbc_btnNuevo.gridx = 0;
		gbc_btnNuevo.gridy = 0;
		panel_1.add(btnNuevo, gbc_btnNuevo);
		
		btnBaja = new JButton("Baja");
		btnBaja.setPreferredSize(new Dimension(80, 25));
		btnBaja.setMinimumSize(new Dimension(80, 25));
		btnBaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBaja = new GridBagConstraints();
		gbc_btnBaja.anchor = GridBagConstraints.WEST;
		gbc_btnBaja.insets = new Insets(0, 5, 5, 5);
		gbc_btnBaja.gridx = 1;
		gbc_btnBaja.gridy = 0;
		panel_1.add(btnBaja, gbc_btnBaja);
		
		btnAlta = new JButton("Alta");
		btnAlta.setPreferredSize(new Dimension(80, 25));
		btnAlta.setMinimumSize(new Dimension(80, 25));
		btnAlta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnAlta = new GridBagConstraints();
		gbc_btnAlta.anchor = GridBagConstraints.EAST;
		gbc_btnAlta.insets = new Insets(0, 5, 5, 5);
		gbc_btnAlta.gridx = 0;
		gbc_btnAlta.gridy = 1;
		panel_1.add(btnAlta, gbc_btnAlta);
		
		btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(new Dimension(80, 25));
		btnSalir.setMinimumSize(new Dimension(80, 25));
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.anchor = GridBagConstraints.WEST;
		gbc_btnSalir.insets = new Insets(0, 5, 5, 5);
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 1;
		panel_1.add(btnSalir, gbc_btnSalir);
		
		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
		gbc_lblMensaje.insets = new Insets(0, 5, 5, 5);
		gbc_lblMensaje.gridwidth = 3;
		gbc_lblMensaje.gridx = 0;
		gbc_lblMensaje.gridy = 3;
		contentPane.add(lblMensaje, gbc_lblMensaje);
	}

}
