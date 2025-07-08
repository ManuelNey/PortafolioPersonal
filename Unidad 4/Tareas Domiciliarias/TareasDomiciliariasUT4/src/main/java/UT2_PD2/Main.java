package UT2_PD2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println("El !4 es: " + factorial.CalcularFactorial(4));
        System.out.println("El !5 es: " + factorial.CalcularFactorial(5));
        System.out.println("El !0 es: " + factorial.CalcularFactorial(0));
        InvertirArray arreglo = new InvertirArray();
        int [] array = {1,2,3,4,5,6,7,8,9};
        arreglo.InvertirArrayConIndices(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}