package org.example;

import clases.OrdenadorArreglos;
import utils.ManejadorArchivosGenerico;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ManejadorArchivosGenerico manejadorArchivosGenerico = new ManejadorArchivosGenerico();
        String[] lineas = manejadorArchivosGenerico.leerArchivo("src/main/java/utils/numeros.txt");
        int length = Integer.parseInt(lineas[0]);
        int[] array = new int[length];
        for (int i = 1; i <= length; i++) {
            array[i-1] = Integer.parseInt(lineas[i]);
        }
        OrdenadorArreglos ordenadorArreglos = new OrdenadorArreglos();
        int contador = ordenadorArreglos.ordenar(array);
        System.out.println("Arreglo de tamaño: " + array.length);
        System.out.println("Cantidad de lineas if ejecutadas: "+ contador);
        System.out.println("Primer numero: "+ array[0]);
        System.out.println("Último numero: "+ array[array.length - 1]);
    }
}