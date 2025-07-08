package UT5_PD3;

import tda.TArbolTrie;

public class Main {
    public static void main(String[] arg)
    {
        TArbolTrie triePalabrasIndice = new TArbolTrie();
        triePalabrasIndice.indizarLibro("src/main/java/UT5_PD3/PalabrasIndice/PalabrasIndice.txt");

        TArbolTrie trie = new TArbolTrie();
        trie.indizarLibro("src/main/java/UT5_PD3/libro/libro1.txt");
        trie.imprimirIndice();
    }
}
