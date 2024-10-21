package accesosecuencial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Si queremos editar un fichero(eliminar/reemplazar)
 * No se puede leer y escribir a la vez, hay que usar un auxiliar
 * CreateTempFile
 */
public class ArreglaFicheroTexto {
	
	public static void main(String[] args){
		
		String nomFichero = "f_texto.txt";
		File f = new File(nomFichero);
		
		if (!f.exists()) {
			System.out.println("Fichero " + nomFichero + " no existe.");
			return;
		}
		
		try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
			
			File fTemp = File.createTempFile(nomFichero, ""); //prefijo y sufijo (vacío)
			System.out.println("Creado fichero temporal " + fTemp.getAbsolutePath());
			
			BufferedWriter bfw = new BufferedWriter(new FileWriter(fTemp));
			String linea = bfr.readLine();
			
			while(linea != null){
				
				boolean principioLinea = true, espacios = false, primerAlfab = false;
				
				for(int i = 0; i < linea.length(); i++){
					
					char c = linea.charAt(i);
					
					if (Character.isWhitespace(c)) {
						
						if (!espacios && !principioLinea) { //elimina los espacios consecutivos o de principio de línea
							
							bfw.write(c);
							
						}
						
						espacios = true;
						
					} else if (Character.isAlphabetic(c)) { 
						
						if(!primerAlfab){ //en mayúscula la primera letra de cada línea
							
							bfw.write(Character.toUpperCase(c));
							primerAlfab = true;
							
						} else {
							
							bfw.write(c);
							
						}
						
						espacios = false;
						principioLinea = false;
						
					} 
					//todo lo que no sea carácter o espacio se ignora
				}
				
				bfw.newLine();
				linea = bfr.readLine();
				
			}
			
			bfw.close();
			boolean f1 = f.renameTo(new File(nomFichero + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".bak"));
			boolean f2 = fTemp.renameTo(new File(nomFichero));
			
			System.out.println("F1: " + f1 + "\nF2: " + f2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}

