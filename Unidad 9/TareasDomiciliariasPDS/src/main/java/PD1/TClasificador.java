package PD1;

public class TClasificador {
    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_COUNTING = 6;



    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
     */
    public boolean estaOrdenado(int[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            if (datos[i] > datos[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapAscendente(datosParaClasificar, datosParaClasificar.length);
            case METODO_CLASIFICACION_COUNTING:
                return ordenarPorCountingSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        // TODO:Implementar encuentraPivote a criterio de cada equipo
        int posicionPivote = encuentraPivoteRandom(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];//int pivote = posicionPivote;
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;//izquierda--;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha--;//derecha++;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha)
                quicksort(entrada, i, derecha);// quicksort(entrada, i, izquierda);
            if (izquierda < j)
                quicksort(entrada, izquierda, j); //quicksort(entrada, derecha, j);
        }
    }

    private int encuentraPivoteMayorDiferentEntre2(int izquierda, int derecha, int[] entrada) {
        if (izquierda >= derecha)
        {
            return -1;
        }
        int pocisionSiguiente = izquierda + 1;
        int valorIzq = entrada[izquierda];
        Integer valorDifSiguiente = entrada[pocisionSiguiente];
        while ((valorIzq == valorDifSiguiente) && pocisionSiguiente < derecha)
        {
            ++pocisionSiguiente;
            valorDifSiguiente = entrada[pocisionSiguiente];
        }
        if (valorIzq == valorDifSiguiente)
        {
            return -1;
        }
        else if (valorDifSiguiente > valorIzq)
        {
            return pocisionSiguiente;
        }
        return izquierda;
    }

    private int encuentraPivoteMedio(int izquierda, int derecha, int[] entrada) {
        if (izquierda >= derecha) {
            return -1;
        }

        int posPivote = (izquierda + derecha) / 2;
        int valorIzq = entrada[izquierda];
        int valorMedio = entrada[posPivote];

        if (valorIzq == valorMedio) {
            int i = izquierda + 1;
            while (i <= derecha && entrada[i] == valorIzq) {
                i++;
            }
            if (i > derecha) {
                return -1;
            } else {
                if (entrada[i] > valorIzq) {
                    return i;
                } else {
                    return izquierda;
                }
            }
        } else {
            if (valorMedio > valorIzq) {
                return posPivote;
            } else {
                return izquierda;
            }
        }
    }

    private int encuentraPivoteRandom(int izquierda, int derecha, int[] entrada) {
        if (izquierda >= derecha) {
            return -1;
        }

        int valorIzq = entrada[izquierda];
        int intentos = 0;
        while (intentos < 10) {
            int posPivote = (int) (Math.random() * (derecha - izquierda + 1)) + izquierda;
            int valorPivote = entrada[posPivote];

            if (valorPivote != valorIzq) {
                if (valorPivote > valorIzq) {
                    return posPivote;
                } else {
                    return izquierda;
                }
            }
            intentos++;
        }
        return -1;
    }


    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

        for (int posIncrementoActual = 1; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                            j = j - inc; //j = j--
                        }
                        //Nuevo
                        else{
                            break;
                        }
                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) { //for (int i = 2; i < datosParaClasificar.length; i++)
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) { //while ((j >= 0) && (datosParaClasificar[j + 1] > datosParaClasificar[j]))
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        //datosParaClasificar = null;
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    private int[] ordenarPorHeapAscendente(int[] heap, int n) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            desplazaElementoAscendente(heap, i, n);
        }

        for (int i = n -1; i > 0; i--) {
            intercambiar(heap, 0, i);
            desplazaElementoAscendente(heap, 0, i);
        }
        return heap;
    }


    private void desplazaElementoAscendente(int[] heap, int primero, int ultimo) {
        int actual = primero;
        while (2 * actual + 1 < ultimo) {
            Integer hijoIzq =( 2 * (actual)) + 1;
            Integer hijoDer = hijoIzq + 1;
            Integer mayor;
            if (hijoDer >= ultimo) {
                mayor = hijoIzq;
            } else {
                if (heap[hijoIzq] < heap[hijoDer]) {
                    mayor = hijoDer;
                } else {
                    mayor = hijoIzq;
                }
            }
            if (heap[actual] < heap[mayor])
            {
                intercambiar(heap, actual, mayor);
                actual = mayor;
            } else {
                break;
            }
        }
    }

