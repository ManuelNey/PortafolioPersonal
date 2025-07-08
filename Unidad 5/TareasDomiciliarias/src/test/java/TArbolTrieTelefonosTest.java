import PD7.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TArbolTrieTelefonosTest {

    @Test
    public void testInsertarYBuscarPorPaisYArea() {
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();
        TAbonado a1 = new TAbonado("AnaClara", "59898123456");
        TAbonado a2 = new TAbonado("Luis", "59898111222");

        trie.insertar(a1);
        trie.insertar(a2);

        LinkedList<TAbonado> resultado = trie.buscarTelefonos("598", "98");
        assertEquals(2, resultado.size());
        assertEquals(resultado.getFirst().getNombre(), "AnaClara");
        assertEquals(resultado.getLast().getNombre(), "Luis");
    }

    @Test
    public void testBuscarPorPrefijo() {
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();
        trie.insertar(new TAbonado("Ana", "59893127456"));
        trie.insertar(new TAbonado("PedroPascal", "59893111222"));
        trie.insertar(new TAbonado("Clara", "59893123456"));

        LinkedList<TAbonado> resultado = new LinkedList<>();
        trie.buscarPorPrefijo("5989", resultado);

        assertEquals(3, resultado.size());
        assertEquals(resultado.getFirst().getNombre(),"Ana");
        assertEquals(resultado.getLast().getNombre(), "PedroPascal");
    }

    @Test
    public void testInsertarNumeroInvalidoLargo() {
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();
        trie.insertar(new TAbonado("Messi", "123"));

        LinkedList<TAbonado> resultado = trie.buscarTelefonos("123", "12");
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testInsertarConCaracteresInvalidos() {
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();
        trie.insertar(new TAbonado("Pedro", "59g98A123B6"));

        assertTrue(trie.buscarTelefonos("598", "78").isEmpty());
    }

    @Test
    public void testBuscarEnTrieVacio() {
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();
        LinkedList<TAbonado> resultado = trie.buscarTelefonos("598", "98");
        assertNull(resultado);
    }
}