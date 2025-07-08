package tdas;

public class THash<K, V> {
    private class NodeHash<K, V> {
        private V value;
        private K key;

        public NodeHash(K key, V value) {
            this.value = value;
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }

    private NodeHash<K, V>[] arrayNodos;
    private Float factorCarga;
    private int size;

    public THash(int tamanio, Float factorCarga) {
        if (  factorCarga == null ||factorCarga > 1 || factorCarga < 0 || tamanio < 0) {
            throw new IllegalArgumentException("Parámetros no válidos");
        }
        arrayNodos = (NodeHash<K, V>[]) new NodeHash[tamanio];
        this.factorCarga = factorCarga;
    }


    public V buscar(K unaClave) {
        int indice = funcionHashing(unaClave) % arrayNodos.length;
        int i = 0;
        while (i < arrayNodos.length) {
            if (arrayNodos[indice] == null) {
                return null;
            }
            if (arrayNodos[indice].getKey().equals(unaClave)) {
                return arrayNodos[indice].getValue();
            }
            i++;
            indice = (indice + 1) % arrayNodos.length;
        }
        return null;
    }

    public int buscarComparaciones(K unaClave) {
        int indice = funcionHashing(unaClave) % arrayNodos.length;
        int i = 0;
        while (i < arrayNodos.length) {
            if (arrayNodos[indice] == null) {
                return ++i;
            }
            if (arrayNodos[indice].getKey().equals(unaClave)) {
                return ++i;
            }
            i++;
            indice = (indice + 1) % arrayNodos.length;
        }
        return ++i;
    }

    public boolean insertar(K unaClave, V unValor) {
        if ((float) size / arrayNodos.length >= factorCarga) {
            redimensionar();
        }

        int hashing = funcionHashing(unaClave);
        int indice = hashing % arrayNodos.length;
        int i = 0;
        {
            while (i < arrayNodos.length) {
                if (arrayNodos[indice] == null) {
                    arrayNodos[indice] = new NodeHash(unaClave, unValor);
                    size++;
                    return true;
                }
                if (arrayNodos[indice].getKey().equals(unaClave)) {
                    return false;
                }
                i++;
                indice = (indice + 1) % arrayNodos.length;
            }
        }
        return false;
    }

    public int insertarComparaciones(K unaClave, V unValor) {
        if ((float) size / arrayNodos.length >= factorCarga) {
            redimensionar();
        }

        int hashing = funcionHashing(unaClave);
        int indice = hashing % arrayNodos.length;
        int i = 0;
        {
            while (i < arrayNodos.length) {
                if (arrayNodos[indice] == null) {
                    arrayNodos[indice] = new NodeHash(unaClave, unValor);
                    size++;
                    return ++i;
                }
                if (arrayNodos[indice].getKey().equals(unaClave)) {
                    return ++i;
                }
                i++;
                indice = (indice + 1) % arrayNodos.length;
            }
        }
        return ++i;
    }

    /**
     * Función de hashing que convierte una clave en un índice de la tabla hash.
     *
     * @param unaClave la clave a convertir en índice.
     * @return el índice correspondiente a la clave.
     */
    protected int funcionHashing(K unaClave) {
        // Implementar una función de hashing adecuada para las claves
        // Por ejemplo, se puede usar el método hashCode() de la clave y aplicar un módulo con el tamaño de la tabla
        int valor = unaClave.hashCode();
        if (valor < 0) {
            return -valor;
        }
        return valor;
    }

    private void redimensionar() {
        NodeHash<K, V>[] arrayViejo = arrayNodos;
        arrayNodos = (NodeHash<K, V>[]) new NodeHash[arrayViejo.length * 2];
        size = 0; // Hay que vaciar el array para volver a insertarnos y aumentar denuevo el tamaño
        for (NodeHash<K, V> nodo : arrayViejo) {
            if (nodo != null) {
                insertar(nodo.key, nodo.value);
            }
        }
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayNodos.length; i++) {
            if (arrayNodos[i] != null) {
                sb.append(i);
                sb.append(". clave: ");
                sb.append(arrayNodos[i].key.toString());
                sb.append("--> valor: ");
                sb.append(arrayNodos[i].value.toString() + "\n");
            }
        }
        return sb.toString();
    }
}
