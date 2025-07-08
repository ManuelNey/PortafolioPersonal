package UT4_PD0;

import java.util.Map;

public class NodoOperacion implements INodoOperacion {
    protected String operacion;
    protected IElemento izquierdo;
    protected IElemento derecho;

    public String obtenerOperacion() {
        return operacion;
    }

    public void definirOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void definirIzquierdo(IElemento izq) {
        this.izquierdo = izq;
    }

    public void definirDerecho(IElemento der) {
        this.derecho = der;
    }

    public float calcular(Map<String, Integer> vars) {
        float a = izquierdo.calcular(vars);
        float b = derecho.calcular(vars);
        return switch (operacion) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    public void agregar(String valor) {
        // Sin implementación
    }

    public IElemento obtenerIzquierdo(IElemento nodo) {
        return izquierdo;
    }

    public IElemento obtenerDerecho(IElemento nodo) {
        return derecho;
    }
}
