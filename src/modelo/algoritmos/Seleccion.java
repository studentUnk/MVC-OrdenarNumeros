package modelo.algoritmos;

import java.util.Arrays;

public class Seleccion {

	public static void main(String[] args) {
		// Integer [] lista = new Integer[] {5,2,1,8,3,9,7};
		// Integer[] lista = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		// Integer [] lista = new Integer[] {8,1,4,9,6,3,5,2,7,0};
		Integer[] lista = new Integer[] { 79, 21, 15, 99, 88, 65, 75, 85, 76, 46, 84, 24 };
		Seleccion seleccion = new Seleccion();
		// quickSort.ordenar(lista, 0, lista.length - 1,
		// quickSort.seleccionarPivote(lista.length, 0, lista.length-1, 2), 2);
		seleccion.ordenarLista(lista);
		// quickSort.ordenar(lista, 0, lista.length - 1, quickSort.seleccionarPivote(0,
		// lista.length - 1, 2), 4);
		System.out.println(Arrays.toString(lista));
	}

	/**
	 * Esta funci�n es usada para intercambiar el valor asignado a una posici�n en
	 * el arreglo por otro en el mismo arreglo.
	 * 
	 * @param lista Arreglo que se est� ordenando
	 * @param pos1  Valor a
	 * @param pos2  Valor b
	 * @return Nada, ya que el cambio es realizado directamente en el arreglo
	 */
	private void intercambiar(Integer lista[], int pos1, int pos2) {
		int temp = lista[pos1];
		lista[pos1] = lista[pos2];
		lista[pos2] = temp;
	}

	public void ordenarLista(Integer lista[]) {
		for (int i = 0; i < lista.length; i++) {
			int posicion = i;
			Integer menor = Integer.MAX_VALUE;
			for (int j = i; j < lista.length; j++) {
				if (menor > lista[j]) { // Hallar numero menor
					posicion = j;
					menor = lista[j];
				}
			}
			if (i != posicion) {
				intercambiar(lista, i, posicion); // Mover numero m�s peque�o al inicio
			}
		}
	}

}
