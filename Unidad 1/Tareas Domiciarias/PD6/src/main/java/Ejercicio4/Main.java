package Ejercicio4;

public class Main {
    public static void main(String[] args) {
        int[] primero = {0,3,2,9};
        int[] segundo = {1,5,4,7};
        System.out.println(multiplicarVectores(primero, segundo));
    }
    public static int multiplicarVectores(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return -1;
        }
        int suma = 0;
        for (int i = 0; i < array1.length; i++) {
            suma += array1[i] * array2[i];
        }
        return suma;
    }
    //La idea principal del código es primero revisar si ambos vectores se pueden multiplicar, para ento
    //verificamos que ambos tengan la misma longitud / cantidad de elementos
    //Luego hay que multiplicar los elementos del mismo índice/vector para ser sumado para el resultado final
    //Y así sucesivamente hasta terminar de utilizar todos los elementos
}
