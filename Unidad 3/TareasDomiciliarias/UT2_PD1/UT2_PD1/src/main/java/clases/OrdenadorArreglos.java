package clases;

public class OrdenadorArreglos {
    public int ordenar(int[] arreglo) {
        int N = arreglo.length;
        int contador = 0;
        for (int i = 0; i <= N - 1; i++) {
            for (int j = N - 1; j >= i + 1; j--) {
                contador++;
                if (arreglo[j] < arreglo[j - 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j - 1];
                    arreglo[j - 1] = temp;
                }
            }
        }
        return contador;
    }
}
