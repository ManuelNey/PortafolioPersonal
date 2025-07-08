package tdas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArbolBB<T> implements IArbolBB<T>{
    private IElementoAB raiz;
    private int contador;
    public ArbolBB() {
       raiz = null;
    }
    public IElementoAB getRaiz() {
        return raiz;
    }
    public ArbolBB(IElementoAB raiz) {
        this.raiz = raiz;
    }
    public int getContador() {
        return contador;
    }
    public boolean insertar(IElementoAB elemento) {
        if (elemento == null || elemento.getEtiqueta() == null) {
            System.out.println("No pude insertar, sigo con: "+ contador + " elementos");
            return false;
        }
        if (raiz == null) {
            raiz = elemento;
            contador++;
            return true;
        }
        else {
            boolean valor = raiz.insertar(elemento);
            if (valor) {
                contador++;
            }
            return valor;
        }
    }
    public boolean insertar(Comparable etiqueta, T unDato) {
        if (etiqueta == null || unDato == null) {
            System.out.println("No pude insertar, sigo con: "+ contador + " elementos");
            return false;
        }
        ElementoAB <T> elemento = new ElementoAB<T>(etiqueta, unDato);
        if (raiz == null) {
            raiz = elemento;
            contador++;
            return true;
        }
        else {
            boolean valor = raiz.insertar(elemento);
            if (valor) {
                contador++;
            }
            return valor;
        }
    }
    public T buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta == null || raiz == null) {
            return null;
        }
        IElementoAB<T> resultado = raiz.buscar(unaEtiqueta);
        if (resultado == null) {
            return null;
        }
        return resultado.getDatos();
    }
    public List<T> preOrden() {
        if (raiz == null) {
            return null;
        }
        LinkedList<T> lista = new LinkedList<T>();
        raiz.preOrden(lista);
        return lista;
    }
    public List<T> inOrden() {
        if (raiz == null) {
            return null;
        }
        LinkedList<T> lista = new LinkedList<T>();
        raiz.inOrden(lista);
        return lista;
    }
    public List<T> postOrden() {
        if (raiz == null) {
            return null;
        }
        LinkedList<T> lista = new LinkedList<T>();
        raiz.postOrden(lista);
        return lista;
    }
    public void eliminar(Comparable unaEtiqueta) {
        if (raiz == null) {
            return;
        }
        raiz = raiz.eliminar(unaEtiqueta);

    }
    public boolean esVacio() {
        return raiz == null;
    }
    public boolean vaciar(){
        if (esVacio()) {
            return false;
        }
        raiz = null;
        return true;
    }
    public int obtenerAltura() {
        if (esVacio()) {
            return -1;
        }
        return raiz.obtenerAltura();
    }

    public int obtenerTamanio() {
        if (esVacio()) {
            return 0;
        }
        return raiz.obtenerTamanio();
    }
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (esVacio()) {
            return 0;
        }
        return raiz.obtenerNivel(unaEtiqueta);
    }
    public int obtenerHojas() {
        if (esVacio()) {
            return 0;
        }
        return raiz.obtenerHojas();
    }
    public int obtenerCantidadNivel(int nivelBuscar) {
        if (esVacio()) {
            return 0;
        }
        return raiz.cantNodosNivel(0, nivelBuscar);
    }
    public void listarHojasConNivel() {
        if (esVacio()) {
            return;
        }
        raiz.listarHojasConNivel(0);
    }
    public boolean esDeBusqueda(){
        ArrayList<Comparable> elementos = new ArrayList();
        raiz.inOrderEtiquetas(elementos);
        for (int i = 0; i < elementos.size() -1 ; i++) {
            if (elementos.get(i).compareTo(elementos.get(i+1)) >= 0) {
                return false;
            }
        }
        return true;
    }
    public IElementoAB<T> mayorClaveArbol() {
        if (esVacio()) {
            return null;
        }
        else return raiz.mayorClave();
    }
    public IElementoAB<T> menorClaveArbol() {
        if (esVacio()) {
            return null;
        }
        else return raiz.menorClave();
    }
    public IElementoAB<T> obtenerPadre(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        }
        return raiz.obtenerPadre(unaEtiqueta);
    }
}
