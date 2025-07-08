package org.example.TDA;

public class FloydConCamino {
    private final Double[][] matrizFloyd; // Matriz de costos mínimos
    private final TCamino[][] matrizP; // Matriz de predecesores

    public FloydConCamino(Double[][] matrizFloyd, TCamino[][] matrizP) {
        this.matrizFloyd = matrizFloyd;
        this.matrizP = matrizP;
    }

    public Double[][] getMatrizFloyd() {
        return matrizFloyd;
    }

    public TCamino[][] getMatrizP() {
        return matrizP;
    }
}

