package Test_UT2_PD2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import UT2_PD2.InvertirArray;


public class InvertirArrayTest {
    private InvertirArray arreglo = new InvertirArray();
    @Test
    public void InvertirArrayConIndices1() {
        int[] array = new int[10];
        int[] arrayInvertido = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            arrayInvertido[array.length - i -1] = i;
        }
        arreglo.InvertirArrayConIndices(array,0,array.length-1);//Invierto todo
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], arrayInvertido[i]);
        }
    }
    @Test
    public void InvertirArrayConIndicesConDiferenciaInpar() {
        int [] array = {5,6,7,10,8,9,1,3};
        int [] arrayInvertido = {5,6,1,9,8,10,7,3};
        arreglo.InvertirArrayConIndices(array,2,6);
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], arrayInvertido[i]);
        }
    }
    @Test
    public void InvertirArrayConIndicesConDiferenciaPar() {
        int [] array = {1,2,8,7,6,5,4,3};
        int [] arrayInvertido = {1,2,3,4,5,6,7,8};
        arreglo.InvertirArrayConIndices(array,2,array.length-1);
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], arrayInvertido[i]);
        }
    }
    @Test
    public void InvertirArrayConIndiceVacio() {
        int[] array = new int[10]; //Hsgo espacio para 10 números de tipo int pero todos tienen 0
        arreglo.InvertirArrayConIndices(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], 0); //Por defecto en java los
        }
    }
    @Test
    public void InvertirArrayConIndicesValoresNoValidos() {
        int [] array = {1,2,8,7,6,5,4,3};
        try {
            arreglo.InvertirArrayConIndices(array,-3,array.length+3);
            fail("No saltó la exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Indice fuera de rango");
        }
    }
}
