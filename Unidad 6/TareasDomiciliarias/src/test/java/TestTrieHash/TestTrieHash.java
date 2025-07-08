package TestTrieHash;

import org.junit.jupiter.api.BeforeEach;
import tdas.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrieHash {
    private TNodoTrieHashMap trie;
    @BeforeEach
    public void setUp() {
        trie = new TNodoTrieHashMap();
    }

    @Test
    public void testInsertarPalabra() {
        trie.insertar("casa");
        int comparaciones = trie.buscar("casa");
        assertTrue(comparaciones > 0);
    }

    @Test
    public void testInsertarMultiplesPalabras() {
        trie.insertar("casa");
        trie.insertar("caso");
        trie.insertar("cama");
        int comparaciones = trie.buscar("caso");
        assertTrue(comparaciones > 0);
    }

    @Test
    public void testBuscarPalabraInexistente() {
        trie.insertar("casa");
        int comparaciones = trie.buscar("cosa");
        assertEquals(0, comparaciones);
    }

    @Test
    public void testBuscarPalabraExistente() {
        trie.insertar("algoritmo");
        int comparaciones = trie.buscar("algoritmo");
        assertEquals(9, comparaciones);
    }

    @Test
    public void testBuscarPrefijo() {
        trie.insertar("casa");
        trie.insertar("caso");
        trie.insertar("cosa");
        LinkedList<String> palabras = new LinkedList<>();
        trie.predecir("cas", palabras);
        assertEquals(2, palabras.size());
    }

    @Test
    public void testPrediccionContenidoLista() {
        trie.insertar("casa");
        trie.insertar("caso");
        LinkedList<String> palabras = new LinkedList<>();
        trie.predecir("cas", palabras);
        assertTrue(palabras.contains("casa"));
    }

    @Test
    public void testPrediccionPrefijoInexistente() {
        trie.insertar("casa");
        trie.insertar("caso");
        LinkedList<String> palabras = new LinkedList<>();
        trie.predecir("cam", palabras);
        assertTrue(palabras.isEmpty());
    }

    @Test
    public void testPrediccionPrefijoCompleto() {
        trie.insertar("casa");
        trie.insertar("caso");
        LinkedList<String> palabras = new LinkedList<>();
        trie.predecir("casa", palabras);
        assertEquals(1, palabras.size());
    }

    @Test
    public void testBuscarCantidadPrefijo() {
        trie.insertar("casa");
        trie.insertar("caso");
        trie.insertar("cama");
        int cantidad = trie.buscarCantidadPrefijo("ca");
        assertEquals(3, cantidad);
    }

    @Test
    public void testBuscarCantidadPrefijoInexistente() {
        trie.insertar("casa");
        trie.insertar("caso");
        int cantidad = trie.buscarCantidadPrefijo("co");
        assertEquals(0, cantidad);
    }

    @Test
    public void testBuscarCantidadPrefijoVacio() {
        trie.insertar("casa");
        trie.insertar("caso");
        trie.insertar("lote");
        int cantidad = trie.buscarCantidadPrefijo("");
        assertTrue(cantidad >= 3);
    }

    }
