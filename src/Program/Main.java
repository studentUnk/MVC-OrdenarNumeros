package Program;

import java.util.ArrayList;
import Model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> lista = FileManager.leer("Numeros.txt");
		for(int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
		
		System.out.println("Ordenando lista");
		lista = OrderList.insertionSort(lista);
		System.out.println("-lista desordenada");
		System.out.println("Ordenando lista");
		FileManager.escribir("prueba.txt", lista);
	}

}
