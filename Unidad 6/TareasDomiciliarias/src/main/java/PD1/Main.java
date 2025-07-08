package PD1;

import medibles.*;
import tdas.TArbolTrie;
import tdas.THashMapTrie;
import utils.ManejadorArchivosGenerico;

public class Main {

    private static final int REPETICIONES = 20;
    /**
     * Método principal que ejecuta las mediciones de tiempo y memoria al buscar palabras en diferentes estructuras de datos.
     * Crea instancias de LinkedList, ArrayList, HashMap y TreeMap, y realiza inserciones y búsquedas en cada una.
     * Finalmente, escribe los resultados de las mediciones en un archivo CSV.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();
        THashMapTrie trieHash = new THashMapTrie();
        
        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src/main/java/PD1/listado-general_desordenado.txt");
        String[] palabrasBuscar = ManejadorArchivosGenerico.leerArchivo("src/main/java/PD1/listado-general_palabrasBuscar.txt");
        for (String p : palabrasclave) {
                // insertar la palabra p en el trie
                trie.insertar(p);
                trieHash.insertar(p);
        }

        Medible[] medibles = new Medible[2];
        int i = 0;
        medibles[i++] = new MedicionBuscarTrie(trie);
        medibles[i++] = new MedicionBuscarTrieHashMap(trieHash);
        Medicion mi;
	    i = 0;
        Object[] params = {REPETICIONES, palabrasBuscar};
        String[] lineas = new String[3];
		lineas[i++] = "algoritmo,tiempo,memoria";
		for (Medible m: medibles){
            mi= m.medir(params);
			System.out.println(mi.toString());
			lineas[i++] = mi.getTexto()+","+mi.getTiempoEjecucion().toString()+","+mi.getMemoria().toString();
        }
        ManejadorArchivosGenerico.escribirArchivo("src/main/java/PD1/salida.txt",lineas);
        Medible[] predecirmedibles = new Medible[2];
        int j = 0;
        predecirmedibles[j++] = new MedicionPredecirTrie(trie);
        predecirmedibles[j++] = new MedicionPredecirTrieHash(trieHash);
        Medicion mi2;
        j = 0;
        Object[] params2 = {REPETICIONES, "cas"};
        String[] lineas2 = new String[3];
        lineas2[j++] = "algoritmo,tiempo,memoria";
        for (Medible m: predecirmedibles){
            mi2= m.medir(params2);
            System.out.println(mi2.toString());
            lineas2[j++] = mi2.getTexto()+","+mi2.getTiempoEjecucion().toString()+","+mi2.getMemoria().toString();
        }
        ManejadorArchivosGenerico.escribirArchivo("src/main/java/PD1/salida2.txt",lineas2);
    }
}