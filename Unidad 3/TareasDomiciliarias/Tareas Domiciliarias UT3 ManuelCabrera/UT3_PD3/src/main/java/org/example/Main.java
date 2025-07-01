package org.example;

import tdas.ILista;
import tdas.INodo;
import tdas.Lista;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ILista<String> lista = new Lista<>();

        lista.insertar("Primer dato", "Carlos");
        lista.insertar("Segundo dato", "Ana");
        lista.insertar("Tercer dato", "Bruno");

        System.out.println(lista.imprimir());

        System.out.println();

        System.out.println(lista.imprimir(" - "));

        System.out.println();

        INodo<String> buscado = lista.buscar("Segundo dato");
        if (buscado != null) {
            System.out.println("Encontré a: " + buscado.getDato());
        } else {
            System.out.println("No se encontró el elemento.");
        }

        boolean eliminado = lista.eliminar("Segundo dato");
        System.out.println();
        if (eliminado) {
            System.out.println("El elemento " + buscado.getDato() + " eliminado.");
        }
        else{
            System.out.println("El elemento no existe");
        }

        System.out.println("\nCantidad de elementos: " + lista.cantElementos());

        System.out.println();

        System.out.println("Vacía? " + lista.esVacia());
    }
}