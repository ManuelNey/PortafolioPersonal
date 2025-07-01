//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Character.isLetter;
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ContadorPalabras {
    public static int contarPalabras(String Texto) {
        String[] separadores = {"\n", "-", "_", ".", " ", ",", "!","/"};
        int contador = 0;
        boolean palabraValida = false;
        String TextoFormato = Texto.trim();
        for (int i = 0; i < TextoFormato.length(); i++) {
            char caracter = TextoFormato.charAt(i);
            if (isLetter(caracter)) {
                palabraValida = true;
            }
            if (Arrays.asList(separadores).contains(String.valueOf(caracter)) && palabraValida) {
                contador++;
                palabraValida = false;
            }
        }
        if (palabraValida) {
            contador++; //Si una palabra válida quedó al final y no detectada porque no termina en espacio pasa a ser contada
        }
        return contador;
    }
    public  static HashMap<String, Integer> contarVocalesYConsonantes(String Texto) {
        int contadorV = 0;
        int contadorC = 0;
        HashMap<String, Integer> restultado = new HashMap<String, Integer>();
        Texto = Texto.toLowerCase();
        String vocales = "aeiouáéíóú";
        for (int i = 0; i < Texto.length(); i++) {
            char c = Texto.charAt(i);
            if(isLetter(c)) {
                if (vocales.contains(String.valueOf(c))) {
                    contadorV++;
                }
                else{
                    contadorC++;
                }

            }
        }
        restultado.put("vocales", contadorV);
        restultado.put("consonantes", contadorC);
        return restultado;
    }
    public static int contarPalabrasMasLargasQue(String Texto, byte largoASuperar) {
        boolean palabraValida = false;
        String TextoFormato = Texto.trim();
        String[] separadores = {"\n", "-", "_", ".", " ", ",", "!","/"};
        int largoPalabra = 0;
        int palabrasMayores = 0;
        for (int i = 0; i < TextoFormato.length(); i++) {
            char caracter = TextoFormato.charAt(i);
            if (isLetter(caracter)) {
                palabraValida = true;
                largoPalabra++;
            }
            if (Arrays.asList(separadores).contains(String.valueOf(caracter)) && palabraValida) {
                if (largoPalabra > largoASuperar)
                {
                    palabrasMayores++;
                }
                largoPalabra = 0;
                palabraValida = false;
            }
        }
        if (palabraValida && largoPalabra > largoASuperar)
        {
            palabrasMayores++;
        }
        return palabrasMayores;
    }
    public static StringBuilder conversor(String Texto) {
        Texto = Texto.trim();
        Texto = Texto.toLowerCase();
        String[] vocalestilde = {"á", "é", "í", "ó", "ú"};
        String[] vocalesnormales = {"a", "e", "i", "o", "u"};
        StringBuilder textoTrasformado = new StringBuilder();
        for (int i = 0; i < Texto.length(); i++) {
            char c = Texto.charAt(i);
            byte lugarTilde = -1;
            for (Byte IndiceTilde = 0; IndiceTilde < vocalestilde.length; IndiceTilde++) {
                if (vocalestilde[IndiceTilde].charAt(0) == c) {
                    lugarTilde = IndiceTilde;
                    break;
                }
            }
            if (lugarTilde == -1) {
                c = vocalesnormales[lugarTilde].charAt(0);
            }
            textoTrasformado.append(c);
        }
        return textoTrasformado;
    }
    public static String [] separadorDePalabras(String[] Texto) { //Este metodo sirve ya que el lector de archivos nos da arrays de linea más no de palabras
        ArrayList<String> palabrasCoindentes = new ArrayList<>();
        for (int i = 0; i < Texto.length; i++) {
            String[] separadores = {"\n", "-", "_", ".", " ", ",", "!","/"};
            boolean palabraValida = false;
            String linea = Texto[i];
            StringBuilder palabra = new StringBuilder();
            for (int j = 0; j < linea.length(); j++) {
                char caracter = linea.charAt(j);
                if (isLetter(caracter)) {
                    palabraValida = true;
                }
                if (Arrays.asList(separadores).contains(String.valueOf(caracter)) && palabraValida) {
                    palabraValida = false;
                    palabrasCoindentes.add(palabra.toString());
                    palabra = new StringBuilder();
                }
                palabra.append(caracter);
            }
            if (palabraValida) {
                palabrasCoindentes.add(palabra.toString());
            }
        }
        return palabrasCoindentes.toArray(new String[0]);
    }
    public static String [] palabrasCoincidentes(String [] texto1, String [] texto2) {
        List<String> palabrasCoincidentes = new ArrayList<>();
        for (int i = 0; i < texto1.length; i++) {
            String palabra1 = texto1[i];
            if (Arrays.asList(texto2).contains(palabra1)&& !palabrasCoincidentes.contains(palabra1))
            {
                palabrasCoincidentes.add(palabra1);
            }
        }
        return palabrasCoincidentes.toArray(new String[0]);
    }
    public static String [] palabrasCoincidentesRelevantes(String [] texto1, String [] texto2) {
        String[] PalabrasComunes =
                {"de", "el", "la", "los", "las", "un", "una", "unos", "unas",
                "con", "en", "por", "para", "entre", "a", "y"};
        List<String> palabrasCoincidentes = new ArrayList<>();
        for (int i = 0; i < texto1.length; i++) {
            String palabra1 = texto1[i];
            if (Arrays.asList(texto2).contains(palabra1)&& !palabrasCoincidentes.contains(palabra1) && !Arrays.asList(PalabrasComunes).contains(palabra1))
            {
                palabrasCoincidentes.add(palabra1);
            }
        }
        return palabrasCoincidentes.toArray(new String[0]);
    }
    public static String [] Spliteador(String texto) {
        return texto.split(" ");
    }
    public static void Mostrador(String [] texto) {
        for (int i = 0; i < texto.length; i++) {
            System.out.println(texto[i]);
        }
    }
    public static String[] ObtenerLineas(String texto) {
        ArrayList<String> linhas = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(texto);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                System.out.println(lineaActual);
                linhas.add(lineaActual);
                lineaActual = br.readLine();
            }
            return linhas.toArray(new String[linhas.size()]);
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + texto);
            e.printStackTrace();
            return linhas.toArray(new String[linhas.size()]);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + texto);
            e.printStackTrace();
            return linhas.toArray(new String[linhas.size()]);
        } finally {
            System.out.println("Archivo leido satisfactoriamente");
        }
    }
    public static int cantPalabras(String [] lineasArchivo) {
        int acumulador = 0;
        for (int i = 0; i < lineasArchivo.length; i++) {
            String linea = lineasArchivo[i];
            acumulador += contarPalabras(linea);
        }
        return acumulador;
    }
}