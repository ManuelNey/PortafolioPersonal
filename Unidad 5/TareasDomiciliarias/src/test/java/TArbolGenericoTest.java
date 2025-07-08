import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tda.TArbolGenerico;
import PD1.PartesFacultad;


public class TArbolGenericoTest {
    private TArbolGenerico arbolUCU;

    @BeforeEach
    public void setUp() {
        arbolUCU = new TArbolGenerico();
    }

    @Test
    public void testInsertarRaiz() {
        assertTrue(arbolUCU.insertar("Rectoria", new PartesFacultad("Rectoria"), ""));
    }

    @Test
    public void testInsertarHijo() {
        assertTrue(arbolUCU.insertar("Rectoria", new PartesFacultad("Rectoria"), ""));
        assertTrue(arbolUCU.insertar("Vicerrectoria", new PartesFacultad("Vicerrectoria"), "Rectoria"));
    }

    @Test
    public void testInsertarConPadreInexistente() {
        assertFalse(arbolUCU.insertar("Vicerrectoria", new PartesFacultad("Vicerrectoria"), "Batman"));
    }

    @Test
    public void testBuscarExistente() {
        assertTrue(arbolUCU.insertar("Rectoria", new PartesFacultad("Rectoria"), ""));
        assertTrue(arbolUCU.insertar("Vicerrectoria", new PartesFacultad("Vicerrectoria"), "Rectoria"));
        assertNotNull(arbolUCU.buscar("Vicerrectoria"));
    }

    @Test
    public void testBuscarInexistente() {
        assertTrue(arbolUCU.insertar("Rectoria", new PartesFacultad("Rectoria"), ""));
        assertNull(arbolUCU.buscar("DirectoresDeCarrera"));
    }

    @Test
    public void testListarIndentado() {
        assertTrue(arbolUCU.insertar("Rectoria", new PartesFacultad("Rectoria"), ""));
        assertTrue(arbolUCU.insertar("Vicerrectoria", new PartesFacultad("Vicerrectoria"), "Rectoria"));
        assertTrue(arbolUCU.insertar("Facultad", new PartesFacultad("Facultad"), "Vicerrectoria"));
        assertEquals(arbolUCU.listarIndentado(), "Rectoria\n" + "\tVicerrectoria\n" + "\t\tFacultad\n");

    }
}