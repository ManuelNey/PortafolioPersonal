package org.example.TDA;

import org.example.utils.UtilGrafos;

import java.util.*;

public class TGrafoDirigido<T> implements IGrafoDirigido {

    private Map<Comparable, IVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<IVertice> vertices, Collection<IArista> aristas) {
        this.vertices = new HashMap<>();
        for (IVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta(), (T) vertice.getDatos());
        }
        for (IArista arista : aristas) {
            insertarArista(arista);
        }
    }

    private int tiempo;

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            IVertice vertOrigen = buscarVertice(nomVerticeOrigen);
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
        IVertice vertOrigen = buscarVertice(etiquetaOrigen);
        IVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
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
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private IVertice buscarVertice(Comparable unaEtiqueta) {
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
    public boolean insertarArista(IArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            IVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            IVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta, T dato) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            IVertice vert = new TVertice(unaEtiqueta, dato);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        if ((dato != null)) {

        }
        return false;
    }

    @Override
    public boolean insertarVertice(IVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, IVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, IVertice> getVertices() {
        return vertices;
    }

    public Comparable centroDelGrafo() {
        Double[][] floyd = floyd();
        Object[] etiquetas = vertices.keySet().toArray();
        Comparable centroDelGrafo = null;
        Double valorMinExcentricidad = Double.MAX_VALUE;

        for (int j = 0; j < vertices.size(); j++) { // ← columnas: destino
            Comparable verticeElegido = (Comparable) etiquetas[j];
            Double excentricidadVertice = null;

            for (int i = 0; i < vertices.size(); i++) { // ← filas: origen
                if (i != j && floyd[i][j] < Double.MAX_VALUE) {
                    if (excentricidadVertice == null || floyd[i][j] > excentricidadVertice) {
                        excentricidadVertice = floyd[i][j]; // ← buscamos el máximo en la columna
                    }
                }
            }

            if (excentricidadVertice == null) {
                excentricidadVertice = Double.MAX_VALUE;
            }

            if (excentricidadVertice < valorMinExcentricidad) {
                centroDelGrafo = verticeElegido;
                valorMinExcentricidad = excentricidadVertice;
            }
        }

        return centroDelGrafo;
    }


    public Double[][] floyd() {
        int i, j, k;
        int n = vertices.size();
        Double[][] matrizDelGrafo = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[][] matrizFloyd = new Double[n][n];

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                matrizFloyd[i][j] = matrizDelGrafo[i][j];
            }
        }
        for (i = 0; i < n; i++) {
            matrizFloyd[i][i] = 0.d;
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (matrizFloyd[i][k] + matrizFloyd[k][j] < matrizFloyd[i][j]) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                    }
                }
            }
        }
        return matrizFloyd;
    }

    public FloydConCamino floydConCaminos() { //Todavia falta pulirlo
        int i, j, k;
        int n = vertices.size();
        Object[] etiquetas =  vertices.keySet().toArray();

        Double[][] matrizDelGrafo = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[][] matrizFloyd = new Double[n][n];
        TCamino[][] matrizP = new TCamino[n][n];

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                matrizFloyd[i][j] = matrizDelGrafo[i][j];
                if (matrizDelGrafo[i][j] < Double.MAX_VALUE) {
                    TVertice origen = (TVertice) vertices.get(etiquetas[i]);
                    TVertice destino = (TVertice) vertices.get(etiquetas[j]);
                    matrizP[i][j] = new TCamino(origen);
                    matrizP[i][j].agregarAdyacencia(new TAdyacencia(0D, destino)); //No vamos a hacer que el camino contenga el costo del camino, para eso ya está la matriz de floyd que también se retorna
                } else {
                    matrizP[i][j] = null;
                }
            }
        }

        for (i = 0; i < n; i++) {
            matrizFloyd[i][i] = 0.d;
            matrizP[i][i] = null;
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (matrizFloyd[i][k] + matrizFloyd[k][j] < matrizFloyd[i][j]) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];

                        TCamino primerTramo = matrizP[i][k];
                        TCamino segundoTramo = matrizP[k][j];
                        TCamino caminoAConstruir = primerTramo.copiar();
                        List<Comparable> etiquetasSegundoTramo = segundoTramo.getEtiquetas();

                        for (int t = 1; t < etiquetasSegundoTramo.size(); t++) { // Desde 1 para evitar repetir el nodo intermedio k
                            Comparable etiq = etiquetasSegundoTramo.get(t);
                            TVertice vertice = (TVertice) vertices.get(etiq);
                            caminoAConstruir.agregarAdyacencia(new TAdyacencia(0d, vertice));
                        }

                        matrizP[i][j] = caminoAConstruir;
                    }
                }
            }
        }
        return new FloydConCamino(matrizFloyd, matrizP);
    }

    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        if (etiquetaVertice == null || !vertices.containsKey(etiquetaVertice)) {
            return null;
        }
        Object[] etiquetas =  vertices.keySet().toArray();
        Double[][] matrizFloyd = floyd();

        int indice = -1;
        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(etiquetaVertice)) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            return null;
        }

        double maxDistancia = 0;
        for (int j = 0; j < etiquetas.length; j++) {
            if (j != indice) {
                double distancia = matrizFloyd[j][indice];
                if (distancia == Double.MAX_VALUE) {
                    return Double.MAX_VALUE; //excentricidad de ese valor
                }
                if (maxDistancia < distancia) {
                    maxDistancia = distancia;
                }
            }
        }

        return maxDistancia;
    }

    public boolean[][] warshall() {
        int i, j, k;
        int n = vertices.size();
        Double[][] matrizDelGrafo = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] matrizWashall = new boolean[n][n];

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (matrizDelGrafo[i][j] != Double.MAX_VALUE) {
                    matrizWashall[i][j] = true;
                }
            }
        }
        for (i = 0; i < n; i++) {
            matrizWashall[i][i] = false;
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (matrizWashall[i][k] && matrizWashall[k][j]) {
                        matrizWashall[i][j] = true;
                    }
                }
            }
        }
        return matrizWashall;
    }

    public HashMap<Comparable, Double> Dijkstra(Comparable etiquetaOrigen) {
        if (etiquetaOrigen == null || vertices.get(etiquetaOrigen) == null) {
            return null;
        }
        Set<IVertice> visitados = new HashSet<>();
        HashMap<Comparable, Double> distancias = new HashMap<>();
        distancias.put(etiquetaOrigen, 0d);
        IVertice esteVertice = vertices.get(etiquetaOrigen);
        for (IVertice v : vertices.values()) {
            if (!v.getEtiqueta().equals(etiquetaOrigen)) {
                Double costo = esteVertice.obtenerCostoAdyacencia(v);
                distancias.put(v.getEtiqueta(), costo);
            }
        }
        //Se cargaron los datos de cada adyacencia al origen

        while (vertices.size() != visitados.size()) {
            Double costo = Double.MAX_VALUE;
            IVertice vertirceSelecionado = null;
            //Arrancamos diciendo que el mejor coste es que no haya camino (así posteriormente se actualiza en funcion de la mejor arista)
            for (IVertice v : vertices.values()) {
                if (!visitados.contains(v) && distancias.get(v.getEtiqueta()) < costo) {
                    vertirceSelecionado = v;
                    costo = distancias.get(v.getEtiqueta());
                }
                //Seleccionamos la mejor distancia posible
            }
            if (vertirceSelecionado == null) //Precaucción por si no se llega aseleccionar un vertice (aunque no debería de pasar)
            {
                break;
            }
            distancias.put(vertirceSelecionado.getEtiqueta(), costo);
            visitados.add(vertirceSelecionado);

            //Lo que se hace aqui es actualizar las distancias de los que pueden tener como vertice intermedio al nuevo agregado.
            for (IAdyacencia adyacente : vertirceSelecionado.getAdyacentes()) {
                Double costoDelAdyacente = adyacente.getCosto();
                IVertice destinoAdyacente = adyacente.getDestino();
                Double costoTotal = costo + costoDelAdyacente;
                if (costoTotal < distancias.get(destinoAdyacente.getEtiqueta())) {
                    distancias.put(destinoAdyacente.getEtiqueta(), costoTotal); //Se actualiza si se puede mejorar el camino pasando por w
                }
            }
        }
        return distancias;
    }

    public HashMap<Comparable, TCamino> DijkstraCaminos(Comparable etiquetaOrigen) {
        if (etiquetaOrigen == null || vertices.get(etiquetaOrigen) == null) {
            return null;
        }
        Set<IVertice> visitados = new HashSet<>();
        HashMap<Comparable, Double> distancias = new HashMap<>();
        HashMap<Comparable, TCamino> caminos = new HashMap<>();

        //Incio los datos de la distancia y camino del origen
        TVertice vertOrigen = (TVertice) vertices.get(etiquetaOrigen);
        distancias.put(etiquetaOrigen, 0d);
        caminos.put(etiquetaOrigen, null); //No hay camino hacia si mismo

        // Inicializo las distancias del resto (con caminos)
        for (IVertice v : vertices.values()) {
            Comparable etiquetaVertice = v.getEtiqueta();
            if (!etiquetaVertice.equals(etiquetaOrigen)) {
                //Tenemos que obtener la adyacencia entre el origen y el primer vertice seleccionado de djtrak porque no podemos acceder a la adcencia del origen con su arista más corta en el bucle principal.
                Double costo = vertOrigen.obtenerCostoAdyacencia(v);
                distancias.put(etiquetaVertice, costo);
                //Establecemos los caminos bases para poder aplicar dijktrac entre el origen y los demás vertices
                if (costo < Double.MAX_VALUE) {
                    // Creamos el camino directo del origen al vertice en si, si este existe (o sea si hay una arista entre ellos)
                    TCamino nuevoCamino = new TCamino(vertOrigen);
                    TAdyacencia ady = (TAdyacencia) vertOrigen.buscarAdyacencia(v);
                    if (ady != null) {
                        nuevoCamino.agregarAdyacencia(ady);
                        caminos.put(etiquetaVertice, nuevoCamino);
                    }
                } else {
                    caminos.put(etiquetaVertice, null); //Declaración que no hay camino directo del origen a ese vertice
                }
            }
        }

        //bucle principal
        while (visitados.size() < vertices.size()) {
            Double costo = Double.MAX_VALUE;
            IVertice vertirceSelecionado = null;
            //Arrancamos diciendo que el mejor coste es que no haya camino (así posteriormente se actualiza en funcion de la mejor arista)
            for (IVertice v : vertices.values()) {
                if (!visitados.contains(v) && distancias.get(v.getEtiqueta()) < costo) {
                    vertirceSelecionado = v;
                    costo = distancias.get(v.getEtiqueta());
                }
                //Seleccionamos la mejor distancia posible
            }
            if (vertirceSelecionado == null) //Precaucción por si no se llega aseleccionar un vertice, eso pasa cuando no puedo llegar a ellos desde el origen
            {
                break;
            }
            //Seleccionamos el mejor vertice según su distancia, ponemos su distancia en el mapa porque sabemos que ya no va a cambiar y lo ponemos en visitados
            distancias.put(vertirceSelecionado.getEtiqueta(), costo);
            visitados.add(vertirceSelecionado);
            //Lo que se hace aqui es actualizar las distancias de los que pueden tener como vertice intermedio al nuevo agregado.
            for (IAdyacencia ady : vertirceSelecionado.getAdyacentes()) {
                IVertice destino = ady.getDestino();
                Double costoActual = distancias.get(destino.getEtiqueta());
                Double nuevoCosto = distancias.get(vertirceSelecionado.getEtiqueta()) + ady.getCosto();

                if (nuevoCosto < costoActual) {
                    // Actualizar distancia
                    distancias.put(destino.getEtiqueta(), nuevoCosto);

                    // Copiar camino existente hacia verticeSeleccionado
                    TCamino caminoPrevio = caminos.get(vertirceSelecionado.getEtiqueta());
                    TCamino nuevoCamino;
                    if (caminoPrevio != null) {
                        nuevoCamino = caminoPrevio.copiar();
                    } else {
                        nuevoCamino = new TCamino((TVertice) vertirceSelecionado);
                    }
                    // Agregar nueva adyacencia al camino
                    nuevoCamino.agregarAdyacencia((TAdyacencia) ady);

                    // Guardar el nuevo camino completo en el capa de caminos
                    caminos.put(destino.getEtiqueta(), nuevoCamino);
                }
            }
        }
        return caminos;
    }

    public TCamino mejorCamino(Comparable origen, Comparable destino) {
        if (origen == null || destino == null) {
            return null;
        }
        return DijkstraCaminos(origen).get(destino);
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice == null) {
            return false;
        }
        IVertice verticeBuscado = vertices.get(nombreVertice);
        if (verticeBuscado == null) {
            return false;
        }
        for (IVertice v : vertices.values()) {
            v.eliminarAdyacencia(nombreVertice); //Elimino las adyacencia que tienen como destino el vertice a eliminar
        }
        vertices.remove(nombreVertice);
        return true;
    }

    @Override
    public void desvisitarVertices() {
        for (IVertice v : vertices.values()) {
            v.setVisitado(false);
        }
    }

    public Boolean tieneCiclo() {
        for (IVertice v : vertices.values()) {
            if (v.tieneCiclos(new TCamino((TVertice) v))) {
                return true;
            }
        }
        return false;
    }

    public Boolean tieneCiclo(Comparable etiquetaOrigen) {
        if (etiquetaOrigen == null || vertices.get(etiquetaOrigen) == null) {
            return null;
        }
        TVertice origen = (TVertice) vertices.get(etiquetaOrigen);
        if (vertices.get(etiquetaOrigen).tieneCiclos(new TCamino(origen))) {
            return true;
        }
        return false;
    }


    public TCamino darUnCaminoCualquiera(Comparable verticeOrigen, Comparable verticeDestino) {
        if (verticeOrigen == null || verticeDestino == null || vertices.get(verticeOrigen) == null || vertices.get(verticeDestino) == null) {
            return null;
        }
        desvisitarVertices();
        TVertice vertOrigen = (TVertice) vertices.get(verticeOrigen);
        return vertOrigen.cualquierCamino(verticeDestino, new TCamino(vertOrigen));
    }

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        if (etiquetaOrigen == null || etiquetaDestino == null || vertices.get(etiquetaDestino) == null || vertices.get(etiquetaDestino) == null) {
            return null;
        }
        desvisitarVertices();
        TVertice vertOrigen = (TVertice) vertices.get(etiquetaOrigen);
        TCaminos caminos = new TCaminos();
        vertOrigen.todosLosCaminos(etiquetaDestino, new TCamino(vertOrigen), caminos);
        return caminos;
    }


    public LinkedList<IVertice> bpf(IVertice verticeOrigen) {
        if (verticeOrigen == null) {
            return null;
        }
        LinkedList<IVertice> visitados = new LinkedList<>();

        desvisitarVertices();

        if (verticeOrigen != null) {
            verticeOrigen.bpf(visitados);
        }

        return visitados;
    }

    public LinkedList<IVertice> bpf() {
        LinkedList<IVertice> visitados = new LinkedList<>();

        desvisitarVertices();

        for (IVertice vert : vertices.values()) {
            if (!vert.getVisitado()) {
                vert.bpf(visitados);
            }
        }

        return visitados;
    }

    public LinkedList<IVertice> bpf(Comparable etiquetaOrigen) {
        if (etiquetaOrigen == null) {
            return null;
        }
        IVertice verticeOrigen = vertices.get(etiquetaOrigen);
        if (verticeOrigen == null) {
            return new LinkedList<>();
        }
        return bpf(verticeOrigen);
    }

    public List<TVertice> ordenTopologico() {
        desvisitarVertices();
        TGrafoDirigido grafoInvertido = invertirGrafo();

        Stack<TVertice> pila = new Stack<>();
        for (Object obj : grafoInvertido.vertices.values()) {
            TVertice v = (TVertice) obj;
            if (!v.getVisitado()) {
                TVertice vertice = (TVertice) v;
                vertice.ordenTautologico(pila);
            }
        }

        // Armamos la lista en orden topológico
        List<TVertice> resultado = new LinkedList<>();
        while (!pila.isEmpty()) {
            resultado.add(pila.pop());
        }

        return resultado;
    }

    private TGrafoDirigido invertirGrafo() {
        List<TVertice> nuevosVert = new ArrayList<>();

        for (IVertice vertice : this.vertices.values()) {
            TVertice vert = (TVertice) vertice;
            nuevosVert.add(new TVertice(vert.getEtiqueta(), vert.getDatos()));
        }

        List<TArista> aristasInvertidas = new ArrayList<>();

        for (IVertice vertice : this.vertices.values()) {
            TVertice vert = (TVertice) vertice;
            for (Object ady : vert.getAdyacentes()) {
                Comparable origen = ((TAdyacencia) ady).getDestino().getEtiqueta();
                Comparable destino = vert.getEtiqueta();
                double costo = ((TAdyacencia) ady).getCosto();

                aristasInvertidas.add(new TArista(origen, destino, costo));
            }
        }
        return new TGrafoDirigido(nuevosVert, aristasInvertidas);
    }

    public TCaminos obtenerTodosTopologicos() {
        if(!tieneCiclo()) {
            desvisitarVertices();
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
        return null;
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
            Double costoTot=0.0;
            for (int i = 1; i < caminoActual.size(); i++) {
                nuevoCamino.getOtrosVertices().add(caminoActual.get(i));
                TVertice anterior= (TVertice) buscarVertice(caminoActual.get(i-1));
                TVertice destino= (TVertice) buscarVertice(caminoActual.get(i));
                if(anterior.buscarAdyacencia(destino)!=null){
                    costoTot+= anterior.obtenerCostoAdyacencia(destino);
                }

            }
            nuevoCamino.setCostoTotal(costoTot);
            caminos.agregarCaminos(nuevoCamino);
        }
    }
    public TCamino obtenerCaminoCritico(Comparable origen, Comparable destino) {
        TVertice vOrigen = (TVertice) this.buscarVertice(origen);
        TCaminos caminos = new TCaminos();
        desvisitarVertices();
        TCamino caminoPrevio = new TCamino(vOrigen);
        vOrigen.todosLosCaminos(destino, caminoPrevio, caminos);

        TCamino caminoCritico = null;
        double maxDuracion = -1;

        for (TCamino c : caminos.getCaminos()) {
            if (c.getCostoTotal() > maxDuracion) {
                maxDuracion = c.getCostoTotal();
                caminoCritico = c;
            }
        }
        return caminoCritico;
    }

    public List<Holgura> obtenerHolguras(Comparable origen, Comparable destino) {
        TVertice vOrigen = (TVertice) this.buscarVertice(origen);
        TCaminos caminos = new TCaminos();
        desvisitarVertices();
        TCamino caminoPrevio = new TCamino(vOrigen);
        vOrigen.todosLosCaminos(destino, caminoPrevio, caminos);

        List<Holgura> resultado = new ArrayList<>();
        double maximaDuracion = 0;

        for (TCamino c : caminos.getCaminos()) {
            if (c.getCostoTotal() > maximaDuracion) {
                maximaDuracion = c.getCostoTotal();
            }
        }

        for (TCamino c : caminos.getCaminos()) {
            double holgura = maximaDuracion - c.getCostoTotal();
            resultado.add(new Holgura(c, c.getCostoTotal(), holgura));
        }

        return resultado;
    }

    public void clasificarArcos(
            TVertice verticeO,           TipoListaArcos arcosArbol,
            TipoListaArcos arcosRetroceso,
            TipoListaArcos arcosAvance,
            TipoListaArcos arcosCruzados) {
        TVertice verticeOrigen= (TVertice) buscarVertice(verticeO.getEtiqueta());
        tiempo = 0;
        desvisitarVertices();

        dfsClasificar(verticeOrigen, arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
    }

    private void dfsClasificar(TVertice v, TipoListaArcos arcosArbol, TipoListaArcos arcosRetroceso, TipoListaArcos arcosAvance, TipoListaArcos arcosCruzados) {
        tiempo++;
        v.setTin(tiempo);
        v.setVisitado(true);

        for (Object adyacencia : v.getAdyacentes()) {
            TAdyacencia ady= (TAdyacencia) adyacencia;
            TVertice destino = (TVertice) ady.getDestino();
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
}
