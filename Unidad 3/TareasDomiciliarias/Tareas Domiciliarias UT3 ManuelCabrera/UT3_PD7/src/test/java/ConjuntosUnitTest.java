import org.junit.jupiter.api.Test;
import tdas.Conjunto;
import tdas.IConjunto;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuntosUnitTest {

    @Test
    void testUnionEInterseccion() {
        IConjunto<Integer> conjuntoA = new Conjunto<>();
        IConjunto<Integer> conjuntoB = new Conjunto<>();
        conjuntoA.insertar(5,5);
        conjuntoA.insertar(3,3);
        conjuntoA.insertar(1,1);
        conjuntoB.insertar(6,6);
        conjuntoB.insertar(3,3);
        conjuntoB.insertar(2,2);


        IConjunto<Integer> union = conjuntoA.union(conjuntoB);
        assertEquals("1,2,3,5,6,", union.imprimir(","));

        // Intersección: solo el 3
        IConjunto<Integer> interseccion = conjuntoA.interseccion(conjuntoB);
        assertEquals("3,", interseccion.imprimir(","));
    }

}
