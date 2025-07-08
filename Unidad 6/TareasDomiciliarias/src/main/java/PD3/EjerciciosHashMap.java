package PD3;

import tdas.ListaOrdenada_ExtensionLinkedList;
import utils.ManejadorArchivosGenerico;

import java.util.*;

public class  EjerciciosHashMap {
    public static <K,V> void eliminarValoresNull(HashMap<K,V> mapa)
    {
        Iterator<Map.Entry<K,V>> iter = mapa.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<K,V> par = iter.next();
            if (par.getValue() == null) {
                iter.remove(); // con un foreach explota
            }
        }
    }
    public static HashMap<String, String> invertirClaveValor(HashMap<String,String>  mapa)
    {
        HashMap<String, String> mapaInvertido = new HashMap<>();
        for (Map.Entry<String,String> par : mapa.entrySet()) //Recorrido en clave-valor es como un foreach
        {
            if (mapaInvertido.containsKey(par.getValue()))
            {
                throw new IllegalArgumentException("Clave repetida");
            }
            mapaInvertido.put(par.getValue(), par.getKey());
        }
        return mapaInvertido;
    }
    public static void imprimirPorLargoYLexicografico(LinkedList<String> palabras)
    {
        ListaOrdenada_ExtensionLinkedList<ComparadorPalabras> listaOrdenada = new ListaOrdenada_ExtensionLinkedList<>();
        for (String palabra : palabras)
        {
            ComparadorPalabras comparadorPalabras = new ComparadorPalabras(palabra.length(), palabra);
            listaOrdenada.addSorted(comparadorPalabras);
        }
        for (ComparadorPalabras parDato : listaOrdenada)
        {
            System.out.println(parDato.getPalabra());
        }
    }
    public static void DecirLineasAleatorias(String ruta, int cantidadDeLineas) {
        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(ruta);

        if (lineasArchivo.length == 0) {
            System.out.println("El archivo está vacío o no se pudo leer.");
            return;
        }

        if (cantidadDeLineas > lineasArchivo.length) {
            System.out.println("El archivo tiene menos líneas (" + lineasArchivo.length + ") que las solicitadas (" + cantidadDeLineas + ").");
            return;
        }

        List<String> listaLineas = new ArrayList<>(lineasArchivo.length);
        Collections.addAll(listaLineas, lineasArchivo);

        Collections.shuffle(listaLineas); //Mescla una collection dada, no puedo usar un array normal
        //Fuente: https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/

        for (int i = 0; i < cantidadDeLineas; i++) {
            System.out.println(listaLineas.get(i));
        }
    }
}
