package TA3;

public class TClasificador {
    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;

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

    private int encuentraPivoteMaximo(int izquierda, int derecha, int[] entrada) {
        if (izquierda >= derecha) {
            return -1;
        }

        int max = izquierda;
        for (int i = izquierda + 1; i <= derecha; i++) {
            if (entrada[i] > entrada[max]) {
                max = i;
            }
        }

        if (entrada[max] == entrada[izquierda]) {
            return -1;
        } else {
            return max;
        }
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
	public static void main(String args[]) {
		TClasificador clasif = new TClasificador();

		GeneradorDatosGenericos gdgDos = new GeneradorDatosGenericos(300);
		GeneradorDatosGenericos gdgTres = new GeneradorDatosGenericos(1000);
		GeneradorDatosGenericos gdgCuatro = new GeneradorDatosGenericos(3000);

		// 300 elementos
		int[] vectorAleatorioDos = gdgDos.generarDatosAleatorios();
		int[] vectorAscendenteDos = gdgDos.generarDatosAscendentes();
		int[] vectorDescendenteDos = gdgDos.generarDatosDescendentes();

		// 10.000 elementos
		int[] vectorAscendenteTres = gdgTres.generarDatosAscendentes();
		int[] vectorDescendenteTres = gdgTres.generarDatosDescendentes();
		int[] vectorAleatorioTres = gdgTres.generarDatosAleatorios();

		// 30.000 elementos
		int[] vectorAleatorioCuatro = gdgCuatro.generarDatosAleatorios();
		int[] vectorAscendenteCuatro = gdgCuatro.generarDatosAscendentes();
		int[] vectorDescendenteCuatro = gdgCuatro.generarDatosDescendentes();

		// TA4 Ejercicio 1 Parte 1 Punto 4
		// Se puede cambiar la función pivote en el método ordenarPorQuicksort
		System.out.println();
		System.out.println("QUICKSORT DESCENDENTE CON 300: ");
		int[] resDescendenteDos = clasif.clasificar(vectorDescendenteDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resDescendenteDos.length; i++) {
			System.out.print(resDescendenteDos[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT ASCENDENTE CON 300: ");
		int[] resAscendenteDos = clasif.clasificar(vectorAscendenteDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAscendenteDos.length; i++) {
			System.out.print(resAscendenteDos[i] + " ");
		}
		System.out.println();
		System.out.println("QUICKSORT ALEATORIO CON 300: ");
		int[] resAleatorioDos = clasif.clasificar(vectorAleatorioDos, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAleatorioDos.length; i++) {
			System.out.print(resAleatorioDos[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT DESCENDENTE CON 10000: ");
		int[] resDescendenteTres = clasif.clasificar(vectorDescendenteTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resDescendenteTres.length; i++) {
			System.out.print(resDescendenteTres[i] + " ");
		}

		System.out.println();
		System.out.println("QUICKSORT ASCENDENTE CON 10000: ");
		int[] resAscendenteTres = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAscendenteTres.length; i++) {
			System.out.print(resAscendenteTres[i] + " ");
		}
		System.out.println();
		System.out.println("QUICKSORT ALEATORIO CON 10000: ");
		int[] resAleatorioTres = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_QUICKSORT);
		for (int i = 0; i < resAleatorioDos.length; i++) {
			System.out.print(resAleatorioTres[i] + " ");
		}

		// TA4 Ejercicio 2
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 ASCENDENTE:");
		int[] resInsercionAscendente300 = clasif.clasificar(vectorAscendenteDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente300.length; i++) {
			System.out.print(resInsercionAscendente300[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 DESCENDENTE:");
		int[] resInsercionDescendente300 = clasif.clasificar(vectorDescendenteDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente300.length; i++) {
			System.out.print(resInsercionDescendente300[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 300 ALEATORIO:");
		int[] resInsercionAleatorio300 = clasif.clasificar(vectorAleatorioDos, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio300.length; i++) {
			System.out.print(resInsercionAleatorio300[i] + " ");
		}

		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 ASCENDENTE:");
		int[] resInsercionAscendente1000 = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente1000.length; i++) {
			System.out.print(resInsercionAscendente1000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 DESCENDENTE:");
		int[] resInsercionDescendente1000 = clasif.clasificar(vectorDescendenteTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente1000.length; i++) {
			System.out.print(resInsercionDescendente1000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 1000 ALEATORIO:");
		int[] resInsercionAleatorio1000 = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio1000.length; i++) {
			System.out.print(resInsercionAleatorio1000[i] + " ");
		}

		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 ASCENDENTE:");
		int[] resInsercionAscendente3000 = clasif.clasificar(vectorAscendenteCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAscendente3000.length; i++) {
			System.out.print(resInsercionAscendente3000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 DESCENDENTE:");
		int[] resInsercionDescendente3000 = clasif.clasificar(vectorDescendenteCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionDescendente3000.length; i++) {
			System.out.print(resInsercionDescendente3000[i] + " ");
		}
		System.out.println();
		System.out.println("INSERCIÓN DIRECTA CON 3000 ALEATORIO:");
		int[] resInsercionAleatorio3000 = clasif.clasificar(vectorAleatorioCuatro, METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resInsercionAleatorio3000.length; i++) {
			System.out.print(resInsercionAleatorio3000[i] + " ");
		}

		System.out.println();
		System.out.println("SHELL ALEATORIO:");
		int[] resAleatorio = clasif.clasificar(vectorAleatorioCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resAleatorio.length; i++) {
			System.out.print(resAleatorio[i] + " ");
		}
		System.out.println();
		System.out.println("SHELL ASCENDENTE:");
		int[] resAscendente = clasif.clasificar(vectorAscendenteCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resAscendente.length; i++) {
			System.out.print(resAscendente[i] + " ");
		}
		System.out.println();
		System.out.println("SHELL DESCENDENTE:");
		int[] resDescendente =clasif.clasificar(vectorDescendenteCuatro, METODO_CLASIFICACION_SHELL);
		for (int i = 0; i < resDescendente.length; i++) {
			System.out.print(resDescendente[i] + " ");
		}

		//
		System.out.println();
		System.out.println("HEAPSORT ALEATORIO CON 300:");
		int [] resHeapsortAleatorio = clasif.clasificar(vectorAleatorioTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortAleatorio.length; i++) {
			System.out.print(resHeapsortAleatorio[i] + " ");
		}

		System.out.println();
		System.out.println("HEAPSORT ASCENDENTE CON 1000:");
		int[] resHeapsortAscendente = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortAscendente.length; i++) {
			System.out.print(resHeapsortAscendente[i] + " ");
		}

		System.out.println();
		System.out.println("HEAPSORT DESCENDENTE CON 3000:");
		int[] resHeapsortDescendente = clasif.clasificar(vectorAscendenteTres, METODO_CLASIFICACION_HEAPSORT);
		for (int i = 0; i < resHeapsortDescendente.length; i++) {
			System.out.print(resHeapsortDescendente[i] + " ");
		}
	}
}