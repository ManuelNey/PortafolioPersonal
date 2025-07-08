
package org.example.TDA;

import java.util.LinkedList;
import java.util.List;


public class TCamino {
    public TVertice origen;

    public LinkedList<Comparable> otrosVertices;

    private Double costoTotal;

    public void imprimirEtiquetasPantalla() {
        System.out.print("Origen: " + origen.getEtiqueta());
        for (Comparable etiqueta : otrosVertices) {
            System.out.print(" -> " + etiqueta);
        }
        System.out.println();
    }

    public TCamino(TVertice v) {

        this.origen = v;
        this.otrosVertices = new LinkedList<>();
        this.costoTotal = 0.0;
    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + ((Number) adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
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
        TVertice origenCopiado = new TVertice(origen.getEtiqueta(), origen.getDatos());
        TCamino copia = new TCamino(origenCopiado);
        origenCopiado.getAdyacentes().addAll(origen.getAdyacentes());
        copia.otrosVertices.addAll(getOtrosVertices());
        copia.costoTotal = this.costoTotal;
        return copia;
    }

    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen: ").append(origen.getEtiqueta());
        for (Comparable etiqueta : otrosVertices) {
            sb.append(" -> ").append(etiqueta);
        }
        return sb.toString();
    }

    public List<Comparable> getEtiquetas() {
        Comparable origenEtiqueta = origen.getEtiqueta();
        LinkedList<Comparable> etiquetas = new LinkedList<>();
        etiquetas.add(origenEtiqueta);
        etiquetas.addAll(getOtrosVertices());
        return etiquetas;
    }

    public LinkedList<Comparable> getOtrosVertices() {
        return otrosVertices;
    }


    public TVertice getOrigen() {
        return origen;
    }
    public Double getCostoTotal(){
        return costoTotal;
    }
    public void setCostoTotal(Double costoTotalNuevo)
    {
        costoTotal = costoTotalNuevo;
    }
}
