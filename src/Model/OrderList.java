package Model;

import java.util.ArrayList;

public final class OrderList {
	public void sort() {
		
	}
	
	// Validar que la lista esta ordenada de forma ascendente
	public static boolean IsOrder(ArrayList<Integer> lista) { 
		for (int i = 0; i < (lista.size() - 1); i++) {
			if (lista.get(i) > lista.get(i + 1)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static ArrayList<Integer> bubbleSort(ArrayList<Integer> lista) {
		int aux;
		for(int i = 0; i < lista.size(); i++) {
			for(int j = 0; j < lista.size() - 1; j++) {
				if(lista.get(j) > lista.get(j+1)) {
					aux = lista.get(j);
					lista.set(j, lista.get(j+1));
					lista.set(j+1, aux);
				}
			}
		}
		return lista;
	}
	
	public static ArrayList<Integer> selectionSort(ArrayList<Integer> lista) {
		int minIndex,swapAux;
		System.out.println(lista.size());
		for(int i = 0; i < lista.size() -1; i++) {
			System.out.println(i);
			minIndex = i;
			for(int j = i; j < lista.size(); j++) {
				if(lista.get(j) < lista.get(minIndex)) {
					minIndex = j;
				}
			}
			swapAux = lista.get(i);
			lista.set(i, lista.get(minIndex));
			lista.set(minIndex,swapAux);
		}
		return lista;
	}
	
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> lista){
		int pos, valor;
		for(int i = 0; i < lista.size(); i++) {
			pos = i;
			valor = lista.get(i);
			while(pos > 1 && valor < lista.get(pos - 1)) {
				lista.set(pos, lista.get(pos - 1));
				pos--;
			}
			lista.set(pos, valor);
		}
		return lista;
	}
}
