package controlador;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import modelo.*;
import modelo.algoritmos.*;

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
	
	public Integer [] obtenerListaE() {
		return listaE;
	}
	
	public void iniciarHilo() {
		thread.start();
	}
	
	@Override
	public void run() {
		System.out.println("La lista va a ser ordenada");
		cargarListaOrdenada();
		dialog.setVisible(false);
		dialog.removeAll();
	}

}
