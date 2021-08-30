package modelo.algoritmos;

import java.util.ArrayList;
import java.util.Arrays;

public class Insercion {
	
	public static void main(String[] args) {
		//Integer [] lista = new Integer[] {5,2,1,8,3,9,7};
		//Integer[] lista = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		// Integer [] lista = new Integer[] {8,1,4,9,6,3,5,2,7,0};
		Integer[] lista = new Integer[] { 79, 21, 15, 99, 88, 65, 75, 85, 76, 46, 84,
		 24 };
		Insercion insercion = new Insercion();
		// quickSort.ordenar(lista, 0, lista.length - 1,
		// quickSort.seleccionarPivote(lista.length, 0, lista.length-1, 2), 2);
		insercion.ordenarLista(lista);
		//quickSort.ordenar(lista, 0, lista.length - 1, quickSort.seleccionarPivote(0, lista.length - 1, 2), 4);
		System.out.println(Arrays.toString(lista));
	}
	
	public void ordenarLista(Integer lista[]) {
		if(lista.length > 0 ) {
			ArrayList<Integer> array = new ArrayList<Integer>();
			array.add(lista[0]); // Agregar primer elemento
			boolean agregar;
			for (int i = 1; i < lista.length; i++) {
				agregar = false;
				for (int j = 0; j < array.size(); j++) {
					if(lista[i] < array.get(j)) {
						agregar = true;
						array.add(j, lista[i]); // Agregar nuevo elemento
						break;
					}
				}
				if(!agregar) {
					array.add(lista[i]);
				}
			}
			for(int i = 0; i < lista.length; i++) { // copiar valores a la lista original
				lista[i] = array.get(i);
			}
		}
	}

}
