package org.example;

import tdas.Conjunto;
import tdas.IConjunto;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IConjunto<Integer> conjuntoPares = new Conjunto<>();
        IConjunto<Integer> conjuntoPrimos = new Conjunto<>();
        IConjunto<Integer> conjuntoTriangulares = new Conjunto<>();

        conjuntoPares.insertar(2, 2);
        conjuntoPares.insertar(4, 4);
        conjuntoPares.insertar(6, 6);
        conjuntoPares.insertar(8, 8);
        conjuntoPares.insertar(10, 10);

        conjuntoPrimos.insertar(2, 2);
        conjuntoPrimos.insertar(3, 3);
        conjuntoPrimos.insertar(5, 5);
        conjuntoPrimos.insertar(7, 7);
        conjuntoPrimos.insertar(11, 11);

        conjuntoTriangulares.insertar(1,1);
        conjuntoTriangulares.insertar(3,3);
        conjuntoTriangulares.insertar(6,6);
        conjuntoTriangulares.insertar(10,10);

        IConjunto<Integer> conjuntoInterseccionOrdenNCuadrado = conjuntoPares.interseccionOrdenNALa2(conjuntoPrimos);
        System.out.println(conjuntoInterseccionOrdenNCuadrado.imprimir());
        IConjunto<Integer> conjuntoInterseccion = conjuntoPares.interseccion(conjuntoPrimos);
        System.out.println(conjuntoInterseccion.imprimir());
        IConjunto<Integer> conjuntoUnion = conjuntoPares.unionOrdenNALa2(conjuntoPrimos);
        System.out.println(conjuntoUnion.imprimir());
        IConjunto<Integer> conjuntoUnionTyP = conjuntoPares.union(conjuntoPrimos);
        System.out.println(conjuntoUnionTyP.imprimir());
    }
}