package tdas;


import java.util.LinkedList;
import java.util.List;


public class ArbolAVL<T> implements IArbolAVL<T>{
    private IElementoAVL raiz;
    public ArbolAVL() {
        raiz = null;
    }
    public ArbolAVL(IElementoAVL raiz) {
        this.raiz = raiz;
    }
    public IElementoAVL getRaiz() {
        return raiz;
    }
    public boolean insertar(Comparable etiqueta, T unDato) {
        if (etiqueta == null || unDato == null) {
            return false;
        }
        IElementoAVL<T> elemento = new ElementoAVL<T>(etiqueta, unDato);
        if (raiz == null) {
            raiz = elemento;
            return true;
        }
        else {
            IElementoAVL elementoBuscado = raiz.buscar(etiqueta);
            if (elementoBuscado != null) {
                return false;
            }
            raiz = raiz.insertar(elemento);
            return true;
        }
    }
    public boolean isBalanced() {
        if (raiz == null) {
            return true;
        }
        else {
            return !raiz.isDesbalanced();
        }
    }
    public T buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta == null || raiz == null) {
            return null;
        }
        IElementoAVL<T> resultado = raiz.buscar(unaEtiqueta);
        if (resultado == null) {
            return null;
        }
        return resultado.getDatos();
    }
    public void eliminar(Comparable etiqueta) {
        if (raiz != null || etiqueta != null)
        {
            return;
        }
        IElementoAVL elementoBuscado = raiz.buscar(etiqueta);
        if ( elementoBuscado != null) {
            raiz = raiz.eliminar(etiqueta);
            }
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
}