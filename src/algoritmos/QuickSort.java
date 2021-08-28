package algoritmos;

import java.util.Arrays;
//import java.util.Random;
//import java.lang.Runnable;
import java.util.concurrent.ThreadLocalRandom;
//import algoritmos.Algoritmo;

/**
 * La clase Quicksort es una clase que ofrece funciones para ordenar una lista
 * implementando el algoritmo Quicksort. Para ello la lista se divide en dos
 * despu�s de escoger un pivote (aleatorio, el primero, el �ltimo, el de la mitad,
 * despu�s de escogido a la izquierda quedar�n los elementos que sean menores y a 
 * la derecha los elementos que sean mayores, posterior a esto se realiza el mismo 
 * proceso con todos los elementos de la izquierda y luego
 * con los de la derecha, esto hasta que el dominio a comparar sea menor a dos.
 * 
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-27 
 */

public class QuickSort {

	/**
	 * Esta funci�n es una funci�n de prueba, por lo tanto no tiene un uso real en la aplicaci�n.
	 * @param args No es usado
	 * @return nada
	 */
	public static void main(String[] args) {
		// Integer [] lista = new Integer[] {5,2,1,8,3,9,7};
		Integer[] lista = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		// Integer [] lista = new Integer[] {8,1,4,9,6,3,5,2,7,0};
		// Integer[] lista = new Integer[] { 79, 21, 15, 99, 88, 65, 75, 85, 76, 46, 84,
		// 24 };
		QuickSort quickSort = new QuickSort();
		// quickSort.ordenar(lista, 0, lista.length - 1,
		// quickSort.seleccionarPivote(lista.length, 0, lista.length-1, 2), 2);
		quickSort.ordenar(lista, 0, lista.length - 1, quickSort.seleccionarPivote(0, lista.length - 1, 2), 4);
		System.out.println(Arrays.toString(lista));
	}

	int limite = 0; // Usado para validar la cantidad m�xima de comparaciones sea el tama�o de la lista

