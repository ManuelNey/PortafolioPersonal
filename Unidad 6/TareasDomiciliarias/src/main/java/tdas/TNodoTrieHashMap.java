package tdas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMap implements INodoTrie, Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private char etiqueta;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char letra = unaPalabra.charAt(c);
            if (nodo.hijos.get(letra) == null) {
                nodo.hijos.put(letra, new TNodoTrieHashMap());
            }
            nodo = nodo.hijos.get(letra);
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (Character clave : nodo.hijos.keySet()) {
                if (nodo.hijos.get(clave) != null) {
                    imprimir(s + (clave ), nodo.hijos.get(clave));
                }
            }
        }
    }

    @Override
    public void imprimir() {

        imprimir("", this);
    }

    private TNodoTrieHashMap buscarNodoTrie(String s) {
        TNodoTrieHashMap nodo = this;

        // implementar

        return nodo;
    }


    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        if (prefijo == null || palabras == null) {
            return;
        }
        TNodoTrieHashMap nodo = buscarUltimaLetra(prefijo);
        if (nodo == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(prefijo);
        nodo.cargaPalabrasConPrefijo(palabras, sb);
    }

    private static int darPosicion(char c) {
        return (int) c - (int) 'a';
    }

    private int contadorPalabras(TNodoTrieHashMap nodo) {
        int contador = 0;
        if (nodo.esPalabra) {
            contador++;
        }

        for (Character clave : nodo.hijos.keySet()) {
                contador += contadorPalabras(nodo.hijos.get(clave));
        }
        return contador;
    }

    private TNodoTrieHashMap buscarUltimaLetra(String s) {
        if (s == null) {
            return null;
        }
        if (s.isEmpty()) {
            return this;
        }
        char primerLetra = s.charAt(0);
        TNodoTrieHashMap nodo = hijos.get(primerLetra);
        if (hijos.get(primerLetra) == null) {
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
        for (Character clave : hijos.keySet()) {
            sb.append(clave);
            this.hijos.get(clave).cargaPalabrasConPrefijo(lista, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int buscar(String s) {
        int comparaciones = 0;
        TNodoTrieHashMap nodo = this;
        for (int i = 0; i < s.length(); i++) {
            if (nodo.hijos.get(s.charAt(i)) == null) {
                return 0;
            }
            nodo = nodo.hijos.get(s.charAt(i));
            comparaciones++;
        }
        if (nodo.esPalabra) {
            return comparaciones;
        } else {
            return 0;
        }
    }
    public TNodoTrieHashMap buscarNodo(String s) {
        TNodoTrieHashMap nodo = this;
        for (int i = 0; i < s.length(); i++) {
            int posicion = darPosicion(s.charAt(i));
            if (nodo.hijos.get(s.charAt(i)) == null) {
                return null;
            }
            nodo = nodo.hijos.get(s.charAt(i)) ;
        }
        if (nodo.esPalabra) {
            return nodo;
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

        TNodoTrieHashMap nodoFinal = buscarUltimaLetra(s);
        if (nodoFinal == null) {
            return 0;
        }

        int cantPalabras = contadorPalabras(nodoFinal);
        return cantPalabras;
    }
}
