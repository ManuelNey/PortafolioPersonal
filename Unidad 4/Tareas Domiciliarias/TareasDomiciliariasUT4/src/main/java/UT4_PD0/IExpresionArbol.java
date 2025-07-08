package UT4_PD0;

import java.util.Map;

public interface IExpresionArbol extends IElemento {
    String obtenerOperacion();
    float calcular(Map<String, Integer> variables);
}
