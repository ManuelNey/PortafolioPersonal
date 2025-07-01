package org.example;
import principales.AlgoritmosYEstructura;
import principales.ManejadorArchivos;
import principales.Sucursal;

import java.io.IOException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
        AlgoritmosYEstructura algoritmosEstructura = new AlgoritmosYEstructura();
        String[] suc1 = manejadorArchivos.leerArchivo("src/main/resources/suc1.txt");
        for (String suc : suc1) {
            algoritmosEstructura.agregarSucursal(new Sucursal(suc));
        }
        String[] array = algoritmosEstructura.listarSucursales();
        System.out.println(algoritmosEstructura.cantidadSucursales());
         //Son 105 ciudades.Xian está repetida 3 veces solo cuenta la primera. son 107 ciudades y 105 diferentes. 1)b
        algoritmosEstructura.eliminarSucursal("Chicago");
        String[] arraySinChicago = algoritmosEstructura.listarSucursales();
        for (String suc : arraySinChicago) {
            System.out.println(suc);
        }
        /*
        La lista queda así:
        Dubai
        Nueva York
        Shanghai
        Hong Kong
        Shenzhen
        Tokio
        ...
        ... y sigue..
        Por lo tanto la respuesta sería Shenzhen. 2)c
         */
        AlgoritmosYEstructura algoritmosYEstructura2 = new AlgoritmosYEstructura();
        String[] lista = manejadorArchivos.leerArchivo("src/main/resources/suc2.txt");
        for (String suc : lista) {
            algoritmosYEstructura2.agregarSucursal(new Sucursal(suc));
        }
        algoritmosYEstructura2.eliminarSucursal("Shenzen");
        algoritmosYEstructura2.eliminarSucursal("Tokio");
        String[] sucursales = algoritmosYEstructura2.listarSucursales();
        if (sucursales != null) {
            for (String suc : sucursales) {
                System.out.println(suc);
            }
        }
        else{
            System.out.println("No hay sucursales");
            //Solo nos quedamos sin sucursales para mostrar. 3) d
        }
        AlgoritmosYEstructura algoritmosYEstructura3 = new AlgoritmosYEstructura();
        for (String suc : manejadorArchivos.leerArchivo("src/main/resources/suc3.txt")) {
            algoritmosYEstructura3.agregarSucursal(new Sucursal(suc));
        }
        System.out.println(algoritmosYEstructura3.imprimir(";_")); //La respuesta es la d
    }
}