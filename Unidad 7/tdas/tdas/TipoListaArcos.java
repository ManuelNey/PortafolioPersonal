package org.example.tdas;

import java.util.LinkedList;
import java.util.List;

public class TipoListaArcos {
    private List<TArista> lista;

    public TipoListaArcos() {
        this.lista = new LinkedList<>();
    }

    public void insertar(TArista arco) {
        lista.add(arco);
    }

    public List<TArista> getLista() {
        return lista;
    }

    public boolean esVacia() {
        return lista.isEmpty();
    }
}