	/**
	 * Esta funci�n es usada para intercambiar el valor asignado a una posici�n en el arreglo
	 * por otro en el mismo arreglo.
	 * @param lista Arreglo que se est� ordenando
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
	 * Esta funci�n divide en dos y mueve los valores en base a un rango de selecci�n (minimo <= maximo),
	 * despu�s de haber establecido un elemento de comparaci�n (pivote).
	 * Ubica en la secci�n izquierda todos los valores que sean menores, y en la secci�n derecha todos 
	 * aquellos que sean mayores.
	 * Para poder realizar esto se usan dos variables que se van moviendo en el rango establecido
	 * (min y max), conforme se van acercando la una a la otra, el pivote se va moviendo. 
	 * Se tiene en cuenta las siguientes reglas:
	 * 1. Si en la posici�n de min hay un elemento mayor y en la posici�n de max hay un elemento menor
	 *    ambos son intercambiados. min pasa a la siguiente posici�n antes del intercambio (min+1) y max retrocede
	 *    una posici�n (max-1)
	 * 2. Si en la posici�n de min hay un elemento mayor pero en la posici�n de max no, la posici�n y el 
	 *    valor son intercambiados con el pivote, hecho esto el valor de min pasa al siguiente (min+1).
	 * 3. Si en la posici�n de max hay un elemento menor pero en la posici�n de min no, la posici�n y el 
	 *    valor son intercambiados con el pivote, hecho esto el valor de max retrocede uno (max-1).
	 * 4. Si max < pivote, y el valor en la posici�n es mayor al del pivote, la posici�n del pivote cambia a la de max
	 *    y max toma el indice que ten�a previamente el pivote menos uno (1).
	 * 5. Si min > pivote, y el valor en la posici�no es menro al del pivote, la posici�n del pivote cambia la de min
	 *    y min toma el indice que tenia previamente el pivote m�s uno (1).
	 * 6. Si min = max, se evalua si el valor es menor o mayor al pivote y se realiza acci�n 4 o 5 seg�n corresponda.
	 * Finalmente se realiza este mismo proceso en cada una de las divisiones resultantes hasta que el rango de valores
	 * a comparar sea menor a 2.
	 * @param lista Arreglo o lista de datos que se va a ordenar
	 * @param min La posici�n m�nima de la lista a ordenar 
	 * @param max La posici�n m�xima de la lista a ordenar
	 * @param pivote Posicion del valor para dividir la lista en dos
	 * @param sP Variable usada para determinar que tipo de pivote elegir (primero, �ltimo, central, aleatorio)
	 * @return Nada, ya que al ordenar lo hace directamente en el parametro "lista".
	 */
	private void ordenar(Integer lista[], int min, int max, int pivote, int sP) {
		if (min < max && limite < lista.length) { // Hay mas de un elemento
			int min_p = min, max_p = max;
			while (min <= max) {
				if (min < pivote) { // Esta a la izquierda del pivote
					if (lista[min].compareTo(lista[pivote]) > 0) {
						if (max > pivote) { // Esta a la derecha del pivote
							if (lista[max].compareTo(lista[pivote]) < 0) {
								intercambiar(lista, min, max);
								min++;
								max--;
								continue;
							} // Pivote intermedio
						}
						intercambiar(lista, min, pivote);
						if (max < pivote) {
							max = pivote - 1;
						}
						pivote = min;
						min++;
						continue;

					}
				} else { // Esta a la derecha del pivote
					if (lista[min].compareTo(lista[pivote]) < 0) {
						intercambiar(lista, min, pivote);
						int min_t = pivote;
						pivote = min;
						min = min_t + 1;
						if (max < pivote) {
							max = pivote - 1;
						}
						continue;
					}
				}
				if (max > pivote) {
					if (lista[max].compareTo(lista[pivote]) < 0) { // Esta a la derecha del pivote
						intercambiar(lista, max, pivote);
						if (min > pivote) {
							min = pivote + 1;
						}
						pivote = max;
						max--;
						continue;
					}
				} else {
					if (lista[max].compareTo(lista[pivote]) > 0) { // Esta a la izquierda del pivote
						intercambiar(lista, max, pivote);
						int max_t = pivote;
						pivote = max;
						max = max_t - 1;
						continue;
					}
				}
				if (min == max) {
					if (pivote > min) {
						if (lista[min].compareTo(lista[pivote]) > 0) {
							intercambiar(lista, min, pivote);
							max = pivote + 1;
							pivote = min;
						}
					} else {
						if (lista[min].compareTo(lista[pivote]) < 0) {
							intercambiar(lista, min, pivote);
							int min_t = min;
							min = pivote;
							max = min_t;
							pivote = min_t;
						}
					}
				}
				min++;
				max--;
			}
			limite++;
			if (pivote > min_p) {
				ordenar(lista, min_p, pivote - 1, seleccionarPivote(min_p, pivote - 1, sP), sP); // Lista
																									// izquierda
			}
			if (pivote < max_p) {
				ordenar(lista, pivote + 1, max_p, seleccionarPivote(pivote + 1, max_p, sP), sP); // Lista
																									// derecha
			}
		}
	}

	/**
	 * Esta funci�n tiene como prop�sito evitar ordenar una lista que ya est� ordenada. En caso contrario
	 * ordena el arreglo haciendo uso de la funci�n ordenar().
	 * @param lista Arreglo de enteros que se va a ordenar
	 * @param tipoQuickSort Tipo de posici�n a usar para el pivote (primero, �ltimo, central, aleatorio)
	 * @see ordenar Funci�n descrita previamente
	 */
	public void ordenarLista(Integer[] lista, int tipoQuickSort) {
		Algoritmo alg = new Algoritmo(); // Contiene la funci�n para verificar si la lista ya est� ordenada
		if (!alg.listaOrdenada(lista)) { // Lista no esta ordenada
			ordenar(lista, 0, lista.length - 1, seleccionarPivote(0, lista.length - 1, tipoQuickSort), tipoQuickSort);
		}
	}

	/**
	 * Esta funci�n retorna la posible posici�n para el pivote. 
	 * @param min Posici�n m�nima del rango en el arreglo a comparar
	 * @param max Posici�n m�xima del rango en el arreglo a comparar
	 * @param s Indica el tipo de pivote para retornar el valor correspondiente
	 * @return Tipo de pivote elegido
	 */
	private int seleccionarPivote(int min, int max, int s) {
		switch (s) {
		// Pivote de la izquierda - primero
		case 1:
			return min;
		// Pivote de la derecha - �ltimo
		case 2:
			return max;
		// Pivote central 
		case 3:
			return (int) ((min + max) / 2);
		// Pivote aleatorio
		case 4:
			return ThreadLocalRandom.current().nextInt(min, max + 1);
		// Defecto
		default:
			return -1;
		}
	}

}
