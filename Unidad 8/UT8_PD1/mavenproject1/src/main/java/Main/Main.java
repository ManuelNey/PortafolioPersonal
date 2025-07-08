package Main;

import com.mycompany.mavenproject1.TArista;
import com.mycompany.mavenproject1.TGrafoNoDirigido;
import com.mycompany.mavenproject1.TVertice;
import com.mycompany.mavenproject1.UtilGrafos;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear vértices
        TVertice a = new TVertice("A");
        TVertice b = new TVertice("B");
        TVertice c = new TVertice("C");
        TVertice d = new TVertice("D");

        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);

        LinkedList<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista(a.getEtiqueta(), c.getEtiqueta(), 2));
        aristas.add(new TArista(b.getEtiqueta(), c.getEtiqueta(), 6));
        aristas.add(new TArista(a.getEtiqueta(), b.getEtiqueta(), 1));
        aristas.add(new TArista(a.getEtiqueta(), d.getEtiqueta(), 5));
        aristas.add(new TArista(b.getEtiqueta(), d.getEtiqueta(), 4));


        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);
        UtilGrafos.imprimirMatrizMejorado(UtilGrafos.obtenerMatrizCostos(grafo.getVertices()), grafo.getVertices(), "La mariz es");
        TGrafoNoDirigido grafoPrim = grafo.Prim();
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(grafoPrim.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, grafoPrim.getVertices(), "La mariz es");

    }

}
