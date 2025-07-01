package uy.edu.ucu.aed.UT3_TA10;

import java.util.LinkedList;

public class Sucursales {
    public LinkedList<String> listaSucursales;
    public Sucursales() {}
    public void agregarSucursal(String nombre) {
        listaSucursales.add(nombre);
    }
    public boolean buscarSucursal(String ciudad) {return listaSucursales.contains(ciudad);}
    public void eliminarSucursal(String nombre) {
        listaSucursales.remove(nombre);
    }
    public void listarSucursales(){
        for(String nombre : listaSucursales){
            System.out.println(nombre);
        }
    }
    public int cantSucursales(){
        return listaSucursales.size();
    }
    public boolean isEmpty(){
        return listaSucursales.isEmpty();
    }
    public static void main(String[] args) {
    }
}
