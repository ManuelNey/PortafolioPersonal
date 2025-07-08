package medibles;

import java.util.TreeMap;

public class MedicionBuscarTreeMap extends Medible {

    private TreeMap<String, String> treeMap;

    public MedicionBuscarTreeMap(TreeMap<String, String> treeMap) {
        this.treeMap = treeMap;
    }

    @Override
    public void ejecutar(Object... params) {
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String[])) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }

        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];

        for (int i = 0; i < repeticion; i++) {
            for (String palabra : palabras) {
                treeMap.containsKey(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.treeMap;
    }
}
