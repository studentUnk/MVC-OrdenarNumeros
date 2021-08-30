package modelo.algoritmos;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Busqueda Binaria ofrece funciones para realizar b�squedas binarias. Se establecen dos tipos de funciones: 
 * desordenada o ordenada. Para desordenada, se escoge una posici�n del pivote, se evalua y se divide hasta que
 * se encuentre el valor buscado (si existe), en el peor de los casos se realizar�an n comparaciones, siendo
 * n el tama�o del arreglo. Para ordenada, se escoge la posici�n del pivote, y en base a esto se evalua si se toma
 * el camino de la derecha o el de la izquierda (mayor o menor que el valor buscado) en base al valor buscado, 
 * esto hasta que la comparaci�n sea mayor a un elemento o el elemento sea encontrado.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-27 
 */

public class BusquedaBinaria {

	private int posicion; // variable para dar por finalizada la b�squeda o retornar el valor del elemento buscado

	/**
	 * Esta funci�n busca en el arreglo "lista" sin tomar consideraci�n en si el arreglo est� ordenado o no. Divide el arreglo
	 * en dos seg�n la posici�n elegida del pivote (izquierda y derecha), analiza si la posici�n elegida del pivote contiene
	 * el elemento deseado de lo contrario realiza el mismo proceso en las dos divisiones realizadas hasta que el elemento sea encontrado
	 * o no existan m�s elementos en la lista a comparar (min > max). 
	 * @param lista Lista a analizar
	 * @param min M�nimo valor del rango a analizar
	 * @param max M�ximo valor del rango a analizar
	 * @param tipoPivote Tipo de pivote a utilizar (central, aleatorio)
	 * @param elemento El elemento que se desea encontrar
	 */
	private void buscarDesorden(Integer[] lista, int min, int max, int tipoPivote, int elemento) {
		if (min <= max && posicion == -1) {
			int indice = seleccionarPivote(min, max, tipoPivote);
			if (lista[indice] == elemento) {
				this.posicion = indice;
			} else {
				buscarDesorden(lista, min, indice - 1, tipoPivote, elemento); // Lista izquierda
				buscarDesorden(lista, indice + 1, max, tipoPivote, elemento); // Lista derecha
			}
		}
	}
	
	/**
	 * Esta funci�n busca en el arreglo "lista" considerando que la lista esta ordenada, en base a esto realiza dos divisiones seg�n la posici�n
	 * elegida para el pivote, por lo tanto, a la izquierda quedar�an todos aquellos que son menores, y a la derecha todos los que son mayores. Hecho
	 * esto se compara el elemento en la posici�n del pivote con el elemento a encontrar, si son iguales, detiene la b�squeda, en caso contrario,
	 * realiza el mismo proceso en la divisi�n izquierda si el elemento es menor que aquel que est� en el pivote, o realiza el mismo proceso en la
	 * divisi�n de la derecha si el elemento a encontrar es mayor. La b�squeda finaliza hasta que el elemento sea encontrado posici�n != -1 o que 
	 * el rango de elementos a comparar no sea v�lido (min > max).
	 * @param lista Lista a analizar
	 * @param min M�nimo valor del rango a analizar
	 * @param max M�ximo valor del rango a analizar
	 * @param tipoPivote Tipo de pivote a utilizar (central, aleatorio)
	 * @param elemento El elemento que se desea encontrar
	 */
	private void buscarOrden(Integer[] lista, int min, int max, int tipoPivote, int elemento) {
		if (min <= max && posicion == -1) {
			int indice = seleccionarPivote(min,max,tipoPivote);
			if(lista[indice] == elemento) {
				this.posicion = indice;
			} else {
				if(lista[indice] < elemento) { // Tomar lista de la derecha
					buscarOrden(lista, indice+1, max, tipoPivote, elemento);
				}else { //Tomar lista de la izquierda
					buscarOrden(lista, min, indice-1, tipoPivote, elemento);
				}
			}
		}
	}

	/**
	 * Esta funci�n es la funci�n de entrada que hace uso de la funci�n buscarOrden.
	 * @param lista Lista de elementos a analizar
	 * @param elemento Elemento a encontrar
	 * @param tipoPivote Tipo de posici�n para el pivote (central, aleatoria)
	 * @return posici�n del elemento deseado (si existe)
	 */
	public int busquedaBinaria(Integer[] lista, int elemento, int tipoPivote) {
		this.posicion = -1;
		buscarOrden(lista, 0, lista.length - 1, tipoPivote, elemento);
		return posicion + 1;
	}

	/**
	 * Esta funci�n retorna la posici�n que tendr� el pivote en el rango de la lista establecido.
	 * @param min Valor m�nimo en el rango de la lista
	 * @param max Valor m�ximo en el rango de la lista
	 * @param tipo Tipo de pivote a retornar (central, aleatorio)
	 * @return Posici�n del pivote
	 */
	public int seleccionarPivote(int min, int max, int tipo) {
		switch (tipo) {
		// Pivote en el medio
		case 1:
			return (min + max) / 2;
		// Pivote aleatorio
		case 2:
			return ThreadLocalRandom.current().nextInt(min, max + 1);
		default:
			return -1;
		}
	}
}
