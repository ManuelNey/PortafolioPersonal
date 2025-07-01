public class Ejercicio2 {
    public static void main(String[] args) {
        String s1 = "Hola"; //Este string se suma a la String Pool ya que antes no estaba presente esta cadena
        String s2 = "Hola"; //Pero el string en realidad lo que hace es saber que ya existe una igual a ella y por eso no generar 2 strings
        //Ambos comparten la misma cadena solo que tienen una referencia distinta (las variables) y comparten el mismo espacio de memoria
        if ( s1 == s2 ) {
            System.out.println( "True" ); //Por eso se imprime True (son la misma cadena en la string pool, ambas hacen referencia al mismo objeto)

        } else {
            System.out.println( "False" );
        }
        String g1 = new String("Hola"); // cadena2 es la que se crea en el heap como un objeto nuevo.
        String g2 = "Hola"; // cadena1 es donde se guarda directamente en el String Pool.
        if ( g1 == g2 ) { //Es false porque compara referencias distintas
            System.out.println( "True" );
        } else {
            System.out.println( "False" );
        }
        String s = "1";
        //Si tenmos que utilizar el intern sería de esta manera
        while (s.intern() != "1000") { // Ahora lo que hace es comparar referencias del pool de java (no solo comparando referencia y los datos dinámicos)
            s += "0";
            System.out.println(s);
        }
        System.out.println("Ya salí del bucle");
        //Pero podría ser una mejor solución si solo nos importa que los valores sean igual el utilizar el equals
        String a = "1";
        while (!a.equals("1000")) {
            a += "0";
            System.out.println(a);
        }
        System.out.println("Ya salí del bucle");
    }
}
// Referencias utilizadas:
// Java Language Specification (JLS) §3.10.5: https://docs.oracle.com/javase/specs/jls/se17/html/jls-3.html#jls-3.10.5

// Keep Coding: https://keepcoding.io/blog/que-hace-string-intern-en-java/

// ArquitecturaJava (Cecilio Álvarez): https://www.arquitecturajava.com/java-string-pool-un-concepto-importante/