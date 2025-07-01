package Ejercicio8;

public class Ejercicio8 {
    public static StringBuilder sb = new StringBuilder("Able was I ere I saw Elba.");
    //La capacidad del StringBuilder inicia en 16 y a esto hay que sumarle cada caracter que tenga
    //Los caracteres de sb son 26
    public static void main(String [] args) {
        System.out.println("Capacidad inicial: " + sb.capacity()); // Imprime 26 + 16
        System.out.println("Capacidad inicial: " + (sb.length()+ 16));
    }
}
