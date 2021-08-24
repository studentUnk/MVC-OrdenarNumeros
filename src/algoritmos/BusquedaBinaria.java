package algoritmos;

import java.util.concurrent.ThreadLocalRandom;

public class BusquedaBinaria {

	private int posicion;

	private void buscar(Integer[] lista, int min, int max, int tipoPivote, int elemento) {
		if (min <= max && posicion == -1) {
			int indice = seleccionarPivote(min, max, tipoPivote);
			if (lista[indice] == elemento) {
				this.posicion = indice;
			} else {
				buscar(lista, min, indice - 1, tipoPivote, elemento); // Lista izquierda
				buscar(lista, indice + 1, max, tipoPivote, elemento); // Lista derecha
			}
		}
	}

	public int busquedaBinaria(Integer[] lista, int elemento, int tipoPivote) {
		this.posicion = -1;
		buscar(lista, 0, lista.length - 1, tipoPivote, elemento);
		return posicion + 1;
	}

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
