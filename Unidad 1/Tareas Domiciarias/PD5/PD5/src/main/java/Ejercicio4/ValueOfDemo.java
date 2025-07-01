package Ejercicio4;

public class ValueOfDemo {
    public static void main(String[] args) {
        // this program requires two
        // arguments on the command line
        args = new String[2];
        args[0] = "13.4";
        args[1] = "66.1";
        if (args.length == 2) {
            // convert strings to numbers
            //float a = (Float.value (args[0])).floatValue(); Estas son las linea que presentaba el programa en el pdf
            // float b = (Float.valueOf(args[1])).float (); A la variable a le faltaba el of del metodo valueof
            float a = (Float.valueOf (args[0])).floatValue(); //Esta es la versión corregida del pdf
            float b = Float.valueOf(args[1]); // En la variable a está presente el .floatValue que es innecesario
            //Ya con escribir Float.valueOf(args[1]); es tipo retornado es el float, por lo que la opción utilizada con b sería la más óptima
            System.out.println("a + b = " +
                    (a + b));
            System.out.println("a - b = " +
                    (a - b));
            System.out.println("a * b = " +
                    (a * b));
            System.out.println("a / b = " +
                    (a / b));
            System.out.println("a % b = " +
                    (a % b));
        } else {
            System.out.println("This program " +
                    "requires two command-line arguments.");
        }
    }
}