package controlador;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import modelo.*;
import modelo.algoritmos.*;

/**
 * El propósito de esta clase es separar el proceso de ordenamiento en un hilo
 * diferente para permitir la visualización de un mensaje y no detener el flujo del hilo
 * principal.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 */
public class OrdenarLista implements Runnable {
	
	private JTextArea textoLista;
	private Integer[] listaE;
	private Archivo archivo;
	//private Aplicacion aplicacion;
	public Thread thread;
	public JDialog dialog;
	
	public OrdenarLista(){
		thread = new Thread(this);
	}	
	
	/**
	 * Función para cargar variables requeridas en la clase.
	 * 
	 * @param textoLista Area de texto que será actualizada
	 * @param listaE Lista de valores organizados
	 * @param archivo Lista de datos cargados directamente del archivo seleccionado
	 * @param dialog Mensaje de dialogo emitido al usuario
	 */
	public void establecerValoresLista(
			JTextArea textoLista,
			Integer[] listaE,
			Archivo archivo,
			JDialog dialog) {
		this.textoLista = textoLista;
		this.listaE = listaE;
		this.archivo = archivo;
		this.dialog = dialog;
	}
	
	/**
	 * Está función ordena la lista ingresada haciendo uso de los algoritmos escritos y almacenados en la carpeta modelo.algoritmos.
	 * Se hará uso del algoritmo quicsort dada su óptimo rendimiento en relación con otros algoritmos de ordenamiento:
	 * (burbuja, selección, inserción).
	 * Finalizado el proceso de ordenamiento, el área de texto es actualizada con la nueva lista.
	 */
	private void cargarListaOrdenada() {
		
			listaE = archivo.convertirArrayEntero();
			
			QuickSort qS = new QuickSort();
			//Burbuja burbuja = new Burbuja();
			//Seleccion seleccion = new Seleccion();
			//Insercion insercion = new Insercion();
			qS.ordenarLista(listaE, 4);
			//burbuja.ordenarLista(listaE);
			//seleccion.ordenarLista(listaE);
			//insercion.ordenarLista(listaE);
			
			System.out.println("Lista ha sido ordenada");
			textoLista.selectAll();
			textoLista.replaceSelection("");
			for (int i = 0; i < listaE.length; i++) {
				String enumeracion = String.format("%09d", i + 1);
				textoLista.append(enumeracion + "->" + listaE[i].toString() + "\n");
			}
			System.out.println("Lista ordenada y cargando al panel");
			//jpanelIzquierdo.repaint();
			//jpanelSuperior.repaint();
		
	}
	
	/**
	 * Función que retorna el arreglo de enteros utilizado para ordenar la lista de datos cargada.
	 * @return Lista de enteros ordenada
	 */
	public Integer [] obtenerListaE() {
		return listaE;
	}
	
	/**
	 * Función que da inicio al hilo secundario.
	 */
	public void iniciarHilo() {
		thread.start();
	}
	
	/**
	 * Función que especifica el proceso que será realizado al dar inicio al hilo secundario, en este caso, ordenar la lista.
	 */
	@Override
	public void run() {
		System.out.println("La lista va a ser ordenada");
		cargarListaOrdenada();
		dialog.setVisible(false);
		dialog.removeAll();
	}

}
