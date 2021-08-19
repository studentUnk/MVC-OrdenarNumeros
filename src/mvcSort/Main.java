package mvcSort;

import algoritmos.QuickSort;

public class Main {
	public static void main(String [] args) {
		Archivo leerArchivo = new Archivo();
		leerArchivo.leer("Numeros.txt");
		//leerArchivo.imprimirDatos();
		Integer [] lista = leerArchivo.convertirArrayEntero();
		QuickSort quickSort = new QuickSort();
		quickSort.ordenarLista(lista,3);
		leerArchivo.escribir("NumerosOrdenados.txt",lista);
	}
}
