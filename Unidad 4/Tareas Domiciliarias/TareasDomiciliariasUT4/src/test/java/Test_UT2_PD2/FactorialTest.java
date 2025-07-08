package Test_UT2_PD2;

import UT2_PD2.Factorial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void factorialDe5() {
        Factorial factorial = new Factorial();
        int resultado = factorial.CalcularFactorial(5);
        assertEquals(120, resultado);
    }
    @Test
    void factorialDe0() {
        Factorial factorial = new Factorial();
        int resultado = factorial.CalcularFactorial(0);
        assertEquals(1, resultado);
    }
    @Test
    void factorialDe4() {
        Factorial factorial = new Factorial();
        int resultado = factorial.CalcularFactorial(4);
        assertEquals(24, resultado);
    }
    @Test
    void factorialDeNegativo() {
        Factorial factorial = new Factorial();
        try {
            factorial.CalcularFactorial(-1);
            fail("No saltó la excepcion");
        }
        catch (IllegalArgumentException excepcion) {
            System.out.println("No existe el factorial de un numero negativo");
        }
    }
}
