package Ejercicio11;

public class ComputeResult {
    public static void main(String[] args) {
        String original = "este es el curso de Programación 2";
        StringBuilder result = new StringBuilder("hola");
        int index = original.indexOf('a'); // 25
        System.out.println(index); // posición de a (la primera que aparece) es 25
        /*1*/   result.setCharAt(0, original.charAt(0));// eola
        System.out.println(result); // Cambia la h por la e
        /*2*/   result.setCharAt(1, original.charAt(original.length()-1)); // e2la
        System.out.println(result); // setea la o por el 2 (último char de original)
        /*3*/   result.insert(1, original.charAt(4)); // e 2la
        System.out.println(result); //inserta un espacio luego de la e
        /*4*/   result.append(original.substring(1,4)); // e 2laste
        System.out.println(result); // incorpora al final la cadena "ste"
        /*5*/   result.insert(3, (original.substring(index, index+2) + " ")); //e 2am laste
        System.out.println(result); // inserta la cadena "am"+ " " luego de 2
    }
}
