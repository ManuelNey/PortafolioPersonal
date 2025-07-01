

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Clases.TAlumno;
import org.junit.jupiter.api.Test;
import tdas.Conjunto;
import tdas.IConjunto;

public class ConjuntosTest {

    @Test
    public void testUnionEInterseccion() {
        Conjunto<TAlumno> curso1 = new Conjunto<>();
        Conjunto<TAlumno> curso2 = new Conjunto<>();

        TAlumno Bruno = new TAlumno(2604, "Bruno", "Uhalde");
        TAlumno Facundo = new TAlumno(1007, "Facundo", "Miegges");
        TAlumno Alan = new TAlumno(1903, "Alan", "Egaña");
        TAlumno Franco = new TAlumno(1406, "Franco", "Morán");
        TAlumno Kevin = new TAlumno(1406, "Franco", "Morán");

        curso1.insertar(Bruno, Bruno.getCedula());
        curso1.insertar(Facundo, Facundo.getCedula());
        curso1.insertar(Alan, Alan.getCedula());

        curso2.insertar(Facundo, Facundo.getCedula());
        curso2.insertar(Kevin, Kevin.getCedula());
        curso2.insertar(Franco, Franco.getCedula());

        IConjunto<TAlumno> union = curso1.union(curso2);
        IConjunto<TAlumno> interseccion = curso1.interseccion(curso2);

        assertEquals(5, union.cantElementos());
        assertEquals(1, interseccion.cantElementos());
        assertEquals(Facundo, interseccion.buscar(1007));
    }
}
