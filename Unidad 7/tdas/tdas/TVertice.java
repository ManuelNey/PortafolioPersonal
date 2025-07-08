package org.example.tdas;

import java.util.LinkedList;
import java.util.List;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int tin;
    private int tout;

    public void setTin(int tin) {
        this.tin = tin;
    }

    public int getTin() {
        return this.tin;
    }

    public void setTout(int tout) {
        this.tout = tout;
    }

    public int getTout() {
        return this.tout;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public T getDatos() {
        return datos;
    }

    public TCamino bpf(TCamino camino) {
        if (!this.getVisitado()) {
            this.setVisitado(true);
            camino.getOtrosVertices().add(this.etiqueta);
            for (IAdyacencia adyacente : adyacentes) {
                IVertice vertice = adyacente.getDestino();
                if (!vertice.getVisitado()) {
                    vertice.bpf(camino);
                }
            }
        }
        return camino;
    }

    public void todosLosCaminos(Comparable destino, TCaminos caminos, TCamino actual) {
        if (this.etiqueta.compareTo(destino) == 0) {
            caminos.agregarCamino(actual.copiar());
            return;
        }

        for (IAdyacencia ady : adyacentes) {
            TAdyacencia adyacente = (TAdyacencia) ady;
            TVertice verticeDestino = (TVertice) adyacente.getDestino();

            if (!verticeDestino.getVisitado()) {
                verticeDestino.setVisitado(true);
                actual.agregarAdyacencia(adyacente);

                verticeDestino.todosLosCaminos(destino, caminos, actual);

                actual.eliminarAdyacencia(adyacente);
                verticeDestino.setVisitado(false);
            }
        }
    }





}
