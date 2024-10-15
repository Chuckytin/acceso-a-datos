package accesosecuencial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

/*
 * Crea un programa que abra un fichero UTF-8 y genere un ISO-8859-1 y otro UTF-16. (OutputStreamWriter nos permite recodificar.) 
 * El UTF-8 lo tendrás que crear con un editor, asegurate de poner acentos, ñ y ese tipo de cosas. 
 */
public class LecturaEntrada2 {

	public static void main(String[] args) {
		
		String origen = "texto.txt";
		String nomFich8859_1 = "8859_1_" + origen;
		String nomFichUTF16 = "UTF_16_" + origen;	
		
		try(
			BufferedReader input = new BufferedReader(new FileReader(origen));
			BufferedWriter out8859 = new BufferedWriter( 
					new OutputStreamWriter(
							new FileOutputStream(nomFich8859_1), "ISO-8859-1"));
				
			BufferedWriter oututf16 = new BufferedWriter( 
					new OutputStreamWriter(
							new FileOutputStream(nomFichUTF16), "UTF-16"));) {
			
			String linea = input.readLine();
			
			while (linea != null) {
				
				out8859.write(linea);
				out8859.newLine();
				oututf16.write(linea);
				oututf16.newLine();
				linea = input.readLine();
				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
