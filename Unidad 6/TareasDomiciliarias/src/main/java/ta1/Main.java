/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta1;

import tdas.THash;
import utils.ManejadorArchivosGenerico;

/**
 *
 * @author jechague
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        THash<String, String> tablaHash = new THash<>(100, 0.90f);
        String[] clavesInsertar = ManejadorArchivosGenerico.leerArchivo("./src/main/java/uy/edu/ucu/aed/tas/ta1/claves_insertar.txt");
        int totalComparacionesInsercion = 0;
        for (String clave : clavesInsertar) {
            totalComparacionesInsercion+= tablaHash.insertarComparaciones(clave, clave);
            System.out.println("Se ha insertado la clave: "+clave);
        }
        System.out.println(tablaHash.imprimir());

        String[] clavesBuscar = ManejadorArchivosGenerico.leerArchivo("src/main/java/uy/edu/ucu/aed/tas/ta1/claves_buscar.txt");

        int totalComparacionesExitosas = 0;
        int totalComparacionesNoExitosas = 0;
        int exitosas = 0;
        int noExitosas = 0;

        for (String clave : clavesBuscar) {
            int comparaciones = tablaHash.buscarComparaciones(clave);
            Object valor = tablaHash.buscar(clave);
            if (valor != null) {
                totalComparacionesExitosas += comparaciones;
                exitosas++;
                System.out.println("Clave encontrada: " + clave);
            } else {
                totalComparacionesNoExitosas += comparaciones;
                noExitosas++;
                System.out.println("Clave no encontrada: " + clave);
            }
        }

        float promedioPorInsercion = (float) totalComparacionesInsercion / clavesInsertar.length;
        float promedioBusquedasExitosas = (float) totalComparacionesExitosas / exitosas;
        float promedioBusquedasNoExitosas =  (float) totalComparacionesNoExitosas / noExitosas;

        System.out.println("Promedio comparaciones inserción: " + promedioPorInsercion);
        System.out.println("Promedio comparaciones exitosas: " + promedioBusquedasExitosas);
        System.out.println("Promedio comparaciones no exitosas: " + promedioBusquedasNoExitosas);


    }

}
