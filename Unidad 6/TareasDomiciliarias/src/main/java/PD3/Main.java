package PD3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Prueba DecirLineasAleatorias ===");
        String rutaArchivo = "src/main/java/PD3/libro.txt";
        int cantidadLineas = 5;
        EjerciciosHashMap.DecirLineasAleatorias(rutaArchivo, cantidadLineas);

        System.out.println("\n=== EliminarValoresNull ===");
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("uno", "1");
        mapa.put("infinito", null);
        mapa.put("tres", "3");
        mapa.put("raiz de -1", null);
        System.out.println("Mapa antes: " + mapa);
        EjerciciosHashMap.eliminarValoresNull(mapa);
        System.out.println("Mapa después: " + mapa);

        System.out.println("\n---- InvertirClaveValor ----");
        HashMap<String, String> mapaOriginal = new HashMap<>();
        mapaOriginal.put("Messi", "10");
        mapaOriginal.put("Neymar", "11");
        mapaOriginal.put("Suarez", "9");
        try {
            HashMap<String, String> mapaInvertido = EjerciciosHashMap.invertirClaveValor(mapaOriginal);
            System.out.println("Mapa invertido: " + mapaInvertido);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

            System.out.println("\n=== ImprimirPorLargoYLexicografico ===");
        LinkedList<String> palabras = new LinkedList<>(Arrays.asList("banana", "melocotón", "manzana", "kiwi", "naranja", "melón", "uva"));
        EjerciciosHashMap.imprimirPorLargoYLexicografico(palabras);
    }
}
