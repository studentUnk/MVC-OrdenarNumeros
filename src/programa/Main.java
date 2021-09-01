package programa;

import vista.Aplicacion;
import controlador.Controlador;

/**
 * Clase principal que da inicio a la interfaz del usuario y a los datos que serán manejados por el controlador.
 * 
 * @author Camilo J.
 * @version 1.0
 * @since 2021-08-31 
 * 
 */
public class Main {
	public static void main(String [] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.crearVentana("Lista Ordenada");
		Controlador controlador = new Controlador(aplicacion);
		controlador.accionBotones();		
	}
}
