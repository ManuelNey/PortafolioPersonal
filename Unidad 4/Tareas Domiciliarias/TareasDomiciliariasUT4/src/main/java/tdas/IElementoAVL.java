package tdas;

import java.util.ArrayList;
import java.util.LinkedList;

public interface IElementoAVL<T>{
    public Comparable getEtiqueta();

    public IElementoAVL<T> getHijoIzq();

    public IElementoAVL<T> getHijoDer();
    public int getAltura();

    void actualizarAltura();

    public void setHijoIzq(IElementoAVL<T> elemento);

    public void setHijoDer(IElementoAVL<T> elemento);
    public IElementoAVL<T> buscar(Comparable unaEtiqueta);

    public IElementoAVL<T> insertar(IElementoAVL<T> elemento);

    public void preOrden(LinkedList<T> unaLista);


    public void inOrden(LinkedList<T> unaLista);


    public void postOrden(LinkedList<T> unaLista);

    boolean isDesbalanced();
    public T getDatos();


    public IElementoAVL<T> eliminar(Comparable unaEtiqueta);



}
