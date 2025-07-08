package PD7;

import utils.ManejadorArchivosGenerico;

import java.util.LinkedList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

       // CARGAR EN EL TRIE LOS TELÉFONOS Y NOMBRES A PARTIR DEL ARCHIVO ABONADOS.TXT
        ManejadorArchivosGenerico manejador = new ManejadorArchivosGenerico();
        String[] lineas = manejador.leerArchivo("src/main/java/ImplementacionPD7/abonados.txt");

        for (String linea: lineas)
        {
            String[] parametrosLinea = linea.split(",");
            String numero = parametrosLinea[0];
            String nombre = parametrosLinea[1];
            System.out.println("Insertando a " + nombre + " Con el numero: " + nombre);
            trieAbonados.insertar(new TAbonado(nombre, numero));
        }
        String codigoPais = "598" ; // utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "93" ;// utilizar el indicado en el archivo "codigos.txt"
        LinkedList<TAbonado> ab = trieAbonados.buscarTelefonos(codigoPais, codigoArea);
        String[] lineasParaEscritura = new String[ab.size()];
        int indice = 0;
        for (TAbonado abonado : ab)
        {
            lineasParaEscritura[indice] = abonado.getNombre() + ", " + abonado.getTelefono();
            System.out.println(abonado.getNombre() + abonado.getTelefono());
            indice++;
        }
        manejador.escribirArchivo("src/main/java/ImplementacionPD7/abonadosFiltrados.txt", lineasParaEscritura);
        // crear el archivo "salida.txt", con los abonados (1 por linea) 
        // correspondientes al pais y area 
        // imprimir Nombre y teléfono, 
        // ordenados alfabeticamente por nombre
        
      
        
    }
}