package tdas;

import java.io.Serializable;
import java.util.LinkedList;

public class THashMapTrie implements IArbolTrie, Serializable {

    private TNodoTrieHashMap raiz;

    @Override
    public void insertar(String palabra) {
        if (palabra == null || palabra.isEmpty())
        {
            return;
        }
        String p = normalizar(palabra);
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertar(p);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (palabra == null || palabra.isEmpty())
        {
            return 0;
        }
        palabra = normalizar(palabra);
        if (raiz == null) {
            return 0;
        }
        return raiz.buscar(palabra);
    }

    public TNodoTrieHashMap buscarNodo(String palabra) {
        palabra = normalizar(palabra);
        if (raiz == null) {
            return null;
        }
        return raiz.buscarNodo(palabra);
    }
    @Override
    public LinkedList<String> predecir(String prefijo) {
        prefijo = normalizar(prefijo);
        if (raiz == null || prefijo == null) { // caso especial:prefijo vacio?
            return null;
        }
        LinkedList<String> palabras = new LinkedList<>();

        raiz.predecir(prefijo, palabras);

        return palabras;
    }

    protected static String normalizar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }

        palabra = palabra.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);

            switch (letra) {
                case 'á':
                    sb.append('a');
                    break;
                case 'é':
                    sb.append('e');
                    break;
                case 'í':
                    sb.append('i');
                    break;
                case 'ó':
                    sb.append('o');
                    break;
                case 'ú':
                    sb.append('u');
                    break;
                case 'ñ':
                    sb.append('n');
                    break;
                default:
                    if ((letra >= 'a' && letra <= 'z') || letra == ' ') {
                        sb.append(letra);
                    }
                    break;
            }
        }

        return sb.toString();
    }
}
