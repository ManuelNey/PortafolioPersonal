package PD4;

import PD3.ComparadorPalabras;
import tdas.ListaOrdenada;
import tdas.ListaOrdenada_ExtensionLinkedList;
import utils.ManejadorArchivosGenerico;

import java.util.*;

public class ContadorFrecuenciasPalabras {

    public static void palabrasConcurrentes(String rutaArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);

        HashMap<String, Integer> palabrasTexto = new HashMap<>();

        for (String linea : lineas) {
            String[] palabraslinea = linea.toLowerCase().split(" ");

            for (String palabra : palabraslinea) {
                if (!palabra.isEmpty()) {
                    if (palabrasTexto.get(palabra) == null) {
                        palabrasTexto.put(palabra, 1);
                    } else {
                        int valor = palabrasTexto.get(palabra);
                        palabrasTexto.replace(palabra, ++valor);
                    }
                }
            }
        }

        Set<Map.Entry<String, Integer>> entradas = palabrasTexto.entrySet();
        ListaOrdenada<String> listaOrdenada = new ListaOrdenada<>();

        for (Map.Entry<String, Integer> par : entradas) {
            listaOrdenada.insertar(par.getKey(),new ComparadorPalabras(par.getValue(), par.getKey()));
        }
        System.out.println("Top 10 palabras más frecuentes:");
        int tamañoMinimo = Math.min(listaOrdenada.cantElementos(), 10); //Control por si no hay 10 palabras
        int cantidadPalabrasContadas = 0;
        for (String palabra : listaOrdenada) {
            if (cantidadPalabrasContadas >= tamañoMinimo){
                break;
            }
            System.out.println(palabra);
        }
    }
}
