package Test_UT2_PD2;

import UT2_PD2.SumaLinea;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SumaLinealTest {

    @Test
    void sumaDeCuatroElementos() {
        SumaLinea sl = new SumaLinea();
        int[] arreglo = {7, 9, 1, 2, 3};
        int resultado = sl.SumaLinea(arreglo, 3);
        assertEquals(17, resultado);
    }


    @Test
    void sumaIngresoMayorAlArray() {
        SumaLinea sl = new SumaLinea();
        int[] arreglo = {10, 20, 30};
        try {
            sl.SumaLinea(arreglo, 4);
            fail("Se esperaba una IllegalArgumentException para un indice mayor al permitido, pero no se lanzó");
        } catch (IllegalArgumentException exceptionFueraDeRango) {
            System.out.println("Se lanzó la excepción");
        }
    }

    @Test
    void cantidadNegativaLanzaExcepcion() {
        SumaLinea sl = new SumaLinea();
        int[] arreglo = {1, 2, 3};
        try {
            sl.SumaLinea(arreglo, -1);
            fail("Se esperaba una IllegalArgumentException para cantidad negativa, pero no se lanzó");
        } catch (IllegalArgumentException exceptionNegativo) {
            System.out.println("Se lanzó la excepción");
        }
    }
    @Test
    void arrayVacio() {
        SumaLinea sl = new SumaLinea();
        int[] arreglo = {};
        try {
            sl.SumaLinea(arreglo, 1);
            fail("Se esperaba una IllegalArgumentException para array vacio, pero no se lanzó");
        } catch (IllegalArgumentException exceptionNegativo) {
            System.out.println("Se lanzó la excepción");
        }
    }
}
