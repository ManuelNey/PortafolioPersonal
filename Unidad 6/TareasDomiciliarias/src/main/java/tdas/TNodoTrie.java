package tdas;

import java.io.Serializable;
import java.util.LinkedList;

public class TNodoTrie implements Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private char etiqueta;
    private LinkedList<Integer> paginas;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    public void iniciarListaPaginas()
    {
        paginas = new LinkedList<Integer>();
    }
    public boolean hayPaginas()
    {
        if (paginas == null)
        {
            return false;
        }
        return true;
    }
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
                nodo.hijos[indice].etiqueta = unaPalabra.charAt(c);
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }
    public void insertar(String unaPalabra, Integer pagina) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
                nodo.hijos[indice].etiqueta = unaPalabra.charAt(c);
            }
            nodo = nodo.hijos[indice];
        }
        //Implementación propia del pd2
        if (!nodo.hayPaginas())
        {
            nodo.esPalabra = true;
            nodo.iniciarListaPaginas();
        }
        if (!nodo.paginas.contains(pagina))
        {
            nodo.paginas.add(pagina);
        }

    }

    private void imprimir(String separador, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra && nodo.paginas != null) {
                System.out.print(separador + ": ");
                for (Integer pagina : nodo.paginas) {
                    System.out.print(pagina + " ");
                }
                System.out.println();
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(separador + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    public void imprimir() {

        imprimir("", this);
    }
    public void predecir(String prefijo, LinkedList<String> palabras) {
        if (prefijo == null || palabras == null) {
            return;
        }
        TNodoTrie nodo = buscarUltimaLetra(prefijo);
        if (nodo == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(prefijo);
        nodo.cargaPalabrasConPrefijo(palabras, sb);
    }

    private static int darPosicion(char c) {
        return (int) c - (int) 'a';
    }

    private int contadorPalabras(TNodoTrie nodo) {
        int contador = 0;
        if (nodo.esPalabra) {
            contador++;
        }

        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (nodo.hijos[c] != null) {
                contador += contadorPalabras(nodo.hijos[c]);
            }
        }
        return contador;
    }

    private TNodoTrie buscarUltimaLetra(String s) {
        if (s == null) {
            return null;
        }
        if (s.isEmpty()) {
            return this;
        }
        char primerLetra = s.charAt(0);
        int posicion = darPosicion(primerLetra);
        TNodoTrie nodo = hijos[posicion];
        if (hijos[posicion] == null) {
            return null;
        } else {
            String palabraCortada = s.substring(1);
            return nodo.buscarUltimaLetra(palabraCortada);
        }
    }

    private void cargaPalabrasConPrefijo(LinkedList<String> lista, StringBuilder sb) {
        if (this.esPalabra) {
            lista.add(sb.toString());
        }
        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (this.hijos[c] != null) {
                sb.append(this.hijos[c].etiqueta);
                this.hijos[c].cargaPalabrasConPrefijo(lista, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public void listarPalabras(StringBuilder sb)
    {
        if (this.esPalabra) {
            System.out.println(sb+" " + this.paginas.toString());
        }
        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (this.hijos[c] != null) {
                sb.append(this.hijos[c].etiqueta);
                this.hijos[c].listarPalabras(sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    public int buscar(String s) {
        int comparaciones = 0;
        TNodoTrie nodo = this;
        for (int i = 0; i < s.length(); i++) {
            int posicion = darPosicion(s.charAt(i));
            if (nodo.hijos[posicion] == null) {
                return 0;
            }
            nodo = nodo.hijos[posicion];
            comparaciones++;
        }
        if (nodo.esPalabra) {
            return comparaciones;
        } else {
            return 0;
        }
    }

    public LinkedList<Integer> buscarPaginas(String s) {
        TNodoTrie nodo = this;
        for (int i = 0; i < s.length(); i++) {
            int posicion = darPosicion(s.charAt(i));
            if (nodo.hijos[posicion] == null) {
                return null;
            }
            nodo = nodo.hijos[posicion];
        }
        if (nodo.esPalabra && nodo.paginas != null) {
            return nodo.paginas;
        } else {
            return null;
        }
    }

    public int buscarCantidadPrefijo(String s) {
        if (s == null) {
            return 0;
        }
        if (s.isEmpty()) {
            return contadorPalabras(this);
        }

        TNodoTrie nodoFinal = buscarUltimaLetra(s);
        if (nodoFinal == null) {
            return 0;
        }

        int cantPalabras = contadorPalabras(nodoFinal);
        return cantPalabras > 0 ? 1 : 0;
    }

}
