package medibles;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase que mide el tiempo y memoria al buscar elementos en una LinkedList.
 * Extiende la clase Medible para realizar mediciones específicas de búsqueda.
 */
public class MedicionPredecirHashMap extends Medible {

    // Atributo que almacena la LinkedList sobre la cual se realizarán las búsquedas
    private HashMap<String, String> hash;

    /**
     * Constructor de la clase MedicionBuscarLinkedList.
     * Inicializa la LinkedList sobre la cual se realizarán las búsquedas.
     *
     * @param linkedList LinkedList que se utilizará para las búsquedas.
     */
    public MedicionPredecirHashMap(HashMap<String, String> linkedList) {
        this.hash = linkedList;
    }

    @Override
    public void ejecutar( Object... params) {
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String)) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }
        String prefijo = (String) params[1];
        int repeticiones = (int) params[0];
        LinkedList<String> lista = new LinkedList<>();
        for (int i = 0; i < repeticiones; i++)
        {

            for (String clave : hash.keySet()) {
                if (clave.startsWith(prefijo)) {
                    lista.add(clave);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        // El objeto cuyo tamaño en memoria se medirá es la LinkedList utilizada para las búsquedas.
        return this.hash;
    }
}
