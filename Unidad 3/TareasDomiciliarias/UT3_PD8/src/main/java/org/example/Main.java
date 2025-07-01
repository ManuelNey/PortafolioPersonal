package org.example;

import principales.AlgoritmosYEstructura;
import principales.ManejadorArchivos;
import principales.Sucursal;

public class Main {
    public static void main(String[] args) {
        ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
        AlgoritmosYEstructura algoritmosEstructura = new AlgoritmosYEstructura();

        String[] suc1 = manejadorArchivos.leerArchivo("src/main/java/Archivos/suc1.txt");
        for (String suc : suc1) {
            algoritmosEstructura.agregarSucursal(new Sucursal(suc));
        }

        String[] array = algoritmosEstructura.listarSucursales();
        System.out.println("Cantidad de sucursales: " + algoritmosEstructura.cantidadSucursales());

        algoritmosEstructura.eliminarSucursal("Chicago");
        System.out.println("Listado sin Chicago:");
        String[] arraySinChicago = algoritmosEstructura.listarSucursales();
        for (String suc : arraySinChicago) {
            System.out.println(suc);
        }

        AlgoritmosYEstructura algoritmosYEstructura2 = new AlgoritmosYEstructura();
        String[] lista = manejadorArchivos.leerArchivo("src/main/java/Archivos/suc1.txt");
        for (String suc : lista) {
            algoritmosYEstructura2.agregarSucursal(new Sucursal(suc));
        }

        algoritmosYEstructura2.eliminarSucursal("Shenzen");
        algoritmosYEstructura2.eliminarSucursal("Tokio");

        System.out.println("Sucursales luego de eliminar Shenzen y Tokio:");
        String[] sucursales = algoritmosYEstructura2.listarSucursales();
        if (sucursales != null && sucursales.length > 0) {
            for (String suc : sucursales) {
                System.out.println(suc);
            }
        } else {
            System.out.println("No hay sucursales.");
        }

        AlgoritmosYEstructura algoritmosYEstructura3 = new AlgoritmosYEstructura();
        for (String suc : manejadorArchivos.leerArchivo("src/main/java/Archivos/suc3.txt")) {
            algoritmosYEstructura3.agregarSucursal(new Sucursal(suc));
        }

        System.out.println(algoritmosYEstructura3.imprimir(";_"));
    }
}
