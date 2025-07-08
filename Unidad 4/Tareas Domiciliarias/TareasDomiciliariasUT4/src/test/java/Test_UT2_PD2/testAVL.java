package Test_UT2_PD2;

import org.junit.jupiter.api.Test;
import tdas.ArbolAVL;
import tdas.ArbolBB;
import tdas.ElementoAVL;

import static org.junit.jupiter.api.Assertions.*;

public class testAVL {
    private ArbolAVL<String> arbol = new ArbolAVL<>();
    @Test
    public void testAVLBalanceoAlInsertarRotacionRL() {
        arbol.insertar("C", "C");
        arbol.insertar("Z", "Z");
        arbol.insertar("A", "A");
        arbol.insertar("J", "J");
        arbol.insertar("K", "K");
        // El subarbol de z quedaría con z de raíz, j como hijo izquierdo de z, null como hijo derecho de z y para j su hijo derecho sería k.
        // Estando z desbalanceado ya que tiene diferencia de 2 de altura en sus subarboles. Este caso necesita una rotación RL
        // Ya el hijo izquierdo de z está desvalanceado (LL) y para poder hacer la LL y que este balance el arbol hay que hacer antes
        // Una rotación RR entre el hijo izquierdo de z (j) y el nodo de mayor altura del hijo izquierdo de z (k)
        // Z se debería de desbalancear tiene 2 de diferencia entre sus hijos.
        assertTrue(arbol.isBalanced());
        assertEquals(arbol.getRaiz().getHijoDer().getEtiqueta(), "K");
        arbol.insertar("M", "M");
        //M desbalancea la raíz (C)
        // Se hace una rotación RR. entre K y C. K pasa a ser raiz y C pasa a ser su hijo izquierdo
        assertEquals("K",arbol.getRaiz().getEtiqueta());
        assertTrue(arbol.isBalanced());

        assertEquals(arbol.getRaiz().getHijoDer().getEtiqueta(), "Z");
        arbol.insertar("N", "N");

        // Z se balancea con RL. rotación LL entre N y M. rotación RR entre N y Z
        assertTrue(arbol.isBalanced());
        //N debería ser el hijo derecho de la raíz, ya que se rotó con Z y Z era el hijo derecho de la raíz
        assertEquals(arbol.getRaiz().getHijoDer().getEtiqueta(), "N");

        arbol.insertar("B", "B");
        //B no rompe ningún balance
        assertTrue(arbol.isBalanced());
    }
    @Test
    public void testAVLBalanceoAlEliminar() {
        arbol.insertar("R", "R");
        arbol.insertar("C", "C");
        arbol.insertar("W", "W");
        arbol.insertar("Z", "Z");
        arbol.insertar("Z", "Z");
        arbol.eliminar("C");
        assertTrue(arbol.isBalanced());
        assertEquals(arbol.getRaiz().getHijoDer().getEtiqueta(), "Z");
        assertEquals(arbol.getRaiz().getHijoIzq().getEtiqueta(), "R");
        assertEquals(arbol.getRaiz().getEtiqueta(), "W");
    }


}
