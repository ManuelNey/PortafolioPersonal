package org.example.tdas;

import java.util.*;

public class TGrafoDirigido<T> implements IGrafoDirigido {

    private Map<T, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    private int tiempo;

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }


    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     * <p>
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     * <p>
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     * <p>
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put((T) unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put((T) unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<T, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<T, TVertice> getVertices() {

        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Map<Comparable, Comparable> excentricidades = new HashMap<>();
        for (IVertice vertice : vertices.values()) {
            excentricidades.put(vertice.getEtiqueta(), obtenerExcentricidad((vertice.getEtiqueta())));
        }

        Comparable min = Double.MAX_VALUE;
        Comparable minEtiqueta = null;

        for (Comparable key : excentricidades.keySet()) {
            Comparable valor = excentricidades.get(key);

            if (valor instanceof Integer && ((Integer) valor) == -1) {
                valor = Double.MAX_VALUE;
            }

            if (valor instanceof Number && min instanceof Number) {
                Double valDouble = ((Number) valor).doubleValue();
                Double minDouble = ((Number) min).doubleValue();

                if (valDouble < minDouble) {
                    min = valor;
                    minEtiqueta = key;
                }
            }
        }

        return minEtiqueta;
    }

    @Override
    public Double[][] floyd() {
        Double[][] C = UtilGrafos.obtenerMatrizCostos(mapaCompatible());
        Double[][] A = new Double[C.length][C.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = C[i][j];
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i][i] = (double) 0;
        }
        for (int k = 0; k < A.length; k++) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                    }
                }
            }
        }
        return A;
    }

    //A es la vacia, y C es la matriz inicial
    public Double[][] floydConPredecesores() {
        Map<Comparable,TVertice> mapaCompatible= new HashMap<>();
        for(T key: vertices.keySet()) {
            mapaCompatible.put((String) key, vertices.get(key));
        }
        Double[][] C= UtilGrafos.obtenerMatrizCostos(mapaCompatible);
        Double[][] A = new Double[C.length][C.length];
        Double[][] P= new Double[C.length][C.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = C[i][j];
                P[i][j] = (double) -1;
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i][i] = (double) 0;
        }
        for (int k = 0; k < A.length; k++) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                        P[i][j] = (double) k;
                    }
                }
            }
        }
        return P;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        if (existeVertice(etiquetaVertice)) {
            int indice = -1;
            TVertice[] verticesList = getVertices().values().toArray(new TVertice[0]);
            for (int i = 0; i < vertices.size(); i++) {
                if (verticesList[i].getEtiqueta().compareTo(etiquetaVertice) == 0) {
                    indice = i;
                    break;
                }
            }
            Double maxDistancia = 0.0;
            if (indice >= 0) {

                Map<Comparable, TVertice> mapaCompatible = mapaCompatible();
                //Creo la matriz con los vaores iniciales
                Double[][] C = UtilGrafos.obtenerMatrizCostos(mapaCompatible);

                //Creo una matriz vacia para poner los valores de floyd
                Double[][] A = new Double[C.length][C.length];
                Double[][] matrizFloyd = floyd();

                //Asumo que todas las distancias son positivas
                for (int j = 0; j < C.length; j++) {
                    if (matrizFloyd[j][indice] > maxDistancia && indice != j) {
                        maxDistancia = matrizFloyd[j][indice];
                    }
                }
            }
            if (maxDistancia == Double.MAX_VALUE) {
                return -1;
            }
            return maxDistancia;
        }
        return null;
    }

    @Override
    public boolean[][] warshall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Map<Comparable, TVertice> mapaCompatible() {
        Map<Comparable, TVertice> mapaCompatible = new HashMap<>();
        for (T key : vertices.keySet()) {
            mapaCompatible.put((String) key, vertices.get(key));
        }
        return mapaCompatible;
    }

    public TCamino caminoMasCorto(Comparable origen, Comparable destino) {
        if (buscarVertice(origen) != null && buscarVertice(destino) != null) {
            Double[][] FloydPredecesores=floydConPredecesores();

            List<TVertice> indices= new ArrayList<>();
            for(TVertice vertice: vertices.values()){
                indices.add(vertice);
            }
            TVertice Vorigen=buscarVertice(origen);
            TVertice Vdestino= buscarVertice(destino);
            //Busco los indices de Origen y Destino
            int IndiceO=-1;
            int IndiceD=-1;
            for(int i=0; i< vertices.size();i++){
                if(indices.get(i).getEtiqueta().equals(origen)){
                    IndiceO=i;
                }
                if(indices.get(i).getEtiqueta().equals(destino)){
                    IndiceD=i;
                }
            }

            //Si En la posicion origen destino de la tabla de Floyd con Predecesores da -1 hay 2 posibilidades:
            //El camino mas corto es directo o no existe un camino de origen al destino. Si es directo la matriz normal
            //tendrá en esa posición de la tabla un número en lugar de un -1.

            //O no existe o es directo
            if(FloydPredecesores[IndiceO][IndiceD]==-1){
                Map<Comparable,TVertice> mapaCompatible= new HashMap<>();
                for(T key: vertices.keySet()){
                    mapaCompatible.put((String) key, vertices.get(key));
                }
                Double[][] matriz= UtilGrafos.obtenerMatrizCostos(mapaCompatible);
                //No existe un camino del Origen al Destino
                if(matriz[IndiceO][IndiceD]==-1){
                    System.out.println("No existe ningún camino desde "+origen+" a "+destino);
                    return null;
                }
                //El camino mas corto es directo
                else{
                    TCamino camino= new TCamino(Vorigen);
                    camino.agregarAdyacencia(Vorigen.buscarAdyacencia(Vdestino));
                    return camino;
                }

            }
            //Existe pero con vertices intermedios
            else{
                TCamino camino= new TCamino(Vorigen);
                obtenerCamino(IndiceO,IndiceD,FloydPredecesores,camino,indices);
                TVertice ultimoV= buscarVertice(camino.getLast());
                TAdyacencia ultimoAdyacente= ultimoV.buscarAdyacencia(destino);
                camino.agregarAdyacencia(ultimoAdyacente);
                return camino;
            }
        }
        System.out.println("No existe el vértice de origen o destino.");
        return null;
    }


    public void obtenerCamino(int i, int j, Double[][] mFloydPred, TCamino camino, List<TVertice> etiquetas) {

        //Si no hay camino directo
        if (mFloydPred[i][j]!=-1) {
            int k= (int) Math.round(mFloydPred[i][j]);
            obtenerCamino(i,k,mFloydPred,camino,etiquetas);
            TVertice verticeI= buscarVertice(etiquetas.get(i).getEtiqueta());
            TVertice verticeK= buscarVertice(etiquetas.get(k).getEtiqueta());
            if(verticeI.buscarAdyacencia(verticeK)!=null) {
                camino.agregarAdyacencia(verticeI.buscarAdyacencia(verticeK));
                obtenerCamino(k, j, mFloydPred, camino, etiquetas);
            }
        }
    }

    public void desvisitarVertices() {
        for (IVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public TCamino bpf(TVertice verticeOrigen) {
        desvisitarVertices();
        TVertice Vorigen=buscarVertice(verticeOrigen.getEtiqueta());
        TCamino camino = new TCamino(Vorigen);
        Vorigen.bpf(camino);
        for (IVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(camino);
            }
        }
        return camino;
    }


    public TCaminos todosLosCaminos(Comparable verticeO, Comparable verticeD) {
        TVertice verticeOrigen= buscarVertice(verticeO);
        TVertice verticeDestino= buscarVertice(verticeD);
        if(verticeOrigen!=null&& verticeDestino!=null) {
            desvisitarVertices();
            TCaminos caminos = new TCaminos();
            TCamino camino = new TCamino(verticeOrigen);
            verticeOrigen.setVisitado(true);
            verticeOrigen.todosLosCaminos(verticeDestino.getEtiqueta(), caminos, camino);
            return caminos;
        }
        return null;
    }

    public void clasificarArcos(
            TVertice verticeO,           TipoListaArcos arcosArbol,
            TipoListaArcos arcosRetroceso,
            TipoListaArcos arcosAvance,
            TipoListaArcos arcosCruzados) {
        TVertice verticeOrigen= buscarVertice(verticeO.getEtiqueta());
        tiempo = 0;
        desvisitarVertices();

        dfsClasificar(verticeOrigen, arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
    }

    private void dfsClasificar(
            TVertice v,
            TipoListaArcos arcosArbol,
            TipoListaArcos arcosRetroceso,
            TipoListaArcos arcosAvance,
            TipoListaArcos arcosCruzados
    ) {
        tiempo++;
        v.setTin(tiempo);
        v.setVisitado(true);

        for (Object adyacencia : v.getAdyacentes()) {
            TAdyacencia ady= (TAdyacencia) adyacencia;
            TVertice destino = ady.getDestino();
            TArista arco = new TArista(v.getEtiqueta(), destino.getEtiqueta(), 0);

            if (!destino.getVisitado()) {
                // Arco de árbol: exploramos recursivamente
                arcosArbol.insertar(arco);
                dfsClasificar(destino, arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
            } else {
                int tinOrigen = v.getTin();
                int tinDestino = destino.getTin();
                int toutDestino = destino.getTout();

                if (tinDestino < tinOrigen && toutDestino == 0) {
                    // Ancestro activo, arco retroceso
                    arcosRetroceso.insertar(arco);
                } else if (tinOrigen < tinDestino && toutDestino != 0) {
                    // Descendiente ya visitado, arco avance
                    arcosAvance.insertar(arco);
                } else if (toutDestino != 0 && tinDestino < tinOrigen) {
                    // Arco cruzado
                    arcosCruzados.insertar(arco);
                }
            }
        }

        tiempo++;
        v.setTout(tiempo);
    }

    public TCaminos obtenerTodosTopologicos() {
        TCaminos caminos = new TCaminos();
        Map<Comparable, Integer> grado = new HashMap<>();
        Map<Comparable, TVertice> mapaVertices = new HashMap<>();

        for (IVertice v : this.vertices.values()) {
            grado.put(v.getEtiqueta(), 0);
            mapaVertices.put(v.getEtiqueta(), (TVertice) v);
        }


        for (IVertice v : this.vertices.values()) {
            for (IAdyacencia ady : v.getAdyacentes()) {
                Comparable destino = ady.getDestino().getEtiqueta();
                grado.put(destino, grado.get(destino) + 1);
            }
        }

        List<Comparable> caminoActual = new ArrayList<>();
        generarOrdenesTopologicos(grado, mapaVertices, caminoActual, caminos);

        return caminos;
    }


    private void generarOrdenesTopologicos(
            Map<Comparable, Integer> grado,
            Map<Comparable, TVertice> mapaVertices,
            List<Comparable> caminoActual,
            TCaminos caminos
    ) {
        boolean agregado = false;

        for (Comparable etiqueta : grado.keySet()) {
            if (grado.get(etiqueta) == 0 && !caminoActual.contains(etiqueta)) {
                caminoActual.add(etiqueta);
                for (Object adyacente : mapaVertices.get(etiqueta).getAdyacentes()) {
                    TAdyacencia ady= (TAdyacencia) adyacente;
                    Comparable dest = ady.getDestino().getEtiqueta();
                    grado.put(dest, grado.get(dest) - 1);
                }

                generarOrdenesTopologicos(grado, mapaVertices, caminoActual, caminos);

                for (Object adyacente : mapaVertices.get(etiqueta).getAdyacentes()) {
                    TAdyacencia ady= (TAdyacencia) adyacente;
                    Comparable dest = ady.getDestino().getEtiqueta();
                    grado.put(dest, grado.get(dest) + 1);
                }
                caminoActual.remove(caminoActual.size() - 1);

                agregado = true;
            }
        }

        if (!agregado && caminoActual.size() == this.vertices.size()) {
            TVertice origen = mapaVertices.get(caminoActual.get(0));
            TCamino nuevoCamino = new TCamino(origen);
            for (int i = 1; i < caminoActual.size(); i++) {
                nuevoCamino.getOtrosVertices().add(caminoActual.get(i));
            }
            caminos.agregarCamino(nuevoCamino);
        }
    }





}
