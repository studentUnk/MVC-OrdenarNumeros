package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Clase para manipular el archivo que ser� cargado en tres sentidos: crear, leer y cargar.
 * El archivo es creado, cuando el usuairo selecciona el archivo que desea guardar. En caso de que el archivo ya exista los valores son agregados al final del archivo.
 * El archivo es cargado cuando el usuario seleccionar el archivo que desea cargar. En caso de que no exista no se crea un arhivo que lo reemplaze.
 * El archivo es le�do despu�s de que ha sido cargado, cada una de las l�neas est�n separadas por un salto de l�nea.
 * 
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 *
 */

public class Archivo {

	private ArrayList<String> datos;
	private File file;
	private String[] tipo = {"txt","csv"};

	public Archivo() {
		this.datos = new ArrayList<String>();
	}

	/**
	 * Funci�n para convertir la lista cargada como String en una lista de Enteros para facilitar su ordenamiento.
	 * @return Lista de String convertida a Entero
	 */
	public Integer[] convertirArrayEntero() {
		Integer[] lista = new Integer[this.datos.size()];
		for (int i = 0; i < this.datos.size(); i++) {
			lista[i] = Integer.parseInt(this.datos.get(i));
		}
		return lista;
	}

	/**
	 * Funci�n para guardar el archivo en una ruta espec�fica.
	 * @param ubicacionArchivo PATH o ruta del archivo en la cual ser� guardado el archivo
	 * @param tipo Tipo de archivo con el cual ser� guardado el archivo (txt, csv)
	 */
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

	/**
	 * Funci�n que escribe los datos que est�n en la variable "lista" en un archivo designado por el usuario.
	 * @param ubicacionArchivo PATH o ruta del archivo en la cual ser�n escritos los datos
	 * @param tipo Tipo de archivo que ser� asignado (txt,csv)
	 * @param lista Lista de datos que ser� escrita en el archivo
	 */
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

	/**
	 * Funci�n que retorna los datos cargados en un array de tipo String
	 * @return Lista de datos cargados
	 */
	public ArrayList<String> getDatos() {
		return datos;
	}

	/**
	 * Funci�n de prueba!
	 */
	public void imprimirDatos() {
		for (int i = 0; i < this.datos.size(); i++) {
			System.out.println(datos.get(i));
		}
	}

	/**
	 * Funci�n para leer los datos en un archivo.
	 * Cada dato de valor se considera que est� en una l�nea del archivo, por lo tanto, por cada salto de l�nea hay un dato.
	 * @param nombreArchivo Ruta del archivo que ser� le�do
	 */
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
	
	/**
	 * Funci�n para retornar los tipos de arhivos disponibles en el programa.
	 * @return Tipo de archivo (txt,csv)
	 */
	public String [] obtenerTipoArchivo() {
		return tipo;
	}

}
