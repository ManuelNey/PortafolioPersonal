package UT2_PD2;

public class InvertirArray {
    public InvertirArray() {
    }
    public int[] InvertirArrayConIndices(int[] arreglo, int indiceMin, int indiceMax) {
        if (arreglo == null) {
            return null;
        }
        if (indiceMin < 0 || indiceMax >= arreglo.length) {
            throw new IndexOutOfBoundsException("Se han ingresado indices no válidos");
        }
        if (indiceMin >= indiceMax) {
            return arreglo;
        }
        else{
            int aux = arreglo[indiceMax];
            arreglo[indiceMax] = arreglo[indiceMin];
            arreglo[indiceMin] = aux;
            return InvertirArrayConIndices(arreglo, indiceMin + 1, indiceMax -1);
        }

    }
}
