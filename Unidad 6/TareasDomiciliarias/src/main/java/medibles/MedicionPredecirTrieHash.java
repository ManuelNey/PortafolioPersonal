package medibles;

import tdas.THashMapTrie;

/**
 * Clase que mide el tiempo y memoria al buscar elementos en una LinkedList.
 * Extiende la clase Medible para realizar mediciones específicas de búsqueda.
 */
public class MedicionPredecirTrieHash extends Medible {

    // Atributo que almacena la LinkedList sobre la cual se realizarán las búsquedas
    private THashMapTrie trie;

    /**
     * Constructor de la clase MedicionBuscarLinkedList.
     * Inicializa la LinkedList sobre la cual se realizarán las búsquedas.
     *
     * @param trie LinkedList que se utilizará para las búsquedas.
     */
    public MedicionPredecirTrieHash(THashMapTrie trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar( Object... params) {
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String)) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }
        int repeticion = (int) params[0];
        String prefijo = (String) params[1];
        for (int i = 0; i < repeticion; i++) {
            trie.predecir(prefijo);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        // El objeto cuyo tamaño en memoria se medirá es la LinkedList utilizada para las búsquedas.
        return this.trie;
    }
}
