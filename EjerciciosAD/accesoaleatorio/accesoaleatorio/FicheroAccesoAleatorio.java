package accesoaleatorio;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * ¿Qué pasa si lo modificamos para añadir un registro mas allá del último?
 * Crea un método obtenerValor(int posicion, String campo) que devuelva el campo del registro en la posición.
 */
public class FicheroAccesoAleatorio {

    private final File file;
    
    private final List<Pair<String, Integer>> campos;
    
    private long longitudRegistro; 
    private long numeroRegistro = 0; 

    private RandomAccessFile faa;
    
    public File getFichero() {
        return file;
    }

    public FicheroAccesoAleatorio (String nomFich, List<Pair<String, Integer>> campos) throws IOException {
        
        this.campos = campos;
        this.file = new File(nomFich);
        faa = new RandomAccessFile(file, "rws");
        longitudRegistro = 0;
        
        int i = 0;
        for (Pair<String, Integer> campo : campos) {
        	
            this.longitudRegistro += campo.getValue(); //longitudRegistro es el sumatorio de todos los campos de la lista
            System.out.printf("Iteración: %d -> value: %d\n", i++, campo.getValue());
            
        }
        
        if (file.exists()) {
            this.numeroRegistro = file.length() / this.longitudRegistro; //número de registros
        }
        
    }

    public long getNumReg() {
        return this.numeroRegistro;
    }

    public void insertar (Map<String, String> reg) throws IOException {
        insertar(reg, this.numeroRegistro++);
    }

    long insertar (Map<String, String> reg, long pos) throws IOException {
        
        if (pos > this.numeroRegistro) {
        	
            return (long) - 1;
            
        } else if (pos == this.numeroRegistro) {
        	
            this.numeroRegistro++;
            
        }
        
        faa.seek(pos * this.longitudRegistro);
        
        //mueve el cursor al registro n-esimo
        for (Pair<String, Integer> campo : this.campos) { 
        	
        	//recorre los campos y busca el valor, si un campo no existe se pone cadena vacía
            String nombreCampo = campo.getKey();
            Integer longitudCampo = campo.getValue();
            String valorCampo = reg.get(nombreCampo);
            
            if (valorCampo == null) {
                valorCampo = "";
            }
            
            String valorCampoForm = String.format("%1$-" + longitudCampo + "s", valorCampo);
            //System.out.println("%%1$-" + longitudCampo + "s");
            faa.write(valorCampoForm.getBytes(StandardCharsets.UTF_8), 0, longitudCampo);
            
        }
        
        return pos;
        
    }

    // Clase Pair personalizada, simula la biblioteca de javafx
    static class Pair<K,V> {
    	
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        
    }

    public static void main (String[] args) {

        List<Pair<String, Integer>> campos = new ArrayList<>();
        campos.add(new Pair<>("DNI", 9)); //9 carácteres para el DNI
        campos.add(new Pair<>("NOMBRE", 32)); //32 carácteres para todo el campo
        campos.add(new Pair<>("CP", 5)); //5 carácteres para el CP

        try {
        	
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_aleat.dat", campos);
            Map<String, String> reg = new HashMap<>();
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");
            reg.put("CP", "29730");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            reg.put("CP", "13700");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            reg.put("CP", "44126");
            faa.insertar(reg, 1);
            reg.clear();
            reg.put("DNI", "12345678Z");
            reg.put("NOMBRE", "ARCOS");
            
            long posicion = 3;
            
            if (faa.insertar(reg, posicion) < 0) {
                System.err.println("No se pudo insertar registro en posición " + posicion);
            }
            
            System.out.println("Fichero " + faa.getFichero().getAbsolutePath()
                    + " contiene " + faa.getNumReg() + " registros.");
            
            String nombreCampo = "NOMBRE"; // Se escribe valor de este campo del último al primer registro
            
            for(long i = faa.getNumReg() - 1; i >= 0; i--) {
                System.out.println("Registro " + i + ", " + nombreCampo + ": " + faa.obtenerValor(i, nombreCampo));
            }
            
        } catch (IOException e) {
        	
            System.err.println("Error de E/S: " + e.getMessage());
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }

    }

    // Método para obtener el valor de un campo específico en una posición
    public String obtenerValor(long pos, String campo) throws IOException {
    	
        faa.seek(pos * this.longitudRegistro);
        
        byte[] bytes = new byte[this.campos
                                .stream()
                                .filter(p -> p.getKey()
                                		.equals(campo))
                                .findFirst()
                                .orElse(null)
                                .getValue()
                                ];
        
        faa.read(bytes);
        
        return new String(bytes, StandardCharsets.UTF_8).trim();
        
    }
    
}