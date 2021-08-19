package algoritmos;

import java.util.Arrays;
import java.util.Random;
import java.lang.Runnable;

public class QuickSort {

	//int limit = 0;

	public static void main(String[] args) {
		Integer [] lista = new Integer[] {5,2,1,8,3,9,7};
		//Integer [] lista = new Integer[] {8,1,4,9,6,3,5,2,7,0};
		//Integer[] lista = new Integer[] { 79, 21, 15, 99, 88, 65, 75, 85, 76, 46, 84, 24 };
		QuickSort quickSort = new QuickSort();
		quickSort.ordenar(lista, 0, lista.length - 1, quickSort.seleccionarPivote(lista.length, 0, lista.length-1, 3), 3);
	}
	
	public void ordenarLista(Integer [] lista, int tipoQuickSort) {
		ordenar(lista,0,lista.length-1, seleccionarPivote(lista.length,0, lista.length-1,tipoQuickSort),tipoQuickSort);
	}

	public void intercambiar(Integer lista[], int pos1, int pos2) {
		int temp = lista[pos1];
		lista[pos1] = lista[pos2];
		lista[pos2] = temp;
	}

	public void ordenar(Integer lista[], int min, int max, int pivote, int sP) {
		if (min < max) { // Hay mas de un elemento
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
						if(max < pivote) {max = pivote-1;}
						continue;
					}
				}
				if (max > pivote) {
					if (lista[max].compareTo(lista[pivote]) < 0) { // Esta a la derecha del pivote
						intercambiar(lista, max, pivote);
						if(min > pivote) {
							min = pivote+1;
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
			//limit++;
			if (pivote > min_p) {
				ordenar(lista, min_p, pivote - 1, seleccionarPivote((pivote) - min_p, min_p, pivote-1, sP), sP); // Lista
																										// izquierda
			}
			if (pivote < max_p) {
				ordenar(lista, pivote + 1, max_p, seleccionarPivote(max_p - (pivote), pivote + 1, max_p, sP), sP); // Lista
																											// derecha
			}
		}
	}

	private int seleccionarPivote(int tam_lista, int min, int max, int s) {
		if (s > 4 || s < 1) {
			return -1;
		}
		switch (s) {
		// Pivote de la izquierda
		case 1:
			return min;
		// Pivote de la derecha
		case 2:
			//return (tam_lista + min) - 1;
			return max;
		// Pivote central
		case 3:
			return (int) (tam_lista / 2) + min;
		// Pivote aleatorio
		case 4:
			return new Random().nextInt(tam_lista);
		// Defecto
		default:
			return -1;
		}
	}

}
