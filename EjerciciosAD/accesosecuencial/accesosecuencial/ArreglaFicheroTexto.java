package accesosecuencial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArreglaFicheroTexto {
	
	public static void main(String[] args){
		
		String nomFichero = "f_texto.txt";
		File f = new File(nomFichero);
		
		if (!f.exists()) {
			System.out.println("Fichero " + nomFichero + " no existe.");
			return;
		}
		
		try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
			
			File fTemp = File.createTempFile(nomFichero, "");
			System.out.println("Creado fich. temporal " + fTemp.getAbsolutePath());
			
			BufferedWriter bfw = new BufferedWriter(new FileWriter(fTemp));
			String linea = bfr.readLine();
			
			while(linea!=null){
				
				boolean principioLinea = true, espacios=false, primerAlfab=false;
				
				for(int i=0; i<linea.length(); i++){
					
					char c = linea.charAt(i);
					
					if (Character.isWhitespace(c)) {
						if (!espacios && !principioLinea) {
							bfw.write(c);
						}
						espacios=true;
					} else if (Character.isAlphabetic(c)) {
						if(!primerAlfab){
							bfw.write(Character.toUpperCase(c));
							primerAlfab=true;
						} else {
							bfw.write(c);
						}
						espacios=false;
						principioLinea=false;
					}
					
				}
				
				bfw.newLine();
				linea=bfr.readLine();
				
			}
			
			bfw.close();
			f.renameTo(new File(nomFichero + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".bak"));
			fTemp.renameTo(new File(nomFichero));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}

