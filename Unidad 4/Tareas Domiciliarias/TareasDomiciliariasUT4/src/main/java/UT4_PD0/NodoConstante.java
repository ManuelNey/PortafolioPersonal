package UT4_PD0;

import java.util.HashMap;
import java.util.Map;

public class NodoConstante implements IElemento {
    private float valor;

    @Override
    public void definirOperacion(String operacion) {
        this.valor = Float.parseFloat(operacion);
    }

    @Override
    public String obtenerOperacion() {
        return String.valueOf(valor);
    }

    @Override
    public float calcular(Map<String, Integer> variables) {
        return valor;
    }

    @Override
    public void agregar(String valor) {
        // No aplica
    }

    @Override
    public IElemento obtenerDerecho(IElemento nodo) {
        return null;
    }

    @Override
    public IElemento obtenerIzquierdo(IElemento nodo) {
        return null;
    }

    @Override
    public void definirIzquierdo(IElemento izq) {
        // No aplica
    }

    @Override
    public void definirDerecho(IElemento der) {
        // No aplica
    }
}
