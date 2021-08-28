package mvcSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class Archivo {

	private ArrayList<String> datos;
	private File file;
	private String[] tipo = {"txt","csv"};

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

	//public void crear(String nombreArchivo, String ubicacion) {
	public void crear(String ubicacionArchivo, String tipo) {
		try {
			//file = new File(ubicacion+"\\"+nombreArchivo);
			file = new File(ubicacionArchivo + "." + tipo);
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

	//public void escribir(String nombreArchivo, String ubicacion, Integer[] lista) {
	public void escribir(String ubicacionArchivo, String tipo, Integer[] lista) {
		try {
			//crear(nombreArchivo, ubicacion);
			crear(ubicacionArchivo,tipo);
			//FileWriter fileWriter = new FileWriter(nombreArchivo);
			//FileWriter fileWriter = new FileWriter(file);
			//FileWriter fileWriter = new FileWriter(ubicacion+"\\"+nombreArchivo);
			FileWriter fileWriter = new FileWriter(ubicacionArchivo+"."+tipo);
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
	
	public String [] obtenerTipoArchivo() {
		return tipo;
	}

}
