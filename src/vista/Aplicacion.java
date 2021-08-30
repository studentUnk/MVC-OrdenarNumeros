package vista;

//import java.awt.Color;
import java.awt.Dimension;
//import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.text.Element;

public class Aplicacion {

	public JFrame jframe;

	public JPanel jpanelIzquierdo, jpanelDerecho, jpanelSuperior, jpanelInferior, jpanel;
	public JButton botonCargar, botonOrdenar, botonBuscar, botonGuardar, botonPrueba;
	private JScrollPane scrollTextoLista;
	public JTextArea textoLista;
	private JLabel accion;
	// private JTextArea textoLinea;
	public JTextField textoBuscar;
	// public Archivo archivo;
	// private ArrayList<String> datos;
	// private Integer[] listaE;
	// private OrdenarLista ordenarLista;

	public Aplicacion() {
		// archivo = new Archivo();
		textoLista = new JTextArea();
		scrollTextoLista = new JScrollPane(textoLista);
		accion = new JLabel("");
		// scrollTextoLista.setRowHeaderView(textoLinea);
	}

	/*
	 * private void enumerarLista() {
	 * textoLista.getDocument().addDocumentListener(new DocumentListener() { public
	 * String getText() { int pos = textoLista.getDocument().getLength(); Element
	 * raiz = textoLista.getDocument().getDefaultRootElement(); String texto = "1" +
	 * System.getProperty("line.separator"); for(int i = 2; i <
	 * raiz.getElementIndex(pos) + 2; i++) { texto += i +
	 * System.getProperty("line.separator"); } return texto; }
	 * 
	 * @Override public void changedUpdate(DocumentEvent de) {
	 * textoLinea.setText(getText()); }
	 * 
	 * @Override public void insertUpdate(DocumentEvent de) {
	 * textoLinea.setText(getText()); }
	 * 
	 * @Override public void removeUpdate(DocumentEvent de) {
	 * textoLinea.setText(getText()); } }); }
	 */

	private void cargarBotones() {
		botonCargar = new JButton("Cargar lista");
		botonCargar.setMaximumSize(new Dimension(250, botonCargar.getPreferredSize().height));

		botonGuardar = new JButton("Guardar lista");
		botonGuardar.setMaximumSize(new Dimension(250, botonGuardar.getPreferredSize().height));

		botonOrdenar = new JButton("Ordenar lista");
		botonOrdenar.setMaximumSize(new Dimension(250, botonOrdenar.getPreferredSize().height));

		botonBuscar = new JButton("Buscar");
		botonBuscar.setMaximumSize(new Dimension(250, botonBuscar.getPreferredSize().height));

		// Agregar elementos al panel derecho
		jpanelDerecho.add(botonCargar);
		jpanelDerecho.add(botonGuardar);
		jpanelDerecho.add(botonOrdenar);
		jpanelDerecho.add(botonBuscar);
	}

	/*
	 * private void establecerListaE(Integer [] listaE) { this.listaE = listaE; }
	 */

	public void crearVentana(String titulo) {
		jframe = new JFrame(titulo);
		jpanel = new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));

		jpanelDerecho = new JPanel();
		jpanelDerecho.setLayout(new BoxLayout(jpanelDerecho, BoxLayout.Y_AXIS));
		jpanelDerecho.setOpaque(true);
		jpanelIzquierdo = new JPanel();
		jpanelIzquierdo.setLayout(new BoxLayout(jpanelIzquierdo, BoxLayout.Y_AXIS));

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(500, 500));

		seccionDerecha();
		seccionIzquierda();
		jpanelSuperior = new JPanel();
		jpanelSuperior.setLayout(new BoxLayout(jpanelSuperior, BoxLayout.X_AXIS));
		jpanelSuperior.add(jpanelIzquierdo);
		jpanelSuperior.add(jpanelDerecho);
		jpanelInferior = new JPanel();
		// jpanelInferior.setLayout(new FlowLayout());
		seccionInferior();

		// jpanel.add(jpanelIzquierdo);
		// jpanel.add(jpanelDerecho);
		jpanel.add(jpanelSuperior);
		jpanel.add(jpanelInferior);

		jframe.setContentPane(jpanel);
		jframe.pack();
		jframe.setLocationRelativeTo(null); // ubicar en el centro del escritorio
		jframe.setVisible(true);
	}

	private void introducirBusqueda() {
		textoBuscar = new JTextField();
		textoBuscar.setMaximumSize(new Dimension(250, textoBuscar.getPreferredSize().height));
		jpanelDerecho.add(textoBuscar);
	}

	/*
	 * private void mostrarLista() { datos = archivo.getDatos(); for (int i = 0; i <
	 * datos.size(); i++) { String enumeracion = String.format("%09d", i + 1);
	 * textoLista.append(enumeracion + "->" + datos.get(i).toString() + "\n"); } }
	 */

	private void seccionInferior() {
		// accion.setText("El archivo es increiblemente grande");
		jpanelInferior.add(accion);
	}

	private void seccionDerecha() {
		cargarBotones();
		introducirBusqueda();
		
		botonPrueba = new JButton("Tiempos");
		botonPrueba.setMaximumSize(new Dimension(250, botonPrueba.getPreferredSize().height));
		jpanelDerecho.add(botonPrueba);		
	}

	private void seccionIzquierda() {
		JLabel labelSeccionLista = new JLabel("Lista (posicion->elemento)");
		// textoLinea.setBackground(Color.lightGray);
		// textoLinea.setEditable(false);
		textoLista.setEditable(false);
		// enumerarLista();
		jpanelIzquierdo.add(labelSeccionLista);
		jpanelIzquierdo.add(scrollTextoLista);
	}
}
