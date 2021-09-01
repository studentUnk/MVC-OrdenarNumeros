package controlador;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import modelo.*;

/**
 * El propósito de esta clase es separar el proceso de carga de archivo en un hilo
 * diferente para permitir la visualización de un mensaje y no detener el flujo del hilo
 * principal.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 */

public class CargarArchivo implements Runnable {
	
	private String rutaArchivo;
	private Archivo archivo;
	public Thread thread; 
	public JDialog dialog;
	
	private JTextArea textoLista;
	private ArrayList<String> datos;
	
	public CargarArchivo(Archivo archivo, String rutaArchivo, Thread threadExterno) {
		this.archivo = archivo;
		this.rutaArchivo = rutaArchivo;
		thread = new Thread(this);
	}
	
	/**
	 * Función para cargar las variables inicializadas en el hilo principal.
	 * @param datos Lista de datos cargada
	 * @param textoLista Área de texto en el panel que será actualizado
	 * @param dialog Usada para mostrar un mensaje de dialogo al usuario
	 */
	
	public void establecerDatosLista(ArrayList<String> datos, JTextArea textoLista, JDialog dialog) {
		this.datos = datos;
		this.textoLista = textoLista;
		this.dialog = dialog;
	}
	
	/**
	 * Función para actualizar el área de texto "textoLista" imprimiendo en la pantalla
	 * un número acumulativo y una línea del archivo cargado.
	 * @param datos Lista de datos cargada
	 * @param textoLista Área de texto en el panel que será actualizado
	 */
	
	private void mostrarLista(ArrayList<String> datos, JTextArea textoLista ) {
		datos = archivo.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			String enumeracion = String.format("%09d", i + 1);
			textoLista.append(enumeracion + "->" + datos.get(i).toString() + "\n");
		}
	}
	
	/**
	 * Está función retorna la variable "archivo" en caso de que la variable en el hilo principal no se
	 * actualice adecuadamente.
	 * @return Variable archivo
	 */
	
	public Archivo obtenerArchivo() {
		return archivo;
	}
	
	/**
	 * Está función da inicio al hilo secundario.
	 */
	public void iniciarHilo() {
		thread.start();
	}

	/**
	 * Está función es el proceso que será ejecutado dentro del hilo secundario, en este caso, la carga del archivo de datos seleccionado.
	 */
	@Override
	public void run() {
		System.out.println("Archivo " + rutaArchivo + " va a ser cargado");
		archivo.leer(rutaArchivo);
		mostrarLista(datos, textoLista);
		dialog.setVisible(false);
		dialog.removeAll();
	}
}
