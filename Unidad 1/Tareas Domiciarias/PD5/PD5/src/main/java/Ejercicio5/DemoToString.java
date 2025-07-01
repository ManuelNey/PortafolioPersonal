package Ejercicio5;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.PrintStream;

public class DemoToString {
    public DemoToString() {
    }

    public static void main(String[] args) {
        double d = 888.51; //Numero guardado en tipo double
        String s = Double.toString(d); //Trasforma en el tipo String al tipo double
        int dot = s.indexOf("."); //Busca el indice del . en el String de 0(izquierda) al len del string (derecha)
        System.out.println(dot + " digits " +
                "before decimal point.");
        System.out.println( (s.length() - dot - 1) +
                " digits after decimal point."); //Hace el largo del string - donde está el "." - 1(porque existe el indice 0)
        // devolviendo la cantidad de caracteres que hay después del "."
        //Salida:
        //3 digits before decimal point.
        //2 digits after decimal point.
        //Esta es la salida ya que el codigo busca la posición del "." En base a eso revisa cuantos digitos hay antes y cuantos hay después

    }
}
