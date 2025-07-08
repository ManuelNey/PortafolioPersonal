package PD3;

public class ComparadorPalabras implements Comparable<ComparadorPalabras> {
    public ComparadorPalabras (int letras, String palabra){
        this.letras = letras;
        this.palabra = palabra;
    }
    private int letras;
    private String palabra;
    @Override
    public int compareTo(ComparadorPalabras otraPalabra) {
        int valorPorCantidadLetras = Integer.compare(letras,otraPalabra.letras);
        if (valorPorCantidadLetras != 0)
        {
            return valorPorCantidadLetras;
        }
        return palabra.compareTo(otraPalabra.palabra);
    }
    public String getPalabra(){
        return palabra;
    }
}
