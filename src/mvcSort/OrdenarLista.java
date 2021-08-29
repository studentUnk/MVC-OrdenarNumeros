package mvcSort;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import algoritmos.QuickSort;

public class OrdenarLista implements Runnable {
	
	private JTextArea textoLista;
	private Integer[] listaE;
	private Archivo archivo;
	//private Aplicacion aplicacion;
	public Thread thread;
	public JDialog dialog;
	
	OrdenarLista(){
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
