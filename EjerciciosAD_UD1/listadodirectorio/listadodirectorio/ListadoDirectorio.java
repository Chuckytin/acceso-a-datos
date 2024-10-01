package listadodirectorio;

import java.io.File;
import java.text.SimpleDateFormat;
/*
 * Uso de la clase File para mostrar información de ficheros y directorios
 * 
 * EJERCICIO:
 * Modificar el código para que muestre también:
 * 		Tamaño
 * 		Permisos (rwx)
 * 		Fecha última modificación
 */
public class ListadoDirectorio {

	public static void main(String[] args) {
		
		String ruta = ".";
		if (args.length >=1) ruta = args[0];
		
		File fichero = new File(ruta);
			
		if (!fichero.exists()) {
			
			System.out.println("No existe el fichero o directorio " + ruta + ".");
		
		} else {
			
			if (fichero.isFile()) {
				
				System.out.println(ruta + " es un fichero.");
				
			} else {
				
				System.out.println(ruta + " es un directorio. Contenidos: ");
				File[] listaDeFicheros = fichero.listFiles(); // ficheros o directorios
				
				for (File f : listaDeFicheros) {
										
					String textoDecr = f.isDirectory() ? "/" : f.isFile() ? "_": "?";
					System.out.print("(" + textoDecr + ") " + f.getName());
					
					System.out.print(" ___ Permisos: " + permisos(f) + " ___ ");
					
					SimpleDateFormat fecha = new SimpleDateFormat();
					
					System.out.print("Última modificación: " + fecha.format(f.lastModified()) + "\n");
					
				}
				
				System.out.print("Longitud del contenido del directorio: " + listaDeFicheros.length + " ");
				
			}
			
		}
	
	}
	
	public static String permisos(File f) {
		
		String permisos = ""; //rwx
		
		permisos += (f.canRead() ? "r" : "-");
		permisos += (f.canWrite() ? "w" : "-");
		permisos += (f.canExecute() ? "x" : "-");
		
		return permisos;
		
	}
	
}
