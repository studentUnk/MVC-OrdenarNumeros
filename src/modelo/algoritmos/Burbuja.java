package modelo.algoritmos;

import java.util.Arrays;

/**
 * Clase que contiene el algoritmo de ordenamiento Burbuja.
 * 
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 * 
 */
public class Burbuja {
	
	public static void main(String[] args) {
		//Integer [] lista = new Integer[] {5,2,1,8,3,9,7};
		//Integer[] lista = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		// Integer [] lista = new Integer[] {8,1,4,9,6,3,5,2,7,0};
		Integer[] lista = new Integer[] { 79, 21, 15, 99, 88, 65, 75, 85, 76, 46, 84,
		 24 };
		Burbuja burbuja = new Burbuja();
		// quickSort.ordenar(lista, 0, lista.length - 1,
		// quickSort.seleccionarPivote(lista.length, 0, lista.length-1, 2), 2);
		burbuja.ordenarLista(lista);
		//quickSort.ordenar(lista, 0, lista.length - 1, quickSort.seleccionarPivote(0, lista.length - 1, 2), 4);
		System.out.println(Arrays.toString(lista));
	}
	
	/**
	 * Esta función es usada para intercambiar el valor asignado a una posición en el arreglo
	 * por otro en el mismo arreglo.
	 * @param lista Arreglo que se está ordenando
	 * @param pos1 Valor a
	 * @param pos2 Valor b
	 * @return Nada, ya que el cambio es realizado directamente en el arreglo
	 */
	private void intercambiar(Integer lista[], int pos1, int pos2) {
		int temp = lista[pos1];
		lista[pos1] = lista[pos2];
		lista[pos2] = temp;
	}
	
	/**
	 * Esta función ordena una lista recorriendo cada una de sus posciones e intercambiando posiciones contiguas en caso de que
	 * lista[n-1]>lista[n], esto mediante un ciclo que inicia siempre en la posición 0 del arreglo y finaliza en una variable decreciente
	 * cuyo primer valor es el tamaño de la lista. Lo que da como resultado, que el mayor valor de lista[0:n] sea desplazado a lista[n].
	 * @param lista Lista que va a ser ordenada
	 */
	public void ordenarLista(Integer lista []) {
		for(int limite = lista.length-1; limite > 0; limite--) {
			//System.out.println(limite);
			for(int i = 0; i < limite; i++) {
				if(lista[i] > lista[i+1]) {
					intercambiar(lista,i,i+1);
				}
			}
		}
	}

}
