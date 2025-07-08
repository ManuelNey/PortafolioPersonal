package org.example.TDA;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<IAdyacencia> adyacentes;
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

    public LinkedList<IAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta, T datos) {
        this.etiqueta = unaEtiqueta;
        this.datos = datos;
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
    public IAdyacencia buscarAdyacencia(IVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(IVertice verticeDestino) {
        IAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, IVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        IAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public IVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    public IVertice siguienteAdyacente(IVertice w) {
        IAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public IAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (IAdyacencia adyacencia : adyacentes) {
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

    public boolean tieneCiclos(TCamino elCamino) {
        this.setVisitado(true);
        for (IAdyacencia ady : adyacentes) {
            Comparable etiquetaDestino = ady.getDestino().getEtiqueta();
            if (elCamino.getEtiquetas().contains(etiquetaDestino)) {
                return true;
            } else if (!ady.getDestino().getVisitado()) {
                elCamino.agregarAdyacencia((TAdyacencia) ady); //Agrego la adyacencia
                if (ady.getDestino().tieneCiclos(elCamino)) { //Reviso si a partir de esta se genera un ciclo
                    return true;
                }
                elCamino.eliminarAdyacencia((TAdyacencia) ady); //La extraigo del camino
            }
        }
        this.setVisitado(false);
        return false;
    }

    public void bpf(Collection<IVertice> visitados) {
        this.visitado = true;
        visitados.add(this);

        for (IAdyacencia ady : this.adyacentes) {
            IVertice destino = ady.getDestino();
            if (!destino.getVisitado()) {
                destino.bpf(visitados);
            }
        }
        //Aca topocoso
    }

    public void todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        visitado = true;
        if (this.getEtiqueta().equals(etVertDest)) {
            todosLosCaminos.agregarCaminos(caminoPrevio.copiar());
        } else {
            for (IAdyacencia ady : this.adyacentes) {
                TVertice destino = (TVertice) ady.getDestino();
                if (!destino.getVisitado()) {
                    TCamino nuevoCamino = caminoPrevio.copiar();
                    nuevoCamino.agregarAdyacencia((TAdyacencia) ady);

                    destino.todosLosCaminos(etVertDest, nuevoCamino, todosLosCaminos);
                }
            }
        }
        visitado = false;
    }

    public TCamino cualquierCamino(Comparable etVertDest, TCamino caminoPrevio) {
        visitado = true;
        if (this.getEtiqueta().equals(etVertDest)) {
            return caminoPrevio;
        } else {
            for (IAdyacencia ady : this.adyacentes) {
                TVertice destino = (TVertice) ady.getDestino();
                if (!destino.getVisitado()) {
                    caminoPrevio.agregarAdyacencia((TAdyacencia) ady);
                    return destino.cualquierCamino(etVertDest, caminoPrevio.copiar());
                }
            }
        }
        visitado = false;
        return null;
    }
    public void ordenTautologico(Stack<TVertice> pila) {
        this.setVisitado(true);
        for (IAdyacencia ady : this.adyacentes) {
            TVertice destino = (TVertice) ady.getDestino();
            if (!destino.getVisitado()) {
                destino.ordenTautologico(pila);
            }
        }
        pila.push(this);
    }
}
