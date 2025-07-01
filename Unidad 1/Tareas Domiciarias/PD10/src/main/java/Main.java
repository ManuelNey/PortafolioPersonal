import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] lineasTexto1 = ContadorPalabras.ObtenerLineas("src/main/java/textoALeer1");
        String[] lineasTexto2 = ContadorPalabras.ObtenerLineas("src/main/java/textoALeer2");

        String[] texto1Palabras = ContadorPalabras.separadorDePalabras(lineasTexto1);
        String[] texto2Palabras = ContadorPalabras.separadorDePalabras(lineasTexto2);

        System.out.println(Arrays.toString(ContadorPalabras.palabrasCoincidentes(texto2Palabras, texto1Palabras)));

        String [] Ar1 = {"Hola", "mundo", "de", "los", "algoritmos"};
        String [] Ar2 = {"Hola", "mundo", "de", "la", "informatica"};

        System.out.println(Arrays.toString(ContadorPalabras.palabrasCoincidentes(Ar1,Ar2)));
    }
}
