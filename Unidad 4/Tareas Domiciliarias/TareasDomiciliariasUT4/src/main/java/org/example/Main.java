package org.example;

import tdas.ElementoAB;
import tdas.ArbolBB;

public class Main {
    public static void main(String[] args) {
        ElementoAB CINCO = new ElementoAB(5, 5);
        ArbolBB arbolito = new ArbolBB(CINCO);
        arbolito.insertar(2, 2);
        arbolito.insertar(8, 8);
        arbolito.insertar(1, 1);
        arbolito.insertar(3, 3);
        arbolito.insertar(7, 7);
        arbolito.insertar(9, 9);
        arbolito.insertar(6, 6);
        arbolito.insertar(4, 4);
        arbolito.insertar(10, 10);
        System.out.println(arbolito.obtenerCantidadNivel(4));
    }
}
