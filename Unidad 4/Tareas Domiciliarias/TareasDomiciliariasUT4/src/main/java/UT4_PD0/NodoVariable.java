package UT4_PD0;

import java.util.Map;

public class NodoVariable implements INodoVariable {
    private String nombre;

    public void definirOperacion(String operacion) {
        this.nombre = operacion;
    }

    public String obtenerOperacion() {
        return nombre;
    }

    public float calcular(Map<String, Integer> mapa) {
        return mapa.getOrDefault(nombre, 0);
    }

    public void agregar(String valor) {
        // No se permite
    }

    public IElemento obtenerIzquierdo(IElemento nodo) { return null; }
    public IElemento obtenerDerecho(IElemento nodo) { return null; }
    public void definirIzquierdo(IElemento izq) { }
    public void definirDerecho(IElemento der) { }
}
