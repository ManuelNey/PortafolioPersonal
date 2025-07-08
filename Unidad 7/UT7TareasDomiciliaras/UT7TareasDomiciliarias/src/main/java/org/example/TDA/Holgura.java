package org.example.TDA;

public class Holgura {
    public TCamino camino;
    public double duracion;
    public double holgura;

    public Holgura(TCamino camino, double duracion, double holgura) {
        this.camino = camino;
        this.duracion = duracion;
        this.holgura = holgura; //Guarda la diferencia del camino critico con la del camino en si
    }
}