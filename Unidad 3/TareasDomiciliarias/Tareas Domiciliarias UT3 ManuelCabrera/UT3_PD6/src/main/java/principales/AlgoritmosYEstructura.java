package principales;

import principales.Sucursal;
import tda.INodo;
import tda.ListaEnlazadaSimple;

public class AlgoritmosYEstructura {
    private ListaEnlazadaSimple<Sucursal> sucursales;
    public AlgoritmosYEstructura() {
        sucursales = new ListaEnlazadaSimple<>();
    }
    public boolean agregarSucursal(Sucursal sucursal)
    {
        //El metodo se encarga de revisar si una ciudada ya esta agregada y que además no sea null, si cumple esto es agregada
        //No se puede poner 2 veces la misma ciudad
        // Que la sucursal no sea nula
        if (sucursales == null || sucursal == null) {
            return false;
        }
        if (sucursales.buscar(sucursal.getCiudad()) == null) {
            sucursales.insertar(sucursal.getCiudad(), sucursal);
            return true;
        }
        System.out.println("Ya existe una sucursal con ese ciudad" + sucursal.getCiudad());
        return false;
    }
    public Sucursal buscarSucursal(String ciudad){
        if (sucursales == null || ciudad == null) {
            return null;
        }
        Sucursal sucusal =sucursales.buscar(ciudad).getDato();
        if (sucusal == null) {
            return null;
        }
        return sucusal;
    }
    public boolean eliminarSucursal(String ciudad){
        if (sucursales == null || ciudad == null) {
            return false;
        }
        INodo<Sucursal> nodo = sucursales.buscar(ciudad);
        if (nodo == null) {
            return false;
        }
        sucursales.eliminar(nodo.getDato().getCiudad());
        return true;
    }
    public String[] listarSucursales(){
        if (sucursales == null || sucursales.cantElementos() == 0) {
            return null;
        }
        String[] result = new String[sucursales.cantElementos()];
        Object[] sucursaleslista = sucursales.GetArray();
        for (int i = 0; i < sucursales.cantElementos(); i++) {
            Sucursal sucursal = (Sucursal) sucursaleslista[i];
            result[i] = sucursal.getCiudad();
        }
        return result;
    }
    public String imprimir(String separador){
        String[] sucursaleslista = listarSucursales();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < listarSucursales().length; i++) {
            buffer.append(sucursaleslista[i]);
            buffer.append(separador);
        }
        return buffer.toString();
    }
    public int cantidadSucursales(){
        if (sucursales == null) {
            return -1;
        }
        return sucursales.cantElementos();
    }
    public boolean vacio(){
        if (sucursales == null) {
            return true;
        }
        return sucursales.esVacia();
    }

}
