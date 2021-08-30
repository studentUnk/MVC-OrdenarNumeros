package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import vista.Aplicacion;
import modelo.*;
import modelo.algoritmos.BusquedaBinaria;

public class Controlador {

	// Vista
	private Aplicacion aplicacion;
	// Modelo
	private Archivo archivo;
	private ArrayList<String> datos;
	private Integer[] listaE;
	// Controlador
	private OrdenarLista ordenarLista;

	public Controlador(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		archivo = new Archivo();
	}

	public void accionBotones() {
		aplicacion.botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTexto();
			}
		});

		aplicacion.botonGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarTexto();
			}
		});

		aplicacion.botonOrdenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarListaOrdenada();
			}
		});
		
		aplicacion.botonPrueba.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = "No hay ninguna acción por el momento...";
				JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
			}
		});

		aplicacion.botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aplicacion.textoLista.getDocument().getLength() > 0) {
					buscarElemento();
				} else {
					String mensaje = "No hay lista para poder realizar la búsqueda";
					JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operación no válida",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
	}

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
		if (elegirArchivo.showOpenDialog(aplicacion.jframe) == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = elegirArchivo.getSelectedFile();

			JOptionPane jPane = new JOptionPane("El archivo está siendo cargado");
			JDialog dialog = jPane.createDialog(null, "Cargando archivo...");
			jPane.setOptions(new Object[] {}); // remueve todas las opciones de cuadro de diálogo
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);

			CargarArchivo cargarArchivo = new CargarArchivo(archivo, archivoSeleccionado.getAbsolutePath(),
					Thread.currentThread());
			cargarArchivo.establecerDatosLista(datos, aplicacion.textoLista, dialog);
			cargarArchivo.iniciarHilo();
			archivo = cargarArchivo.obtenerArchivo(); // posible redundancia de asignación

			System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " cargado");
		}
	}

	private void buscarElemento() {
		if (ordenarLista != null) { // validar si la lista ya fue ordenada
			listaE = ordenarLista.obtenerListaE();
		}
		if (listaE != null && aplicacion.textoBuscar.getText().length() > 0) {
			System.out.println("Busqueda iniciada");
			BusquedaBinaria bB = new BusquedaBinaria();
			int posicion = bB.busquedaBinaria(listaE, Integer.parseInt(aplicacion.textoBuscar.getText()), 2);
			System.out.println(
					"Elemento " + aplicacion.textoBuscar.getText() + " esta en linea " + Integer.toString(posicion));
			String mensaje = "Elemento " + aplicacion.textoBuscar.getText();
			if (posicion > 0) {
				mensaje = mensaje + " esta en la posicion " + Integer.toString(posicion);
			} else {
				mensaje = mensaje + " no encontrado";
			}
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Resultado", JOptionPane.PLAIN_MESSAGE);
		} else {
			String mensaje = "La lista aún no se ha ordenado para poder realizar la búsqueda";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void cargarListaOrdenada() {
		if (aplicacion.textoLista.getText() != null && !aplicacion.textoLista.getText().toString().isEmpty()) {

			System.out.println("Ordenar lista");

			JOptionPane jPane = new JOptionPane("La lista está siendo ordenada");
			JDialog dialog = jPane.createDialog(null, "Ordenando lista...");
			jPane.setOptions(new Object[] {}); // remueve todas las opciones de cuadro de diálogo
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);

			// OrdenarLista ordenarLista = new OrdenarLista();
			ordenarLista = new OrdenarLista();
			ordenarLista.establecerValoresLista(aplicacion.textoLista, listaE, archivo, dialog);
			ordenarLista.iniciarHilo();

			// this.listaE = ordenarLista.obtenerListaE();

			aplicacion.jpanelIzquierdo.repaint();
			aplicacion.jpanelSuperior.repaint();
		} else {
			String mensaje = "No hay ninguna lista para ordenar";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void guardarTexto() {
		// if(listaE != null) { // Validar que la lista ha sido ordenada
		if (ordenarLista != null) {
			listaE = ordenarLista.obtenerListaE();
		}
		if (listaE != null) { // Validar que la lista ha sido ordenada
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
			if (elegirArchivo.showSaveDialog(aplicacion.jframe) == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = elegirArchivo.getSelectedFile();
				String tipoArchivo = elegirArchivo.getFileFilter().getDescription();
				archivo.escribir(archivoSeleccionado.getAbsolutePath(), tipoArchivo, listaE);
				System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " guardado");
			}
		} else {
			String mensaje = "La lista no ha sido ordenada aún";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operación no válida", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
