package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;

import vista.Aplicacion;
import modelo.*;
import modelo.algoritmos.BusquedaBinaria;

/**
 * Est� clase es el punto de comunicaci�n entre la vista y el controlador. 
 * Todas las acciones que el usuario solicite ser�n tramitadas desde ac� y har�n uso de las clases creadas en el modelo.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 *
 */

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
		establecerFiltro();
	}

	/**
	 * Est� funci�n establece la acci�n que har� cada bot�n despu�s de que el usuario de click sobre este.
	 */
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
				cargarTiempos();
			}
		});

		aplicacion.botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aplicacion.textoLista.getDocument().getLength() > 0) {
					buscarElemento();
				} else {
					String mensaje = "No hay lista para poder realizar la b�squeda";
					JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operaci�n no v�lida",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
	}

	/**
	 * Funci�n en la cual se abre un cuadro de di�logo que le permite al usuario seleccionar el archivo que desea cargar.
	 * El usuario puede seleccionar archivos con extensi�n .txt o .csv
	 * El archivo es cargado y asignado a la variable "archivo".
	 * Al cargar el texto, se iniciar� un hilo secundario haciendo uso de la clase CargarArchivo, con el prop�sito de no bloquear la ventana al usuario
	 * mientras se realiza la carga.
	 */
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

			JOptionPane jPane = new JOptionPane("El archivo est� siendo cargado");
			JDialog dialog = jPane.createDialog(null, "Cargando archivo...");
			jPane.setOptions(new Object[] {}); // remueve todas las opciones de cuadro de di�logo
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);

			CargarArchivo cargarArchivo = new CargarArchivo(archivo, archivoSeleccionado.getAbsolutePath(),
					Thread.currentThread());
			cargarArchivo.establecerDatosLista(datos, aplicacion.textoLista, dialog);
			cargarArchivo.iniciarHilo();
			archivo = cargarArchivo.obtenerArchivo(); // posible redundancia de asignaci�n

			System.out.println("Archivo " + archivoSeleccionado.getAbsolutePath() + " cargado");
		}
	}
	
	/**
	 * Funci�n para calcular tiempos de ejecuci�n por algoritmo de ordenamiento
	 */
	private void cargarTiempos() {
		if(!aplicacion.textoLista.getText().isEmpty()) {
			Tiempos tiempos = new Tiempos();
			JOptionPane jPane = new JOptionPane(" ");
			// Uso de dialog con ProgressBar basado en:
			// http://www.java2s.com/Tutorial/Java/0240__Swing/Creatingamodalprogressdialog.htm
			JDialog dialog = jPane.createDialog(null, "Calculando tiempos...");
			jPane.setOptions(new Object[] {}); // remueve todas las opciones de cuadro de di�logo
			//JDialog dialog = new JDialog(null,"Calculando tiempos...",true);
			JProgressBar progreso = new JProgressBar(0,100);
			dialog.add(progreso);
			dialog.setModal(false);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setSize(300, 75);
			tiempos.establecerValoresLista(aplicacion.tiemposTexto, archivo, dialog, progreso);
			tiempos.iniciarHilo();
		}
		else {
			String mensaje = "No se puede calcular tiempos porque no se ha cargado ning�n dato";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operaci�n no v�lida", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Funci�n para buscar un elemento en la lista cargada.
	 * El proceso de b�squeda se realizar� solo si la lista ha sido ordenada, con el prop�sito de agilizar la b�squeda.
	 * Despu�s de realizada la b�squeda, un mensaje ser� mostrado al usuario indicando el exito o fracaso de la b�squeda.
	 * Si la b�squeda es exitosa, en el mensaje aparecer� la posici�n n�merica en la que se encuentra el valor ingresado.
	 */
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
			String mensaje = "La lista a�n no se ha ordenado para poder realizar la b�squeda";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operaci�n no v�lida", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Funci�n para ordenar la lista cargada y actualizar el �rea de texto en la ventana del usuario.
	 * Similar al proceso de carga, la lista es ordenada y el �rea de texto se actualiza, todo este proceso es realizado en un hilo 
	 * secundario inicializado desde la clase OrdenarLista, con el objetivo de no bloquear la ventana al usuario mientras este proceso se realiza. 
	 */
	private void cargarListaOrdenada() {
		if (aplicacion.textoLista.getText() != null && !aplicacion.textoLista.getText().toString().isEmpty()) {

			System.out.println("Ordenar lista");

			JOptionPane jPane = new JOptionPane("La lista est� siendo ordenada");
			JDialog dialog = jPane.createDialog(null, "Ordenando lista...");
			jPane.setOptions(new Object[] {}); // remueve todas las opciones de cuadro de di�logo
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
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operaci�n no v�lida", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * Funci�n para limitar los caracteres que pueden ser ingresados por el usuario cuando va a realizar una b�squeda.
	 */
	private void establecerFiltro() {
		PlainDocument doc = (PlainDocument) aplicacion.textoBuscar.getDocument();
		doc.setDocumentFilter(new FiltroNumeros());
	}

	/**
	 * Funci�n para guardar la lista ordenada en un arhivo .txt o .csv.
	 * Se carga una ventana auxiliar para que el usuario seleccione la carpeta en la que desea guardar el archivo, establece un nombre
	 * y el archivo es escrito en la ubicaci�n elegida.
	 */
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
			String mensaje = "La lista no ha sido ordenada a�n";
			JOptionPane.showMessageDialog(aplicacion.jframe, mensaje, "Operaci�n no v�lida", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
