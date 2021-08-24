package algoritmos;

public class Algoritmo {
	public boolean listaOrdenada(Integer[] lista) { // Validar que la lista esta ordenada de forma ascendente
		for (int i = 0; i < (lista.length - 1); i++) {
			if (lista[i] > lista[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
