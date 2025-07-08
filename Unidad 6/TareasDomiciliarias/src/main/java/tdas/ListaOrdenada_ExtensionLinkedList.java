package tdas;

import java.util.LinkedList;

public class ListaOrdenada_ExtensionLinkedList<T extends Comparable<T>> extends LinkedList<T> {
    public boolean addSorted(T dato) {
        int indice = 0;
        while (indice < this.size() && dato.compareTo(this.get(indice)) > 0) {
            indice++;
        }
        super.add(indice, dato);
        return true;
    }
}