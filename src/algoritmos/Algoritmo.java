package algoritmos;

/**
 * Esta clase contiene funciones o acciones generales para las clases o algoritmos de búsqueda y ordenamiento
 * que se vayan a implementar.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-27 
 */
public class Algoritmo {
	
	/**
	 * Está función compara desde la primera posición hasta la posición final esperando encontrar un elemento número
	 * que sea menor, esto para determinar si el arreglo está ordenado o no.
	 * @param lista Lista de valores a analizar
	 * @return Verdadero si la lista esta ordenada de lo contrario retorna Falso
	 */
	public boolean listaOrdenada(Integer[] lista) { // Validar que la lista esta ordenada de forma ascendente
		for (int i = 0; i < (lista.length - 1); i++) {
			if (lista[i] > lista[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
