package principales;

import principales.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmosYEstructura {
    private ArrayList<Sucursal> sucursales;

    public AlgoritmosYEstructura() {
        sucursales = new ArrayList<>();
    }

    public boolean agregarSucursal(Sucursal sucursal) {
        if (sucursal == null) return false;

        for (Sucursal s : sucursales) {
            if (s.getCiudad().equalsIgnoreCase(sucursal.getCiudad())) {
                System.out.println("Ya existe una sucursal en la ciudad: " + sucursal.getCiudad());
                return false;
            }
        }
        sucursales.add(sucursal);
        return true;
    }

    public Sucursal buscarSucursal(String ciudad) {
        if (ciudad == null) return null;

        for (Sucursal s : sucursales) {
            if (s.getCiudad().equalsIgnoreCase(ciudad)) {
                return s;
            }
        }
        return null;
    }

    public boolean eliminarSucursal(String ciudad) {
        if (ciudad == null) return false;

        for (Sucursal s : sucursales) {
            if (s.getCiudad().equalsIgnoreCase(ciudad)) {
                sucursales.remove(s);
                return true;
            }
        }
        return false;
    }

    public String[] listarSucursales() {
        String[] lista = new String[sucursales.size()];
        for (int i = 0; i < sucursales.size(); i++) {
            lista[i] = sucursales.get(i).getCiudad();
        }
        return lista;
    }

    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sucursales.size(); i++) {
            sb.append(sucursales.get(i).getCiudad());
            if (i < sucursales.size() - 1) sb.append(separador);
        }
        return sb.toString();
    }

    public int cantidadSucursales() {
        return sucursales.size();
    }

    public boolean vacio() {
        return sucursales.isEmpty();
    }
}
