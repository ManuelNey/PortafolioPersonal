package tdas;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

public class Conjunto<T> extends ListaOrd<T> implements IConjunto<T> {
    //El hacer que conjunto este compuesto de datos de tipos T me permite hacer un uso genérico de estos
    public Conjunto() {
    }
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto) {
        if (otroConjunto == null) {
            return null;
        }
        Conjunto<T> conjunto = new Conjunto();
        T[] elementosA = this.toArray(); //Nos basamos en que la lista están ordenadas ya que usamos la clase de listas ordenada por inseccion
        T[] elementosB = otroConjunto.toArray();
        int i = 0; //Puntero del elemento altual de a
        int j = 0; //Puntero del elemento actual de b
        while (i < elementosA.length && j < elementosB.length) { //Mientras no me vaya de rango en ningun array
            Comparable claveA = this.darClavePorDato(elementosA[i]);
            Comparable claveB = otroConjunto.darClavePorDato(elementosB[j]);
            if (claveA.compareTo(claveB) == 0) { //Si los elementos son igual agrego al conjunto
                conjunto.insertar(elementosA[i], claveA);
                j++;
                i++;
            }
            else if ((claveA.compareTo(claveB)) > 0) { //Si A es mayor a B, significa que valor de B que puede ser igual a A debe estar adelante ya que estan ordenados
                j++;
            }
            else {
                i++; //B es mayor que A por lo tanto el posible igual de B en A está adelante en A.
            }
        }
        return conjunto;
    }
    public IConjunto<T> union(IConjunto<T> otroConjunto) {
        if (otroConjunto == null) {
            return null;
        }
        Conjunto<T> conjunto = new Conjunto();
        T[] elementosA = this.toArray(); //Nos basamos en que la lista están ordenadas ya que usamos la clase de listas ordenada por inseccion
        T[] elementosB = otroConjunto.toArray();
        int i = 0; //Puntero del elemento altual de a
        int j = 0; //Puntero del elemento actual de b
        while (i < elementosA.length && j < elementosB.length) { //Mientras no me vaya de rango en ningun array
            Comparable claveA = this.darClavePorDato(elementosA[i]);
            Comparable claveB = otroConjunto.darClavePorDato(elementosB[j]);
            if (claveA.compareTo(claveB) == 0) { //Si los elementos son igual agrego al conjunto
                conjunto.insertar(elementosA[i], claveA);
                j++;
                i++;
            } else if ((claveA.compareTo(claveB)) > 0) {
                conjunto.insertar(elementosB[j], claveB);
                j++;
            } else {
                conjunto.insertar(elementosA[i], claveA);
                i++;
            }
        }
        while (i < elementosA.length) {
            Comparable claveA = this.darClavePorDato(elementosA[i]);
            conjunto.insertar(elementosA[i], claveA);
            i++;
        }
        while (j < elementosB.length) {
            Comparable claveB = otroConjunto.darClavePorDato(elementosB[j]);
            conjunto.insertar(elementosB[j], claveB);
            j++;
        }
        return conjunto;
    }
    public IConjunto<T> interseccionOrdenNALa2(IConjunto<T> otroConjunto) {
        if (otroConjunto == null) {
            return null;
        }
        Conjunto<T> conjunto = new Conjunto<>();
        T[] elementosA = this.toArray(); // Esto ahora debería devolver un T[]
        T[] elementosB = otroConjunto.toArray();

        for (T elementoA : elementosA) {
            for (T elementoB : elementosB) {
                if (elementoA.equals(elementoB)) {
                    Comparable clave = this.darClavePorDato(elementoA);
                    conjunto.insertar(elementoA, clave);
                }
            }
        }
        return conjunto;
    }

    public IConjunto<T> unionOrdenNALa2(IConjunto<T> otroConjunto) {
        if (otroConjunto == null) {
            return null;
        }
        IConjunto<T> conjunto = new Conjunto();
        T[] elementosA = this.toArray();
        T[] elementosB = otroConjunto.toArray();
        for (int i = 0; i < elementosA.length; i++) {
            Comparable claveA = this.darClavePorDato(elementosA[i]);
            T datoA = conjunto.buscar(claveA);
            if (datoA == null) {
                conjunto.insertar(elementosA[i], claveA);
            }
        }
        for (int j = 0; j < elementosB.length; j++) {

            Comparable claveB = otroConjunto.darClavePorDato(elementosB[j]);
            T datoB = conjunto.buscar(claveB);
            if (datoB == null) {
                conjunto.insertar(elementosB[j], claveB);
            }
        }
        return conjunto;
    }
}
