package PD1;
//Clase que utilicé para ver tiempos de ejecucion dde cada uno

import static PD1.TClasificador.*;

public class TiempoEjecucion {
    public static void probarYMedir(int[] vectorOriginal, int metodo, String nombreMetodo, String tipoVector,TClasificador clasificador) {
        int[] copia = vectorOriginal.clone();
        long inicio = System.nanoTime();
        int[] resultado = clasificador.clasificar(copia, metodo);
        long fin = System.nanoTime();
        long duracionMs = (fin - inicio) / 1000000; // en milisegundos

        System.out.println(nombreMetodo + " con vector " + tipoVector + " → Tiempo: " + duracionMs + " ms");
        System.out.println("Está ordenado?: " + clasificador.estaOrdenado(resultado));
    }

    public static void main(String[] args) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos(10000); // Tamaño ajustable


        int[] aleatorio = gdg.generarDatosAleatorios();
        int[] ascendente = gdg.generarDatosAscendentes();
        int[] descendente = gdg.generarDatosDescendentes();

        probarYMedir(aleatorio, METODO_CLASIFICACION_INSERCION, "Inserción", "aleatorio", clasif);
        probarYMedir(ascendente, METODO_CLASIFICACION_INSERCION, "Inserción", "ascendente", clasif);
        probarYMedir(descendente, METODO_CLASIFICACION_INSERCION, "Inserción", "descendente", clasif);

        System.out.println();
        // Burbuja
        probarYMedir(aleatorio, METODO_CLASIFICACION_BURBUJA, "Burbuja", "aleatorio", clasif);
        probarYMedir(ascendente, METODO_CLASIFICACION_BURBUJA, "Burbuja", "ascendente", clasif);
        probarYMedir(descendente, METODO_CLASIFICACION_BURBUJA, "Burbuja", "descendente", clasif);


        System.out.println();

        // Shellsort
        probarYMedir(aleatorio, METODO_CLASIFICACION_SHELL, "Shell", "aleatorio", clasif);
        probarYMedir(ascendente, METODO_CLASIFICACION_SHELL, "Shell", "ascendente", clasif);
        probarYMedir(descendente, METODO_CLASIFICACION_SHELL, "Shell", "descendente", clasif);


        System.out.println();


        // Quicksort
        probarYMedir(aleatorio, METODO_CLASIFICACION_QUICKSORT, "QuickSort", "aleatorio", clasif);
        probarYMedir(ascendente, METODO_CLASIFICACION_QUICKSORT, "QuickSort", "ascendente", clasif);
        probarYMedir(descendente, METODO_CLASIFICACION_QUICKSORT, "QuickSort", "descendente", clasif);
    }

}
