package algoritmos;

public class BusquedaBinaria {
	
	private int posicion;
	
	private int limite = 0;

	private void buscar(Integer [] lista, int min, int max, int elemento) {
		int indice = (min+max)/2;
		if(min <= max && posicion == -1) {
			if(lista[indice] == elemento) {
				this.posicion = indice;
			}
			else {
				buscar(lista, min, indice-1, elemento); // Lista izquierda
				buscar(lista, indice+1, max, elemento); // Lista derecha
			}
			limite++;
		}
	}
	
	public int busquedaBinaria(Integer [] lista, int elemento) {
		this.posicion = -1;
		buscar(lista, 0, lista.length-1, elemento);
		return posicion+1;
	}
}
