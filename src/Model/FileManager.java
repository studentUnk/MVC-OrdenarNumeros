package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class FileManager {
	private FileManager() {
		
	}
	
	//crea el archivo donde se colocan los datos
	private static boolean crear(String nombreArchivo) {
		try {
			File file = new File(nombreArchivo);
			if (file.createNewFile()) {
				System.out.println("Archivo creado: " + file.getName());
				return true;
			} else {
				System.out.println("Archivo ya existe y sera sobreescrito");
				return true;
			}
		} catch (IOException e) {
			return false;
			//System.out.println("Error al crear archivo");
			//e.printStackTrace();
		}
	}
	
	
	//escribe en un archivo la lista de elementos
	public static boolean escribir(String nombreArchivo, ArrayList<Integer> lista) {
		try {
			crear(nombreArchivo);
			FileWriter fileWriter = new FileWriter(nombreArchivo);
			for (int i = 0; i < lista.size(); i++) {
				fileWriter.write(Integer.toString(lista.get(i)) + "\n");
			}
			fileWriter.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	//lee los datos del archivo y devuelve una lista
	public static ArrayList<Integer> leer(String nombreArchivo) {
		ArrayList<Integer> datos = new ArrayList<Integer>(); 
		try {
			File file = new File(nombreArchivo);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				datos.add(Integer.parseInt(scanner.nextLine())); // Leer lineas del archivo
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return datos;
	}
}
