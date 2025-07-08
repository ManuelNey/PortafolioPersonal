package ParcialFamilia.parcial1;

import tdas.ArbolBB;
import tdas.ElementoAB;
import tdas.IArbolBB;
import tdas.IElementoAB;

public class Main {
    public static void main(String[] args) {
        // Crear personas
        // TODO: Crear las personas necesarias para el árbol genealógico

        // Calcular parentescos
        // TODO: Realizar los cálculos de parentesco entre las personas que se indican en el enunciado
        ResultadoParentesco resultado = Genealogia.calcularParentesco(arbol.getRaiz(), BrunoNodo, FernandaNodo);
        // Emitir archivo de salida
        System.out.println(resultado);
        // TODO: Emitir el archivo de salida (Resultados.txt) con los resultados de los cálculos de parentesco
    }
}
