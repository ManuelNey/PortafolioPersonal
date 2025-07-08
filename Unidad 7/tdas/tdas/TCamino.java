package org.example.tdas;

import java.util.ArrayList;
import java.util.Collection;

public class TCamino {

    private TVertice origen;
    private ArrayList<Comparable> otrosVertices;
    private Double costoTotal;


    public String ImprimirEtiquetasConCosto() {
        String etiquetas = "";
        for (Comparable etiqueta : otrosVertices) {
            etiquetas += etiqueta + ", ";
            System.out.println(etiqueta);
        }
        return etiquetas.substring(0, etiquetas.length() - 2) + ". Costo: " + costoTotal;
    }

    public void imprimirEtiquetas() {
        for (Comparable etiqueta : otrosVertices) {
            System.out.println(etiqueta);
        }
    }

    public TCamino(TVertice v) {
        this.origen = v;
        this.otrosVertices = new ArrayList<>();
        this.costoTotal = 0.0;
    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if(adyacenciaActual!=null) {
            if (adyacenciaActual.getDestino() != null) {
                costoTotal = costoTotal + ((Number) adyacenciaActual.getCosto()).doubleValue();
                return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
            }
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal = costoTotal - ((Number) adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta()));
        }
        return false;
    }

    public TCamino copiar() {
        TVertice origen = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origen);
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        copia.setCostoTotal(this.getCostoTotal());

        return copia;
    }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public IVertice getOrigen() {
        return origen;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    private void setCostoTotal(Double costo) {
        this.costoTotal = costo;
    }

    public void setOrigen(IVertice origen){
        this.origen=(TVertice) origen;
    }

    public Comparable eliminarUltimo(){
        return otrosVertices.removeLast();
    }

    public Comparable getLast(){
        return otrosVertices.getLast();
    }

}


