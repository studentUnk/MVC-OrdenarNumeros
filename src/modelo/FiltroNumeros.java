package modelo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * C�digo basado en: https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 * Esta clase es utilizada para filtrar los caracteres que son ingresados cuando el usuario va a realizar una b�squeda.
 * @author Camilo J.
 * @version 1.0
 * @since 2021-09-04 
 */

public class FiltroNumeros extends DocumentFilter{
	
	/**
	 * Funci�n para insertar caracteres al �rea de texto.
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
	 * Funci�n para validar que los caracteres ingresados son los que se desea, en este caso solo se aceptar�n
	 * n�meros.
	 * @param text Texto actual del �rea de texto en el frame
	 * @return True = si el caracter es aceptado | False = si el caracter no es v�lido
	 */
	private boolean prueba(String text) {
		try {
			if(text.isEmpty()) { return true; }
			Integer.parseInt(text);
			return true;
		} catch(NumberFormatException e) {
			System.out.println("Caracter introducido no es un n�mero");
			return false;
		}
	}
	
	/**
	 * Funci�n para reemplazar un car�cter existente.
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
	 * Funci�n para eliminar car�cteres dentro del �rea de texto.
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
