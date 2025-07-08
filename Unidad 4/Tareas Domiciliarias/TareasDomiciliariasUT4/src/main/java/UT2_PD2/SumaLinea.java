package UT2_PD2;

public class SumaLinea {
    public SumaLinea() {
    }
    public int SumaLinea(int[] arreglo, int cantElementos) {
        if (cantElementos < 0 || cantElementos > arreglo.length)
        {
            throw new IllegalArgumentException("La cantidad de elementos no puede ser negativo ni se pueden sumar más elementos de los disponibles");
        }
        if (arreglo.length == 0){ // verifico que el array no este vacio
            throw new IllegalArgumentException("El array debe tener un elemento al menos");
        }
        if (cantElementos == 1){
            return arreglo[0];
        }
        else
            return SumaLinea(arreglo,cantElementos - 1) + arreglo[cantElementos - 1];
    }
}
