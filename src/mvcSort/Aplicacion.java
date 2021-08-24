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

	private JPanel jpanelIzquierdo, jpanelDerecho, jpanel;
	private JButton botonCargar, botonOrdenar, botonBuscar;
	private JScrollPane scrollTextoLista;
	private JTextArea textoLista;
	// private JTextArea textoLinea;
	private JTextField textoBuscar;
	private Archivo archivo;
	private ArrayList<String> datos;
	private Integer[] listaE;
	Aplicacion() {
		archivo = new Archivo();
		textoLista = new JTextArea();
		scrollTextoLista = new JScrollPane(textoLista);
		// scrollTextoLista.setRowHeaderView(textoLinea);
	}

	private void buscarElemento() {
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
				}
			}
		});
		jpanelDerecho.add(botonCargar);
		jpanelDerecho.add(botonOrdenar);
		jpanelDerecho.add(botonBuscar);
	}

	private void cargarListaOrdenada() {
		if (textoLista.getText() != null) {
			QuickSort qS = new QuickSort();
			listaE = archivo.convertirArrayEntero();
			qS.ordenarLista(listaE, 4);
			System.out.println("Lista ha sido ordenada");
			textoLista.selectAll();
			textoLista.replaceSelection("");
			for (int i = 0; i < listaE.length; i++) {
				String enumeracion = String.format("%09d", i + 1);
				textoLista.append(enumeracion + "->" + listaE[i].toString() + "\n");
			}
			System.out.println("Lista ordenada y cargando al panel");
			jpanelIzquierdo.repaint();
		}
	}

	private void cargarTexto() {
		JFileChooser elegirArchivo = new JFileChooser();
		elegirArchivo.setDialogTitle("Cargar lista");
		elegirArchivo.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt");
		elegirArchivo.setAcceptAllFileFilterUsed(false);
		elegirArchivo.setFileFilter(filtro);
		if (elegirArchivo.showOpenDialog(jframe) == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = elegirArchivo.getSelectedFile();
			archivo.leer(archivoSeleccionado.getAbsolutePath());
			System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " cargado");
			mostrarLista();
		}
	}

	public void crearVentana(String titulo) {
		jframe = new JFrame(titulo);
		jpanel = new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.X_AXIS));

		jpanelDerecho = new JPanel();
		jpanelDerecho.setLayout(new BoxLayout(jpanelDerecho, BoxLayout.Y_AXIS));
		jpanelDerecho.setOpaque(true);
		jpanelIzquierdo = new JPanel();
		jpanelIzquierdo.setLayout(new BoxLayout(jpanelIzquierdo, BoxLayout.Y_AXIS));

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(500, 500));

		seccionDerecha();
		seccionIzquierda();
		jpanel.add(jpanelIzquierdo);
		jpanel.add(jpanelDerecho);

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

	private void mostrarLista() {
		datos = archivo.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			String enumeracion = String.format("%09d", i + 1);
			textoLista.append(enumeracion + "->" + datos.get(i).toString() + "\n");
		}
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