    private int[] ordenarPorHeapDecendente(int[] heap, int n) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            desplazaElementoDecendente(heap, i, n);
        }

        for (int i = n -1; i > 0; i--) {
            intercambiar(heap, 0, i);
            desplazaElementoDecendente(heap, 0, i);
        }
        return heap;
    }
    private void desplazaElementoDecendente(int[] heap, int primero, int ultimo) {
        int actual = primero;
        while (2 * actual + 1 < ultimo) {
            Integer hijoIzq =( 2 * (actual)) + 1;
            Integer hijoDer = hijoIzq + 1;
            Integer menor;
            if (hijoDer >= ultimo) {
                menor = hijoIzq;
            } else {
                if (heap[hijoIzq] < heap[hijoDer]) {
                    menor = hijoIzq;
                } else {
                    menor = hijoDer;
                }
            }
            if (heap[actual] > heap[menor])
            {
                intercambiar(heap, actual, menor);
                actual = menor;
            } else {
                break;
            }
        }
    }

    private int[] ordenarPorCountingSort(int[] datos) {
        if (datos == null || datos.length == 0) return datos;

        // Hay que buscar el elemento más grande para ser el tamaño de nuestro arreglo
        int max = datos[0];
        for (int i = 1; i < datos.length; i++) {
            if (datos[i] > max) {
                max = datos[i];
            }
        }

        int[] conteo = new int[max + 1];
        int[] salida = new int[datos.length];

        // cuanto cuantas veces aparece cada numero
        for (int i = 0; i < datos.length; i++) {
            conteo[datos[i]]++;
        }

        // sumo de izq a derecha lo del anteriro,
        for (int i = 1; i < conteo.length; i++) {
            conteo[i] += conteo[i - 1];
        }

        // ultimo paso donde se hace el verdadero counting sort contando cada aparicion con el lugar de donde deberia ir cada numero
        for (int i = datos.length - 1; i >= 0; i--) {
            int valor = datos[i];
            salida[conteo[valor] - 1] = valor;
            conteo[valor]--;
        }

        return salida;
    }


    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos(100);
        int[] vectorAleatorio = gdg.generarDatosAleatorios();
        int[] vectorAscendente = gdg.generarDatosAscendentes();
        int[] vectorDescendente = gdg.generarDatosDescendentes();

        //Insercion
        int[] resAleatorioInser = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resAleatorioInser.length; i++) {
            System.out.print(resAleatorioInser[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAleatorioInser));
        int[] resAscendenteInser = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resAscendenteInser.length; i++) {
            System.out.print(resAscendenteInser[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAscendenteInser));
        int[] resDescendenteInser = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resDescendenteInser.length; i++) {
            System.out.print(resDescendenteInser[i] + " ");
        }
        System.out.println(clasif.estaOrdenado(resDescendenteInser));


        //Burbuja
        int[] resAleatorioBurb = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resAleatorioBurb.length; i++) {
            System.out.print(resAleatorioBurb[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAleatorioBurb));
        int[] resAscendenteBurb = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resAscendenteBurb.length; i++) {
            System.out.print(resAscendenteBurb[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAscendenteBurb));
        int[] resDescendenteBurb = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resDescendenteBurb.length; i++) {
            System.out.print(resDescendenteBurb[i] + " ");
        }
        System.out.println(clasif.estaOrdenado(resDescendenteBurb));

        //Shell
        int[] resAleatorioShell = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resAleatorioShell.length; i++) {
            System.out.print(resAleatorioShell[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAleatorioShell));
        int[] resAscendenteShell = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resAscendenteShell.length; i++) {
            System.out.print(resAscendenteShell[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAscendenteShell));
        int[] resDescendenteShell = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resDescendenteShell.length; i++) {
            System.out.print(resDescendenteShell[i] + " ");
        }
        System.out.println(clasif.estaOrdenado(resDescendenteShell));


        int[] resAleatorioQuick = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resAleatorioQuick.length; i++) {
            System.out.print(resAleatorioQuick[i] + " ");
        }


        System.out.println(clasif.estaOrdenado(resAleatorioQuick));
        int[] resAscendenteQuick = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resAscendenteQuick.length; i++) {
            System.out.print(resAscendenteQuick[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAscendenteQuick));
        int[] resDescendenteQuick = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_QUICKSORT);
        for (int i = 0; i < resDescendenteQuick.length; i++) {
            System.out.print(resDescendenteQuick[i] + " ");
        }
        System.out.println(clasif.estaOrdenado(resDescendenteQuick));

        int[] resAleatorioHeap = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = 0; i < resAleatorioHeap.length; i++) {
            System.out.print(resAleatorioHeap[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAleatorioHeap));
        int[] resAscendenteHeap = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = 0; i < resAscendenteHeap.length; i++) {
            System.out.print(resAscendenteHeap[i] + " ");
        }

        System.out.println(clasif.estaOrdenado(resAscendenteHeap));
        int[] resDescendenteHeap = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_HEAPSORT);
        for (int i = 0; i < resDescendenteHeap.length; i++) {
            System.out.print(resDescendenteHeap[i] + " ");
        }
        System.out.println(clasif.estaOrdenado(resDescendenteHeap));
    }
}
