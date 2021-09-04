package controlador;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import modelo.Archivo;
import modelo.algoritmos.Burbuja;
import modelo.algoritmos.Insercion;
import modelo.algoritmos.QuickSort;
import modelo.algoritmos.Seleccion;

/**
 * Clase utilizada para calcular los tiempos de ejecuci�n de cada uno de los algoritmos.
 * El calculo realizado es hecho en un solo hilo, por lo tanto, un algoritmo ordena despu�s del otro.
 * 
 * @author Camilo J.
 * @version 1.0
 * @since 2021-09-04
 * 
 */
public class Tiempos implements Runnable {
	
	private JTextArea textoLista;
	//private Integer[] listaE;
	private Archivo archivo;
	//private Aplicacion aplicacion;
	public Thread thread;
	public JDialog dialog;
	public JProgressBar progreso;
	private JLabel[] tiemposTexto;
	private String[] algoritmos = {"Burbuja","Inserci�n","Selecci�n","QuickSort"};
	
	public Tiempos(){
		thread = new Thread(this);
	}	
	
	/**
	 * Funci�n para cargar variables requeridas en la clase.
	 * 
	 * @param textoLista Area de texto que ser� actualizada
	 * @param listaE Lista de valores organizados
	 * @param archivo Lista de datos cargados directamente del archivo seleccionado
	 * @param dialog Mensaje de dialogo emitido al usuario
	 */
	public void establecerValoresLista(
			JLabel[] tiemposTexto,
			Archivo archivo,
			JDialog dialog,
			JProgressBar progreso) {
		//this.textoLista = textoLista;
		this.tiemposTexto = tiemposTexto;
		//this.listaE = listaE;
		this.archivo = archivo;
		this.dialog = dialog;
		this.progreso = progreso;
		
		cargarLista();
	}
	
	/**
	 * Est� funci�n ordena la lista ingresada haciendo uso de los algoritmos escritos y almacenados en la carpeta modelo.algoritmos.
	 * Se har� uso del algoritmo quicsort dada su �ptimo rendimiento en relaci�n con otros algoritmos de ordenamiento:
	 * (burbuja, selecci�n, inserci�n).
	 * Finalizado el proceso de ordenamiento, el �rea de texto es actualizada con la nueva lista.
	 */
	private Integer[] cargarLista() {
		int limite = 75000;
		return archivo.convertirArrayEntero(limite);		
	}
	
	/**
	 * Funci�kn para seleccionar con que tipo de algoritmo ordenar la lista.
	 * @param algoritmo N�mero del algoritmo a seleccionar.
	 */
	private void ordenar(int algoritmo) {
		switch(algoritmo) {
			case 1: 
				System.out.println("Inicia Burbuja");
				Burbuja burbuja = new Burbuja();
				Integer[] listaB = cargarLista();
				burbuja.ordenarLista(listaB);
				break;
			case 2:
				System.out.println("Inicia Inserci�n");
				Insercion insercion = new Insercion();
				Integer[] listaI = cargarLista();
				insercion.ordenarLista(listaI);
				break;
			case 3:
				System.out.println("Inicia Selecci�n");
				Seleccion seleccion = new Seleccion();
				Integer[] listaS = cargarLista();
				seleccion.ordenarLista(listaS);
				break;
			case 4:
				System.out.println("Inicia QuickSort");
				QuickSort quickSort = new QuickSort();
				Integer[] listaQ = cargarLista();
				quickSort.ordenarLista(listaQ, 3); // punto medio
				break;
		}
	}
	
	/**
	 * Funci�n que da inicio al hilo secundario.
	 */
	public void iniciarHilo() {
		thread.start();
	}
	
	/**
	 * Funci�n que especifica el proceso que ser� realizado al dar inicio al hilo secundario, en este caso, ordenar la lista.
	 */
	@Override
	public void run() {
		System.out.println("El an�lisis de tiempos va a iniciar. M�ximo 75000 datos para analizar.");
		String [] tiemposT = new String[4];
		for(int i = 1; i <= 4; i++) {
			long start = System.currentTimeMillis();
			ordenar(i);
			long stop = System.currentTimeMillis();
			tiemposT[i-1] = Long.toString(stop-start);
			System.out.println(tiemposT[i-1]);
			progreso.setValue(i*25);
		}
		System.out.println("Analisis finalizado");
		System.out.println("Cargar tiempos en vista");
		for(int i = 0; i < tiemposTexto.length; i++) {
			String mensaje = "Tiempo para " + algoritmos[i] + " es de " + tiemposT[i] + " ms";
			//tiemposTexto[i].setText(tiemposT[i]);
			tiemposTexto[i].setText(mensaje);
		}
		//cargarListaOrdenada();
		dialog.setVisible(false);
		dialog.removeAll();
	}

}
