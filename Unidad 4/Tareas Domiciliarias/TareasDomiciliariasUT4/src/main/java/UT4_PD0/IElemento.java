package UT4_PD0;

import java.util.Map;

public interface IElemento {
    String obtenerOperacion();
    float calcular(Map<String, Integer> variables);
    void agregar(String valor);
    IElemento obtenerIzquierdo(IElemento nodo);
    IElemento obtenerDerecho(IElemento nodo);
    void definirIzquierdo(IElemento izquierdo);
    void definirDerecho(IElemento derecho);
    void definirOperacion(String operacion);
}
