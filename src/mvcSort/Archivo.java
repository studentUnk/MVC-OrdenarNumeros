package mvcSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class Archivo {

	private ArrayList<String> datos;

	Archivo() {
		this.datos = new ArrayList<String>();
	}

	public Integer[] convertirArrayEntero() {
		Integer[] lista = new Integer[this.datos.size()];
		for (int i = 0; i < this.datos.size(); i++) {
			lista[i] = Integer.parseInt(this.datos.get(i));
		}
		return lista;
	}

	public void crear(String nombreArchivo) {
		try {
			File file = new File(nombreArchivo);
			if (file.createNewFile()) {
				System.out.println("Archivo creado: " + file.getName());
			} else {
				System.out.println("Archivo ya existe y sera sobreescrito");
			}
		} catch (IOException e) {
			System.out.println("Error al crear archivo");
			e.printStackTrace();
		}
	}

	public void escribir(String nombreArchivo, Integer[] lista) {
		try {
			crear(nombreArchivo);
			FileWriter fileWriter = new FileWriter(nombreArchivo);
			for (int i = 0; i < lista.length; i++) {
				fileWriter.write(Integer.toString(lista[i]) + "\n");
			}
			fileWriter.close();
			System.out.println("Lista guardada");
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo");
			e.printStackTrace();
		}

	}

	public ArrayList<String> getDatos() {
		return datos;
	}

	public void imprimirDatos() {
		for (int i = 0; i < this.datos.size(); i++) {
			System.out.println(datos.get(i));
		}
	}

	public void leer(String nombreArchivo) {
		try {
			File file = new File(nombreArchivo);
			Scanner scanner = new Scanner(file);
			// int contar = 0;
			while (scanner.hasNextLine()) {
				datos.add(scanner.nextLine()); // Leer lineas del archivo
				// contar++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al cargar archivo");
			e.printStackTrace();
		}
	}

}
