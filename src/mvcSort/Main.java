package mvcSort;

import algoritmos.*;

public class Main {
	public static void main(String [] args) {
		Archivo leerArchivo = new Archivo();
		//leerArchivo.leer("Numeros.txt");
		leerArchivo.leer("NumerosOrdenados.txt");
		//leerArchivo.imprimirDatos();
		Integer [] lista = leerArchivo.convertirArrayEntero();
		/*QuickSort quickSort = new QuickSort();
		quickSort.ordenarLista(lista,3);
		leerArchivo.escribir("NumerosOrdenados.txt",lista);*/
		BusquedaBinaria bB = new BusquedaBinaria();
		System.out.println(bB.busquedaBinaria(lista, 520490932));
	}
}
