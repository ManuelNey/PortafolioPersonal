package Ejercicio3;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Principal {
    public static void main(String[] args) {
        transformarTextoT9("src/main/java/Ejercicio3/entrada.txt");
        transformarT9Texto("src/main/java/Ejercicio3/entrada.txt");
    }
    public static void transformarTextoT9(String rutaArchivo){
        String[] string = ObtenerLineas(rutaArchivo);
        HashMap<Character, Character> mapeo = new HashMap<>();
        mapeo.put(' ', '0');
        mapeo.put('.', '1');
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "22233344455566677778889999";
        for (int i = 0; i < letras.length(); i++) {
            mapeo.put(letras.charAt(i), numeros.charAt(i));
            mapeo.put(Character.toLowerCase(letras.charAt(i)), numeros.charAt(i));
        }
        String[] resultado = new String[string.length];
        for (int i = 0; i < string.length; i++) {
            String linea = string[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < linea.length(); j++) {
                char letra = linea.charAt(j);
                sb.append(mapeo.getOrDefault(letra, 'x')); // Encontré este metodo que devuelve lo que encuentre en el hashmap y si no lo encuentra da un valor por defecto
                //Leído de: https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/
                //En un principio había hecho esto:
                    //if (mapeo.containsKey(letraString)) {
                        //letraString = String.valueOf(mapeo.get(letraString));
                    //}
                    //else
                        //letraString = "x";
                    //sb.append(letraString);
            }
            resultado[i] = sb.toString();
        }
        escribirArchivo("salida.txt", resultado);

    }
    public static void transformarT9Texto(String rutaArchivo){ //No puedo reutlizar el metodo de antes porque ahí utilizo constantemente el string sin ivertir
        String[] string = ObtenerLineas(rutaArchivo);
        HashMap<Character, Character> mapeo = new HashMap<>();
        mapeo.put(' ', '0');
        mapeo.put('.', '1');
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "22233344455566677778889999";
        for (int i = 0; i < letras.length(); i++) {
            mapeo.put(letras.charAt(i), numeros.charAt(i));
            mapeo.put(Character.toLowerCase(letras.charAt(i)), numeros.charAt(i));
        }
        String[] resultado = new String[string.length];
        for (int i = string.length; 0 < i; i--) {
            String linea = string[i - 1];
            StringBuilder sb = new StringBuilder();
            for (int j = linea.length() ; 0 < j; j--) {
                char letra = linea.charAt(j - 1);
                sb.append(mapeo.getOrDefault(letra, 'x')); // Encontré este metodo que devuelve lo que encuentre en el hashmap y si no lo encuentra da un valor por defecto
                //Leído de: https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/
                //En un principio había hecho esto:
                //if (mapeo.containsKey(letraString)) {
                //letraString = String.valueOf(mapeo.get(letraString));
                //}
                //else
                //letraString = "x";
                //sb.append(letraString);
            }
            resultado[i-1] = sb.toString();
        }
        escribirArchivo("salida.txt", resultado);

    }
    public static String[] ObtenerLineas(String texto) {
        ArrayList<String> linhas = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(texto);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                System.out.println(lineaActual);
                linhas.add(lineaActual);
                lineaActual = br.readLine();
            }
            return linhas.toArray(new String[linhas.size()]);
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + texto);
            e.printStackTrace();
            return linhas.toArray(new String[linhas.size()]);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + texto);
            e.printStackTrace();
            return linhas.toArray(new String[linhas.size()]);
        } finally {
            System.out.println("El proceso ha terminado");
        }
    }
    public static void escribirArchivo(String nombreCompletoArchivo, String[] listaLineasArchivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(nombreCompletoArchivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++){
                String lineaActual = listaLineasArchivo[i];
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "+nombreCompletoArchivo);
            e.printStackTrace();
        }
    }
}
