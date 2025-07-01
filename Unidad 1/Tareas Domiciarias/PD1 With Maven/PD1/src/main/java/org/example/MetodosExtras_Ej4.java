package org.example;

public class MetodosExtras_Ej4 {
    public static int recorrer (String cadena) {
        int res = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != ' ') {
                res++;
            }
        }
        return res;
    }
    public static int getValor() {
        int vector[] = { 6, 16, 26,36,46,56,66,76 };
        int idx = 7;
        return vector[idx];
    }
    public static char getPrimerCaracter(String palabra) {
        return palabra.charAt(0);
    }
    public static String paraAString(int a) {
        return String.valueOf(a);
    }
    public static void main (String[] args) {
        System.out.println(recorrer("Messi")); //b) El rango para recorrer el string no era correcto (se pasaba por 1)
        System.out.println(getValor()); //c) Se le pide el vector ocupado en el espacio 8 pero tenemos únicamente del 0 al 7 hay un error ya que se le pide algo fuera de rango al método
        System.out.println(getPrimerCaracter("Pepe")); //d)  Se hace una nullreference exception porque estamos utilizando una array vacía y llamando a su segundo elemeneto de tipo char
        System.out.println(paraAString(4)); //e) Intenta castear un int a string haciendolo un Interger
    }
}
