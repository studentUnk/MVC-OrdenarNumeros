package programa;

import vista.Aplicacion;
import controlador.Controlador;

public class Main {
	public static void main(String [] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.crearVentana("Lista Ordenada");
		Controlador controlador = new Controlador(aplicacion);
		controlador.accionBotones();		
	}
}
