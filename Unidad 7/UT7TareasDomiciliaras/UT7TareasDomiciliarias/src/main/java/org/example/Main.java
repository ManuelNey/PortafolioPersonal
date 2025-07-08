package org.example;


import org.example.TDA.*;
import org.example.utils.ManejadorArchivosGenerico;
import org.example.utils.UtilGrafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //PD2
        TVertice artigas= new TVertice<>("Artigas","");
        TVertice canelones= new TVertice<>("Canelones","");
        TVertice durazno= new TVertice<>("Durazno","");
        TVertice florida= new TVertice<>("Florida","");
        TVertice montevideo= new TVertice<>("Montevideo","");
        TVertice puntaDE=new TVertice<>("Punta del Este","");
        TVertice rocha= new TVertice<>("Rocha","");

        List<TVertice> vertices= new ArrayList<>();
        vertices.add(artigas);
        vertices.add(canelones);
        vertices.add(durazno);
        vertices.add(florida);
        vertices.add(montevideo);
        vertices.add(puntaDE);
        vertices.add(rocha);

        TArista AR= new TArista("Artigas","Rocha",400);
        TArista CAA= new TArista("Canelones","Artigas",500);
        TArista CACO= new TArista("Canelones","Colonia",200);
        TArista CAD= new TArista("Canelones","Durazno",170);
        TArista CAP= new TArista("Canelones","Punta del Este",90);
        TArista COM= new TArista("Colonia","Montevideo",180);
        TArista FD=  new TArista("Florida","Durazno",60);
        TArista MA=  new TArista("Montevideo","Artigas",700);
        TArista MCA=  new TArista("Montevideo","Canelones",30);
        TArista MCP=  new TArista("Montevideo","Punta del Este",130);
        TArista PR=  new TArista("Punta del Este","Rocha",90);
        TArista RM=  new TArista("Rocha","Montevideo",270);
        TArista FDU=  new TArista("Florida","Durazno",60);

        List<TArista> aristas= new ArrayList<>();
        aristas.add(AR);
        aristas.add(CAA);
        aristas.add(CACO);
        aristas.add(CAD);
        aristas.add(CAP);
        aristas.add(COM);
        aristas.add(FD);
        aristas.add(MA);
        aristas.add(MCA);
        aristas.add(MCP);
        aristas.add(PR);
        aristas.add(RM);
        aristas.add(FDU);

        TGrafoDirigido grafo= new TGrafoDirigido(vertices,aristas);
        Double[][] matriz= UtilGrafos.obtenerMatrizCostos(grafo.getVertices());

        UtilGrafos.imprimirMatrizMejorado(matriz,grafo.getVertices(),"Matriz del Grafo del PD2");
        //Floyd
        Double[][] floyd= grafo.floyd();
        UtilGrafos.imprimirMatrizMejorado(floyd,grafo.getVertices(),"Matriz de Floyd PD2");

        //Excentricidad
        System.out.println("Excentricidad de cada Vertice:");
        for(TVertice vertice: vertices){
            System.out.println("-"+vertice.getEtiqueta()+": "+grafo.obtenerExcentricidad(vertice.getEtiqueta()));
        }
        Comparable centro= grafo.centroDelGrafo();
        System.out.println("El centro del grafo es: "+ centro);

        String separador= "-------------------------------------------------------------------------------------------------------";
        System.out.println(separador);
        System.out.println(separador);





        //PD3

        List<IVertice> vertices2= new ArrayList<>();
        String[] aeropuertos= ManejadorArchivosGenerico.leerArchivo("UT7TareasDomiciliarias/src/main/java/org/example/aeropuertos.txt",false);
        for(String aeropuerto: aeropuertos){
            TVertice vertice= new TVertice<>(aeropuerto,aeropuerto);
            vertices2.add(vertice);
        }
        String[] conexiones= ManejadorArchivosGenerico.leerArchivo("UT7TareasDomiciliarias/src/main/java/org/example/conexiones.txt",false);
        List<IArista> rutas= new ArrayList<>();
        for(String linea: conexiones){
            String[] contenido= linea.split(",");
            TArista arista= new TArista(contenido[0],contenido[1],Double.parseDouble(contenido[2]));
            rutas.add(arista);
        }



        TGrafoDirigido grafoAeropuertos= new TGrafoDirigido<>(vertices2,rutas);
        //Matriz final
        Double[][] matrizAeropuertos= UtilGrafos.obtenerMatrizCostos(grafoAeropuertos.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizAeropuertos,grafoAeropuertos.getVertices(),"Matriz de conexiones entre aeropuertos PD3");

        TCamino camino= grafoAeropuertos.mejorCamino("Montevideo","Rio_de_Janeiro");
        System.out.println("El costo del camino mas corto de Montevideo a Rio es: "+ grafoAeropuertos.mejorCamino("Montevideo","Rio_de_Janeiro").getCostoTotal());

        LinkedList<TVertice> bpf= grafoAeropuertos.bpf(montevideo);
        System.out.println("BPF"); //Orden de Tiempo de ejecución es O(n) porque recorro cada vertice pero consulto por cada arista
        for(TVertice ver: bpf){
            System.out.println(ver.getEtiqueta());
        }
        //Con montevideo si se visitan todos los vertices.


        TCaminos caminosMC= grafoAeropuertos.todosLosCaminos("Montevideo","Curitiba");
        boolean booleanMC=true;
        if(caminosMC.getCaminos().isEmpty()) {
            booleanMC = false;
        }

        TCaminos caminosPAS= grafoAeropuertos.todosLosCaminos("Porto_Alegre","Santos");
        boolean booleanPAS=true;
        if(caminosPAS.getCaminos().isEmpty()) {
            booleanPAS = false;
        }
        System.out.println("¿Existen conexión(es) entre Montevideo y Curitiba?  "+booleanMC);
        System.out.println("¿Existen conexión(es) entre Porto Alegre y Santos?  "+booleanPAS);


        System.out.println("Todos los caminso de montevidoe a rio");
        TCaminos caminosMPA= grafoAeropuertos.todosLosCaminos("Montevideo","Rio_de_Janeiro");
        LinkedList<TCamino> caminosMPALinked=caminosMPA.getCaminos();
        for(TCamino caminoMPA: caminosMPALinked){
            StringBuilder caminoString= new StringBuilder();
            caminoString.append(caminoMPA.getOrigen().getEtiqueta());
            for(Comparable k: caminoMPA.getOtrosVertices()){
                caminoString.append("-");
                caminoString.append(k);
            }
            caminoString.append("   -"+caminoMPA.getCostoTotal());
            System.out.println(caminoString);
        }

        System.out.println(separador);
        System.out.println(separador);

        //PD4
        List<IVertice> vertices4= new ArrayList<>();
        String[] aeropuertos4= ManejadorArchivosGenerico.leerArchivo("UT7TareasDomiciliarias/src/main/java/org/example/aeropuertos_2.txt",false);
        for(String aeropuerto4: aeropuertos4){
            TVertice vertice4= new TVertice<>(aeropuerto4,aeropuerto4);
            vertices4.add(vertice4);
        }
        String[] conexiones4= ManejadorArchivosGenerico.leerArchivo("UT7TareasDomiciliarias/src/main/java/org/example/conexiones_2.txt",false);
        List<IArista> rutas4= new ArrayList<>();
        for(String linea: conexiones4){
            String[] contenido= linea.split(",");
            TArista arista4= new TArista(contenido[0],contenido[1],Double.parseDouble(contenido[2]));
            rutas4.add(arista4);
        }



        TGrafoDirigido grafoAeropuertos4= new TGrafoDirigido<>(vertices4,rutas4);

        System.out.println("todos los caminos de Asuncion A Porto Alegre");
        TCaminos caminosMPA4= grafoAeropuertos4.todosLosCaminos("Asuncion","Porto_Alegre");
        LinkedList<TCamino> caminosMPALinked4=caminosMPA4.getCaminos();
        for(TCamino caminoMPA4: caminosMPALinked4){
            StringBuilder caminoString4= new StringBuilder();
            caminoString4.append(caminoMPA4.getOrigen().getEtiqueta());
            for(Comparable k4: caminoMPA4.getOtrosVertices()){
                caminoString4.append("-");
                caminoString4.append(k4);
            }
            caminoString4.append("   -"+caminoMPA4.getCostoTotal());
            System.out.println(caminoString4);
        }

        //Clasificar Arcos
        TVertice v0 = new TVertice("A","");
        TVertice v1 = new TVertice("B","");
        TVertice v2 = new TVertice("C","");
        TVertice v3 = new TVertice("D","");
        TVertice v4 = new TVertice("E","");

        List<IVertice> verticesClas = new ArrayList<>();
        verticesClas.add(v0);
        verticesClas.add(v1);
        verticesClas.add(v2);
        verticesClas.add(v3);
        verticesClas.add(v4);

        List<IArista> aristasClas = new ArrayList<>();
        aristasClas.add(new TArista("A", "B", 1));
        aristasClas.add(new TArista("B", "C", 1));
        aristasClas.add(new TArista("C", "D", 1));
        aristasClas.add(new TArista("B", "D", 3));
        aristasClas.add(new TArista("A", "D", 8));
        aristasClas.add(new TArista("E","B",1));
        aristasClas.add(new TArista("C","E",1));
        aristasClas.add(new TArista("E", "D", 1));


        TGrafoDirigido<String> grafoClas = new TGrafoDirigido<>(verticesClas, aristasClas);
        TipoListaArcos arcosArbol= new TipoListaArcos();
        TipoListaArcos arcosAvance= new TipoListaArcos();
        TipoListaArcos arcosRetroceso= new TipoListaArcos();
        TipoListaArcos arcosCruzados= new TipoListaArcos();

        grafoClas.clasificarArcos(v0,arcosArbol,arcosRetroceso,arcosAvance,arcosCruzados);
        System.out.println("");

        //Prueba de todos los caminos en orden topologico
        TVertice v0T = new TVertice("1","");
        TVertice v1T = new TVertice("2","");
        TVertice v2T = new TVertice("3","");
        TVertice v3T = new TVertice("4","");
        TVertice v4T = new TVertice("5","");
        TVertice v5T = new TVertice("6","");


        List<IVertice> verticesTop = new ArrayList<>();
        verticesTop.add(v0T);
        verticesTop.add(v1T);
        verticesTop.add(v2T);
        verticesTop.add(v3T);
        verticesTop.add(v4T);
        verticesTop.add(v5T);

        List<IArista> aristasTop = new ArrayList<>();
        aristasTop.add(new TArista("1", "3", 1));
        aristasTop.add(new TArista("2", "3", 1));
        aristasTop.add(new TArista("2", "4", 1));
        aristasTop.add(new TArista("3", "5", 3));
        aristasTop.add(new TArista("4", "5", 8));
        aristasTop.add(new TArista("4", "6", 8));


        TGrafoDirigido<String> grafoTop = new TGrafoDirigido<>(verticesTop, aristasTop);
        TCaminos grafoTopCam=grafoTop.obtenerTodosTopologicos();
        System.out.println();



    }
}