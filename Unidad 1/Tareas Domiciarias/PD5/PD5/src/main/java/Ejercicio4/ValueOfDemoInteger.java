package Ejercicio4;

public class ValueOfDemoInteger {
    public static void main(String[] args) {
        // this program requires two
        // arguments on the command line
        args = new String[2];
        args[0] = "-0,1";
        args[1] = "3";
        if (args.length == 2) {
            // convert strings to numbers
            try {
                Integer a = Integer.valueOf(args[0]); //Ya con este método cumpli
                Integer b = Integer.valueOf(args[1]);
                if (a < 0 || b < 0 ) {
                    System.out.println("Valor invalido, debe ser positivo");
                }
                else {
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
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Numero Invalido, debe ser entero");
            }


        } else {
            System.out.println("This program " +
                    "requires two command-line arguments.");
        }
    }
}