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

/**
 * Esta clase contiene los botones y áreas de texto que serán cargados y visualizados por el usuario.
 * El diseño utilizado es de dos secciones en la parte superior de la ventana, y una sección en la parte inferior.
 * En la parte superior se encuentran los botones y el area de texto en la cual será cargada la lista de datos.
 * En la parte inferior se mostrará los datos de tiempos de rendimiento de cada uno de los algoritmos de ordenamiento ubicados en modelo.algoritmos.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 *
 */
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

	/**
	 * En esta función se crean los botones y estos son cargados a la ventana. Los botones son creados con un tamaño en especifico. 
	 * El ancho que obtienen es el máximo del ancho que tienen permitido tomar. Después de creados son agregados al panel superior-derecho.
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

	/**
	 * En está función se crea todo el esqueleto, más los componentes de cada una de las partes que serán cargadas en la ventana.
	 * Los componentes son cargados en un panel, que a su vez son cargados en otro panel, que finalmente es cargado en un frame el cual es lanzado
	 * y visualizado por el usuario.
	 * @param titulo Titulo de la ventana
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

	/**
	 * Función que carga el área en la cual el usuario puede ingresar el dato de búsqueda de una variable en específico que pueda 
	 * estar en la lista cargada.
	 */
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

	/**
	 * Función en proceso!!!!
	 */
	private void seccionInferior() {
		// accion.setText("El archivo es increiblemente grande");
		jpanelInferior.add(accion);
	}

	/**
	 * En está función se agrega los componentes que serán cargados en la sección superior-derecha del layout.
	 * Esta zona contiene los botones de interacción para que el usuario interactúe con la aplicación.
	 */
	private void seccionDerecha() {
		cargarBotones();
		introducirBusqueda();
		
		botonPrueba = new JButton("Tiempos");
		botonPrueba.setMaximumSize(new Dimension(250, botonPrueba.getPreferredSize().height));
		jpanelDerecho.add(botonPrueba);		
	}

	/**
	 * En está función se agregan los elementos que serán cargados en la sección superior-izquierda del layout.
	 * Acá está ubicada el área de texto con la cual el usuario puede visualizar los datos cargados.
	 */
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
