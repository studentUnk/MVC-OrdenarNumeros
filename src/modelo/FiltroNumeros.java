package modelo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * Código basado en: https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 * Esta clase es utilizada para filtrar los caracteres que son ingresados cuando el usuario va a realizar una búsqueda.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-09-04 
 */

public class FiltroNumeros extends DocumentFilter{
	
	/**
	 * Función para insertar caracteres al área de texto.
	 */
	@Override
	public void insertString(FilterBypass fB, int offset, String string, AttributeSet attr) throws BadLocationException {
		Document doc = fB.getDocument();
		StringBuilder sB = new StringBuilder();
		sB.append(doc.getText(0, doc.getLength()));
		sB.insert(offset, string);
		
		if(prueba(sB.toString())) {
			super.insertString(fB, offset, string, attr);
		}
	}
	
	/**
	 * Función para validar que los caracteres ingresados son los que se desea, en este caso solo se aceptarán
	 * números.
	 * @param text Texto actual del área de texto en el frame
	 * @return True = si el caracter es aceptado | False = si el caracter no es válido
	 */
	private boolean prueba(String text) {
		try {
			if(text.isEmpty()) { return true; }
			Integer.parseInt(text);
			return true;
		} catch(NumberFormatException e) {
			System.out.println("Caracter introducido no es un número");
			return false;
		}
	}
	
	/**
	 * Función para reemplazar un carácter existente.
	 */
	@Override
	public void replace(FilterBypass fB, int offset, int length, String string, AttributeSet attr) throws BadLocationException{
		Document doc = fB.getDocument();
		StringBuilder sB = new StringBuilder();
		sB.append(doc.getText(0, doc.getLength()));
		sB.replace(offset, offset+length, string);
		
		if(prueba(sB.toString())) {
			super.replace(fB, offset, length, string, attr);
		}
	}
	
	/**
	 * Función para eliminar carácteres dentro del área de texto.
	 */
	@Override
	public void remove(FilterBypass fB, int offset, int length) throws BadLocationException {
		Document doc = fB.getDocument();
		StringBuilder sB = new StringBuilder();
		sB.append(doc.getText(0, doc.getLength()));
		sB.delete(offset, offset+length);
		
		if(prueba(sB.toString())) {
			super.remove(fB, offset, length);
		}
	}
}
