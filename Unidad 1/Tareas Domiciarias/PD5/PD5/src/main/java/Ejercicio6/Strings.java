/*
 * Ejemplos de métodos de la clase String (JDK 8)
 */
package Ejercicio6;//
import java.util.Arrays;

public class Strings {
    public static void main(String[] args) {
        String str1 = "Buen día, como está?";
        String sub1 = str1.substring(10);
        System.out.println("substring(4): " + sub1);

        String sub2 = str1.substring(0, 4);
        System.out.println("substring(0,4): " + sub2);

        String str2 = "neymar,suarez,messi";
        String[] arr1 = str2.split(",");
        System.out.println("split(regex):"+Arrays.toString(arr1));

        String[] arr2 = str2.split(",", 2);
        System.out.println("split(regex, limit):"+Arrays.toString(arr2)); //Messi queda con suarez y neymar queda como un elemento diferente

        CharSequence seq = str1.subSequence(5, 8);
        System.out.println("subSequence():"+seq);  // dia

        String str3 = "   Holaa   ";
        System.out.println("trim():"+ str3.trim());  // "Holaa"

        String str4 = "Spiderman tira redes";
        System.out.println("toLowerCase: " + str4.toLowerCase());  // "spidermam tira redes"
        System.out.println("toUpperCase: " + str4.toUpperCase());  // "SPIDERMAN TIRA REDES"

        System.out.println("indexOf('a'): " + str4.indexOf('a'));  // 7

        System.out.println("indexOf('a',9): " + str4.indexOf('a', 9));  // 13

        String str5 = "Hoy es domingo";
        System.out.println("lastIndexOf('o'): " + str5.lastIndexOf('o'));  // 13

        System.out.println("contains(\"Hoy\"): " + str5.contains("Hoy"));  // es true

        String str6 = "cuadrado";
        System.out.println("replace('a','x'): " + str6.replace('a', 'x'));  // "cuxdrxdo"

        System.out.println("replace(\"ado\", \"ilatero\"): " + str6.replace("ado", "ilatero"));  // "bxyzna"

        String str7 = "A20-B12-C5";
        System.out.println("replaceAll(\\d, 'X'): " + str7.replaceAll("\\d", "X"));  // AXX-BXX-CX

        System.out.println("replaceFirst(\\d, 'X'): " + str7.replaceFirst("\\d", "X"));  // AX0-B12-C5
    }
}