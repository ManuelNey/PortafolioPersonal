//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileReader;


import static java.lang.Character.isLetter;
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ContadorDePalabras {
    public int contarPalabras(String Texto) {
        int contador = 0;
        boolean palabraValida = false;
        String TextoFormato = Texto.trim();
        for (int i = 0; i < TextoFormato.length(); i++) {
            if (isLetter(TextoFormato.charAt(i)))
            {
                palabraValida = true;
            }
            if (TextoFormato.charAt(i) == ' ' && palabraValida) {
                contador++;
                palabraValida = false;
            }
        }
        if (palabraValida)
        {
            contador++; //Si una palabra válida quedó al final y no detectada porque no termina en espacio pasa a ser contada
        }
        return contador;
    }
    public int contarVocales(String Texto) {
        int contador = 0;
        String[] vocales = {"a", "e", "i", "o", "u"};
        for (int i = 0; i < Texto.length(); i++) {
            char c = Texto.charAt(i);
            if (Arrays.asList(vocales).contains(String.valueOf(c)))
            {
                contador++;
            }
        }
        return contador;
    }
    public int contarPalabrasMasLargasQue(String Texto, byte largoASuperar) {
        boolean palabraValida = false;
        String TextoFormato = Texto.trim();
        int largoPalabra = 0;
        int palabrasMayores = 0;
        for (int i = 0; i < TextoFormato.length(); i++) {
            if (isLetter(TextoFormato.charAt(i)))
            {
                palabraValida = true;
                largoPalabra++;
            }
            if (TextoFormato.charAt(i) == ' ' && palabraValida) {
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
    public StringBuilder conversor(String Texto) {
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
    public void CrearArchivo()
    {
        File archivo = new File ("c:\\usuarios\\");
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader (fr);
    }

}