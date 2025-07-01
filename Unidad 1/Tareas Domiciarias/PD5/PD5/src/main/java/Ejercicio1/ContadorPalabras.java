package Ejercicio1;

import java.util.HashMap;

import static java.lang.Character.isLetter;

public class ContadorPalabras {
    public enum Vocal{
        a,e,i,o,u,á,é,í,ú,ó
    }

    public static boolean isVocal(char c) {
        Vocal vocales[] = Vocal.values();
        for (int i = 0; i < vocales.length; i++) {
            if (vocales[i].name().charAt(0) == c) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<String, Integer> contarVocalesYConsonantes(String Texto) {
        int contadorV = 0;
        int contadorC = 0;
        HashMap<String, Integer> restultado = new HashMap<String, Integer>();
        Texto = Texto.toLowerCase();
        for (int i = 0; i < Texto.length(); i++) {
            char c = Texto.charAt(i);
            if (isLetter(c)) {
                if (isVocal(c)) { //Usa el metodo IsVocal para corroborar
                    contadorV++;
                } else { //Sino la cuenta como una consonante
                    contadorC++;
                }

            }
        }
        restultado.put("vocales", contadorV);
        restultado.put("consonantes", contadorC);
        return restultado;
    }
    public static void main(String[] args) {
        System.out.println(ContadorPalabras.contarVocalesYConsonantes("Hola me gusta la comida"));
    }
}
