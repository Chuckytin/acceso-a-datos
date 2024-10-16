package accesosecuencial;

import java.io.*;

public class VolcadoBinario {

    static int TAM_FILA = 32;
    static int MAX_BYTES = 2048;
    private InputStream is = null;
    private PrintStream ps = null;

    public VolcadoBinario(InputStream is, PrintStream ps) {
        this.is = is;
        this.ps = ps;
    }

    /**
     * Vuelca el contenido del archivo binario al sistema de salida.
     * Lee y muestra los datos en formato hexadecimal.
     * @throws IOException si ocurre un error de entrada/salida
     */
    public void volcar() throws IOException {
        
        byte buffer[] = new byte[TAM_FILA];
        int bytesLeidos;
        int offset = 0;

        do {
            // Lee TAM_FILA bytes del archivo
            bytesLeidos = is.read(buffer);
            ps.format("[%5d]", offset);
            
            // Muestra los bytes leídos en formato hexadecimal
            for (int i = 0; i < bytesLeidos; i++) {
                ps.format(" %2x", buffer[i]);
            }
            
            offset += bytesLeidos;
            ps.println();
            
        } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
        
    }

    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("No se ha indicado ningún fichero");
            return;
        }
        
        String nombreFichero = args[0];
        
        try (FileInputStream fis = new FileInputStream(nombreFichero)) {
            
            PrintStream out = new PrintStream(new File("salida.txt"));
            VolcadoBinario lectura = new VolcadoBinario(fis, out);
            lectura.volcar();
            
        } catch (FileNotFoundException e) {
        	
            System.err.println("ERROR: No existe fichero " + nombreFichero);
            
        } catch (IOException e) {
        	
            System.err.println("ERROR de E/S: " + e.getMessage());
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }
        
    }
    
}

