package UT4_PD0;

import java.util.List;
import java.util.Map;

public class ArbolExpresion extends NodoOperacion implements IExpresionArbol {
    private IElemento raiz;
    private int cursor;

    public ArbolExpresion() {
        raiz = null;
        cursor = 0;
    }

    public void construirDesdeNotacionPrefija(List<String> expresion) {
        cursor = 0;
        raiz = construirArbol(expresion);
    }

    private IElemento construirArbol(List<String> tokens) {
        if (cursor >= tokens.size()) return null;

        String token = tokens.get(cursor++);
        if (esOperador(token)) {
            NodoOperacion nodo = new NodoOperacion();
            nodo.definirOperacion(token);
            nodo.definirIzquierdo(construirArbol(tokens));
            nodo.definirDerecho(construirArbol(tokens));
            return nodo;
        } else if (esNumero(token)) {
            NodoConstante constante = new NodoConstante();
            constante.definirOperacion(token);
            return constante;
        } else {
            NodoVariable variable = new NodoVariable();
            variable.definirOperacion(token);
            return variable;
        }
    }

    private boolean esOperador(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    private boolean esNumero(String token) {
        try {
            Float.parseFloat(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public float calcular(Map<String, Integer> valores) {
        if (raiz == null) throw new IllegalStateException("Árbol vacío.");
        return raiz.calcular(valores);
    }

    @Override
    public String obtenerOperacion() {
        return raiz.obtenerOperacion();
    }

    @Override
    public void definirOperacion(String op) {
        raiz.definirOperacion(op);
    }

    @Override
    public IElemento obtenerIzquierdo(IElemento nodo) {
        return raiz.obtenerIzquierdo(nodo);
    }

    @Override
    public IElemento obtenerDerecho(IElemento nodo) {
        return raiz.obtenerDerecho(nodo);
    }

    @Override
    public void definirIzquierdo(IElemento izq) {
        raiz.definirIzquierdo(izq);
    }

    @Override
    public void definirDerecho(IElemento der) {
        raiz.definirDerecho(der);
    }
}
