package UT4_PD0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> tokens = Arrays.asList("*", "+", "x", "y", "-", "5", "z");

        //La notación prefija dice lo siguiente:
        // El primer símbolo en este caso:* es el operador principal
        //
        //Luego vienen dos operandos:
        //
        // Primero se dice + x y que es que la operación entre x e y es +
        //
        // Segundo se dice - 5 z que es que la operacion entre 5 y z es -

        //Asigno los valores a las variables del arbol

        HashMap<String, Integer> asignaciones = new HashMap<>();
        asignaciones.put("x", 10);
        asignaciones.put("y", 3);
        asignaciones.put("z", 3);

        ArbolExpresion arbol = new ArbolExpresion();
        arbol.construirDesdeNotacionPrefija(tokens);

        float result = arbol.calcular(asignaciones);
        System.out.println("El resultado es: " + result);


    }
}