package Ejercicio7;

public class StringBuilderEjemplos{
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder(50);
        StringBuilder sb3 = new StringBuilder("Hello World");
        StringBuilder sb4 = new StringBuilder("Batman");
        System.out.println(sb1); // ""
        System.out.println(sb2); // ""
        System.out.println(sb3); // "Hello World"
        System.out.println(sb4); // "Batman"

        sb4.setLength(3);
        System.out.println(sb4); // "Bat"

        sb2.ensureCapacity(100);
        System.out.println(sb2.capacity()); // Queda en 102 de capacidad

        sb4.append("girl");
        System.out.println(sb4); // "Batgirl"
        ;
        sb4.insert(0, "Pelea ");
        System.out.println(sb4); // "Pelea Batgirl"

        sb4.delete(0, 6);
        System.out.println(sb4); // "Batgirl"

        StringBuilder sb5 = new StringBuilder("Tenemos un 3312");
        sb5.deleteCharAt(12);
        System.out.println(sb5); // "Tenemos un 312

        sb3.reverse();
        System.out.println(sb3); // "dlroW olleH"
    }
}
