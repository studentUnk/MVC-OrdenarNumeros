package mvcSort;

//import mvcSort.Archivo;
import algoritmos.*;

//import java.awt.Color;
import java.awt.Dimension;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
//import javax.swing.text.Element;
import javax.swing.JFileChooser;

public class Aplicacion {

	private JFrame jframe;

	private JPanel jpanelIzquierdo, jpanelDerecho, jpanelSuperior, jpanelInferior, jpanel;
	private JButton botonCargar, botonOrdenar, botonBuscar, botonGuardar;
	private JScrollPane scrollTextoLista;
	private JTextArea textoLista;
	private JLabel accion;
	// private JTextArea textoLinea;
	private JTextField textoBuscar;
	private Archivo archivo;
	private ArrayList<String> datos;
	private Integer[] listaE;
	private OrdenarLista ordenarLista;

	Aplicacion() {
		archivo = new Archivo();
		textoLista = new JTextArea();
		scrollTextoLista = new JScrollPane(textoLista);
		accion = new JLabel("");
		// scrollTextoLista.setRowHeaderView(textoLinea);
	}

	private void buscarElemento() {
		if(ordenarLista != null) { // validar si la lista ya fue ordenada
			listaE = ordenarLista.obtenerListaE();
		}
		if(listaE != null && textoBuscar.getText().length() > 0) {
			System.out.println("Busqueda iniciada");
			BusquedaBinaria bB = new BusquedaBinaria();
			int posicion = bB.busquedaBinaria(listaE, Integer.parseInt(textoBuscar.getText()), 2);
			System.out.println("Elemento " + textoBuscar.getText() + " esta en linea " + Integer.toString(posicion));
			String mensaje = "Elemento " + textoBuscar.getText();
			if (posicion > 0) {
				mensaje = mensaje + " esta en la posicion " + Integer.toString(posicion);
			} else {
				mensaje = mensaje + " no encontrado";
			}
			JOptionPane.showMessageDialog(jframe, mensaje, "Resultado", JOptionPane.PLAIN_MESSAGE);
		}else {
			String mensaje = "La lista aún no se ha ordenado para poder realizar la búsqueda";
			JOptionPane.showMessageDialog(jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
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
		botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTexto();
			}
		});
		botonGuardar = new JButton("Guardar lista");
		botonGuardar.setMaximumSize(new Dimension(250, botonGuardar.getPreferredSize().height));
		botonGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarTexto();
			}
		});
		botonOrdenar = new JButton("Ordenar lista");
		botonOrdenar.setMaximumSize(new Dimension(250, botonOrdenar.getPreferredSize().height));
		botonOrdenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarListaOrdenada();
			}
		});
		botonBuscar = new JButton("Buscar");
		botonBuscar.setMaximumSize(new Dimension(250, botonBuscar.getPreferredSize().height));
		botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textoLista.getDocument().getLength() > 0) {
					buscarElemento();
				}else {
					String mensaje = "No hay lista para poder realizar la búsqueda";
					JOptionPane.showMessageDialog(jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		// Agregar elementos al panel derecho
		jpanelDerecho.add(botonCargar);
		jpanelDerecho.add(botonGuardar);
		jpanelDerecho.add(botonOrdenar);
		jpanelDerecho.add(botonBuscar);
	}

	private void cargarListaOrdenada() {
		if (textoLista.getText() != null && !textoLista.getText().toString().isEmpty()) {
			
			System.out.println("Ordenar lista");
			
			JOptionPane jPane = new JOptionPane("La lista está siendo ordenada");
			JDialog dialog = jPane.createDialog(null,"Ordenando lista...");
			jPane.setOptions(new Object[]{}); // remueve todas las opciones de cuadro de diálogo
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
			
			//OrdenarLista ordenarLista = new OrdenarLista();
			ordenarLista = new OrdenarLista();
			ordenarLista.establecerValoresLista(textoLista, listaE, archivo, dialog);
			ordenarLista.iniciarHilo();
			
			//this.listaE = ordenarLista.obtenerListaE();
			
			jpanelIzquierdo.repaint();
			jpanelSuperior.repaint();
		}else {
			String mensaje = "No hay ninguna lista para ordenar";
			JOptionPane.showMessageDialog(jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/*private void establecerListaE(Integer [] listaE) {
		this.listaE = listaE;
	}*/

	private void cargarTexto() {
		JFileChooser elegirArchivo = new JFileChooser();
		elegirArchivo.setDialogTitle("Cargar lista");
		elegirArchivo.setCurrentDirectory(new File(System.getProperty("user.home")));
		String[] extension = archivo.obtenerTipoArchivo();
		FileFilter[] filtro = new FileFilter[extension.length];
		for (int i = 0; i < extension.length; i++) {
			filtro[i] = new FileNameExtensionFilter(extension[i], extension[i]);
			elegirArchivo.addChoosableFileFilter(filtro[i]);
		}
		// FileFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt");
		elegirArchivo.setAcceptAllFileFilterUsed(false);
		// elegirArchivo.setFileFilter(filtro);
		if (elegirArchivo.showOpenDialog(jframe) == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = elegirArchivo.getSelectedFile();
						
			JOptionPane jPane = new JOptionPane("El archivo está siendo cargado");
			JDialog dialog = jPane.createDialog(null,"Cargando archivo...");
			jPane.setOptions(new Object[]{}); // remueve todas las opciones de cuadro de diálogo
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
						
			CargarArchivo cargarArchivo = new CargarArchivo(archivo, archivoSeleccionado.getAbsolutePath(), Thread.currentThread());
			cargarArchivo.establecerDatosLista(
					datos, 
					textoLista,
					dialog);
			cargarArchivo.iniciarHilo();
			archivo = cargarArchivo.obtenerArchivo(); // posible redundancia de asignación		
			
			System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " cargado");			
		}
	}

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
		//jpanelInferior.setLayout(new FlowLayout());
		seccionInferior();
		
		//jpanel.add(jpanelIzquierdo);
		//jpanel.add(jpanelDerecho);
		jpanel.add(jpanelSuperior);
		jpanel.add(jpanelInferior);

		jframe.setContentPane(jpanel);
		jframe.pack();
		jframe.setLocationRelativeTo(null); // ubicar en el centro del escritorio
		jframe.setVisible(true);
	}

	private void guardarTexto() {
		//if(listaE != null) { // Validar que la lista ha sido ordenada
		if(ordenarLista != null) {
			listaE = ordenarLista.obtenerListaE();
		}
		if(listaE != null) { // Validar que la lista ha sido ordenada
			JFileChooser elegirArchivo = new JFileChooser();
			elegirArchivo.setDialogTitle("Guardar Lista");
			elegirArchivo.setCurrentDirectory(new File(System.getProperty("user.home")));
			String[] extension = archivo.obtenerTipoArchivo();
			FileFilter[] filtro = new FileFilter[extension.length];
			for (int i = 0; i < extension.length; i++) {
				filtro[i] = new FileNameExtensionFilter(extension[i], extension[i]);
				elegirArchivo.addChoosableFileFilter(filtro[i]);
			}
			// FileFilter filtro = new FileNameExtensionFilter("txt", "txt");
			elegirArchivo.setAcceptAllFileFilterUsed(false);
			// elegirArchivo.setFileFilter(filtro);
			if (elegirArchivo.showSaveDialog(jframe) == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = elegirArchivo.getSelectedFile();
				String tipoArchivo = elegirArchivo.getFileFilter().getDescription();
				archivo.escribir(archivoSeleccionado.getAbsolutePath(), tipoArchivo, listaE);
				System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " guardado");
			}
		}else {
			String mensaje = "La lista no ha sido ordenada aún";
			JOptionPane.showMessageDialog(jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void introducirBusqueda() {
		textoBuscar = new JTextField();
		textoBuscar.setMaximumSize(new Dimension(250, textoBuscar.getPreferredSize().height));
		jpanelDerecho.add(textoBuscar);
	}

	/*private void mostrarLista() {
		datos = archivo.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			String enumeracion = String.format("%09d", i + 1);
			textoLista.append(enumeracion + "->" + datos.get(i).toString() + "\n");
		}
	}*/
	
	private void seccionInferior() {
		//accion.setText("El archivo es increiblemente grande");
		jpanelInferior.add(accion);
	}

	private void seccionDerecha() {
		cargarBotones();
		introducirBusqueda();
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
