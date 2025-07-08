package com.mycompany.mavenproject1;

import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected TAristas lasAristas = new TAristas() ;
       /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
       super(vertices, aristas);     
      lasAristas.insertarAmbosSentidos(aristas);
       
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }
    public TAristas getLasAristas() {
        return lasAristas;
    }
 


    public TGrafoNoDirigido PrimPropio() {
        Set<TVertice> U = new HashSet<>();
        Set<TVertice> V = new HashSet<>(this.getVertices().values());
        Set<TArista> T = new HashSet<>();
        TVertice verticeInicial = getVertices().values().iterator().next();
        U.add(verticeInicial);
        while (!U.containsAll(V)) {
            TArista aristaMinima = null;

            for (TVertice u : U) {
                LinkedList<TAdyacencia> aristas = u.getAdyacentes();
                for (TAdyacencia ady : aristas) {
                    TVertice destino = ady.getDestino();

                    if (!U.contains(destino)) {
                        TArista posibleArista = new TArista(
                                u.getEtiqueta(), destino.getEtiqueta(), ady.getCosto());
                        if (aristaMinima == null || posibleArista.getCosto() < aristaMinima.getCosto()) {
                            aristaMinima = posibleArista;
                        }
                    }
                }
            }
            if (aristaMinima != null) {
                T.add(aristaMinima);
                U.add(getVertices().get(aristaMinima.getEtiquetaDestino()));
            } else {
                throw new IllegalArgumentException("El grafo no es conexo");
            }
        }
        return new TGrafoNoDirigido(U,T);
    }

    public TGrafoNoDirigido Prim() {
        Collection<Comparable> V = new LinkedList<>(getVertices().keySet());
        Collection<Comparable> U = new LinkedList<>();
        TAristas aristasAAM = new TAristas();
        double costoTotal = 0;

        Comparable primero = V.iterator().next();
        V.remove(primero);
        U.add(primero);

        while (!V.isEmpty()) {
            TArista aristaMin = aristasAAM.buscarMin(U, V);
            if (aristaMin == null) {
                throw new RuntimeException("Grafo no conexo");
            }
            aristasAAM.add(aristaMin);

            Comparable nuevoVertice = aristaMin.getEtiquetaDestino();
            if (V.contains(nuevoVertice)) {
                V.remove(nuevoVertice);
                U.add(nuevoVertice);
            } else {
                nuevoVertice = aristaMin.getEtiquetaOrigen();
                V.remove(nuevoVertice);
                U.add(nuevoVertice);
            }

            costoTotal += aristaMin.getCosto();
        }
        //construir grafo
    }
    @Override
    public TGrafoNoDirigido Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
