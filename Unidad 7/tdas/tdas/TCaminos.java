package org.example.tdas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TCaminos {
    private LinkedList<TCamino> Caminos;

    public TCaminos() {
        Caminos = new LinkedList<>();
    } // implementar e inicializar la colección de caminos (vacía)


    public void imprimir() {
        for (TCamino camino : Caminos) {
            camino.imprimirEtiquetas();
        }
    }

    public List<String> imprimirEtiquetas() {
        List<String> lista = new ArrayList<>();

        for (TCamino camino : Caminos) {
            lista.add((String) camino.getOrigen().getEtiqueta() + ", " + camino.ImprimirEtiquetasConCosto());
        }
        return lista;
    }

    public void agregarCamino(TCamino camino) {
        Caminos.add(camino);
    }

    public LinkedList<TCamino> getCaminos() {
        return Caminos;
    }
}
