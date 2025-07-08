package PD2;

import tda.TArbolTrie;
import utils.ManejadorArchivosGenerico;

import java.util.LinkedList;

public class Main {
    public static void main(String[] arg)
    {
        TArbolTrie trie = new TArbolTrie();
        ManejadorArchivosGenerico manejadorArchivosGenerico = new ManejadorArchivosGenerico();
        String[] lineas = manejadorArchivosGenerico.leerArchivo("src/main/java/PD2/palabras.txt");
        for (String linea : lineas )
        {
            String[] parametros = linea.split(",");
            String palabra = parametros[0];

            for (int i = 1; i < parametros.length ;i++)
            {
                trie.insertarConPaginas(palabra,Integer.parseInt(parametros[i].trim()));
            }
        }
        System.out.println(trie.buscarPaginas("Ala"));
        System.out.println(trie.buscarPaginas("pROGRAMA"));
    }
}
