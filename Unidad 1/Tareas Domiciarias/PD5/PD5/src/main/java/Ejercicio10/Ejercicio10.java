package Ejercicio10;

public class Ejercicio10 {
    public static String string = "Was it a car or a cat I saw?";
    public static void main(String[] args) {
        System.out.print((string.substring(9, 12)).length());
        //largo = caracter 9,10,11 (12 no es incluido)
        //Siendo la palabra car el substring
    }
}

