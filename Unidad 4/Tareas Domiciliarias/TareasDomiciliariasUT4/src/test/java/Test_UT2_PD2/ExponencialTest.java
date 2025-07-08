package Test_UT2_PD2;

import UT2_PD2.Exponencial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExponencialTest {
    private Exponencial exponencial = new Exponencial();
    @Test
    public void exponencialTest1() {
        assertEquals((float)Math.pow(2, 3),exponencial.calcularExponencial (2f, 3f));
    }
    @Test
    public void exponencialTest2() {
        assertEquals((float)Math.pow(5, -2),exponencial.calcularExponencial (5, -2));
    }
    @Test
    public void exponencialTest3() {
        assertEquals((float)Math.pow(-2, 3),exponencial.calcularExponencial (-2, 3));
    }

}
