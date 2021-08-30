package controlador;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import modelo.*;

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
	
	public void establecerDatosLista(ArrayList<String> datos, JTextArea textoLista, JDialog dialog) {
		this.datos = datos;
		this.textoLista = textoLista;
		this.dialog = dialog;
	}
	
	private void mostrarLista(ArrayList<String> datos, JTextArea textoLista ) {
		datos = archivo.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			String enumeracion = String.format("%09d", i + 1);
			textoLista.append(enumeracion + "->" + datos.get(i).toString() + "\n");
		}
	}
	
	public Archivo obtenerArchivo() {
		return archivo;
	}
	
	public void iniciarHilo() {
		thread.start();
	}

	@Override
	public void run() {
		System.out.println("Archivo " + rutaArchivo + " va a ser cargado");
		archivo.leer(rutaArchivo);
		mostrarLista(datos, textoLista);
		dialog.setVisible(false);
		dialog.removeAll();
	}
}
