package UT2_PD2;

public class Factorial {
    public int CalcularFactorial(int n) {
        if (n < 0)
        {
            throw new IllegalArgumentException("No existe el factorial de un negativo");
        }
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return n * CalcularFactorial(n - 1);
        }
    }
}
