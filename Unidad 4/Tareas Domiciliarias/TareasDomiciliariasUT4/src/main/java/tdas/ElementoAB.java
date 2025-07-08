package tdas;

import java.util.ArrayList;
import java.util.LinkedList;

public class ElementoAB<T> implements IElementoAB<T>{
    private Comparable etiqueta;
    private T dato;
    private IElementoAB<T> hijoIzq, hijoDer;
    public ElementoAB(Comparable etiqueta, T unDato) {
        this.etiqueta = etiqueta;
        this.dato = unDato;
        nivel = 0;
    }
    private int nivel;
    public int getNivel() {
        return nivel;
    }
    public Comparable getEtiqueta() {
        return etiqueta;
    }
    public T getDatos() {
        return dato;
    }
    public void setDatos(T dato) {
        this.dato = dato;
    }
    public IElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }
    public IElementoAB<T> getHijoDer() {
        return hijoDer;
    }
    public void setHijoIzq(IElementoAB<T> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }
    public void setHijoDer(IElementoAB<T> hijoDer) {
        this.hijoDer = hijoDer;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public boolean insertar(IElementoAB<T> unElemento) {
        if (unElemento == null || unElemento.getEtiqueta() == null) {
            return false;
        }
        int comparacion = etiqueta.compareTo(unElemento.getEtiqueta());
        if (comparacion == 0) {
            return false;
        }
        else if (comparacion > 0) {
            if (hijoIzq == null) {
                hijoIzq = unElemento;
                return true;
            }
            else{
                return hijoIzq.insertar(unElemento);
            }
        }
        else{
            if (hijoDer == null) {
                hijoDer = unElemento;
                return true;
            }
            else{
                return hijoDer.insertar(unElemento);
            }
        }
    }

    public void preOrden(LinkedList<T> unaLista) {
        unaLista.add(dato);
        if (hijoIzq != null)
        {
            hijoIzq.preOrden(unaLista);
        }
        if (hijoDer != null)
        {
            hijoDer.preOrden(unaLista);
        }
    }
    public void inOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null)
        {
            hijoIzq.inOrden(unaLista);
        }
        unaLista.add(dato);
        if (hijoDer != null)
        {
            hijoDer.inOrden(unaLista);
        }
    }

    public void postOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null)
        {
            hijoIzq.postOrden(unaLista);
        }
        if (hijoDer != null)
        {
            hijoDer.postOrden(unaLista);
        }
        unaLista.add(dato);
    }

    /*
    public boolean eliminar(Comparable etiqueta) {
        if (etiqueta == null) {
            return false;
        }
        int comparacion = this.getEtiqueta().compareTo(etiqueta);
        if (comparacion == 0) {
            if (hijoIzq == null || hijoDer == null) {
                //Me elimino a mi mismo
                return true;
            }
        }
        else if (comparacion < 0) {
            if (hijoIzq == null) {
                return false;
            }
            else{
                hijoIzq.eliminar(etiqueta);
            }
        }
        else{
            if (hijoDer == null) {
                return false;
            }
            else{
                hijoDer.eliminar(etiqueta);
            }
        }
    }
    */
    public IElementoAB buscar(Comparable unElemento) {
        if (unElemento == null) {
            return null;
        }
        int comparacion = etiqueta.compareTo(unElemento);
        if (comparacion == 0) {
            return this;
        }
        else if (comparacion > 0) {
            if (hijoIzq == null) {
                return null;
            }
            return hijoIzq.buscar(unElemento);
        }
        else{
            if (hijoDer == null) {
                return null;
            }
            return hijoDer.buscar(unElemento);
        }
    }
    public int obtenerAltura()
    {
        int AlturaLadoIzq = 0;
        int AlturaLadoDer = 0;
        if (hijoIzq == null && hijoDer == null) {
            return 0;
        }
        if (hijoDer != null) {
            AlturaLadoDer = hijoDer.obtenerAltura() ;
        }
        if (hijoIzq != null)
        {
            AlturaLadoIzq = hijoIzq.obtenerAltura();
        }
        return Math.max(AlturaLadoIzq, AlturaLadoDer) + 1;
    }
    public int obtenerHojas(){
        int contadorHojas = 0;
        if (hijoIzq == null && hijoDer == null) {
            contadorHojas++;
        }
        if (hijoIzq != null) {
            contadorHojas += hijoIzq.obtenerHojas();
        }
        if (hijoDer != null) {
            contadorHojas += hijoDer.obtenerHojas();
        }
        return contadorHojas;
    }

    public int obtenerTamanio() {
        int contadorNodos = 0;
        if (hijoIzq != null) {
            contadorNodos += hijoIzq.obtenerTamanio();
        }
        if (hijoDer != null) {
            contadorNodos += hijoDer.obtenerTamanio();
        }
        return contadorNodos + 1;
    }

    public int obtenerNivel(Comparable unaEtiqueta) {
        if (this.etiqueta.compareTo(unaEtiqueta) == 0) {
            return 0;
        }
        if (hijoIzq != null) {
            int nivelIzq = hijoIzq.obtenerNivel(unaEtiqueta);
            if (nivelIzq != -1) {
                return nivelIzq + 1;
            }
        }
        if (hijoDer != null) {
            int nivelDer = hijoDer.obtenerNivel(unaEtiqueta);
            if (nivelDer != -1) {
                return nivelDer + 1;
            }
        }
        return -1;
    }

    public IElementoAB<T> eliminar(Comparable unaEtiqueta) {
        int comparable = this.etiqueta.compareTo(unaEtiqueta);
        if (comparable > 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }
        if (comparable < 0){
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return this.quitarNodo();
    }

    public IElementoAB<T> quitarNodo() {
        if(hijoIzq == null){
            return hijoDer;
        }
        if (hijoDer == null){
            return hijoIzq;
        }
        IElementoAB<T> elHijo = hijoIzq;
        IElementoAB<T> elPadre = this;
        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }
        if (elPadre == this){
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }
        elHijo.setHijoDer(hijoDer);
        return elHijo;

    }
    public int cantNodosNivel(int nivelactual, int nivelbuscar) {
        int cantidad = 0;
        if (nivelactual == nivelbuscar) {
            cantidad++;
        }
        if (hijoDer != null) {
            cantidad+= hijoDer.cantNodosNivel(nivelactual+1, nivelbuscar);
        }
        if (hijoIzq != null) {
            cantidad+= hijoIzq.cantNodosNivel(nivelactual+1, nivelbuscar);
        }
        return cantidad;
    }
    public void listarHojasConNivel(int nivelActual) {
        if (hijoIzq == null && hijoDer == null) {
            System.out.println("El nodo con la etiqueta " + this.etiqueta + "es hoja en el nivel " + nivelActual + ".");
        }
        if (hijoIzq != null) {
            hijoIzq.listarHojasConNivel(nivelActual+1);
        }
        if (hijoDer != null) {
            hijoDer.listarHojasConNivel(nivelActual+1);
        }
    }

    @Override
    public void inOrderEtiquetas(ArrayList<Comparable> elementos) {
        if (hijoIzq != null)
        {
            hijoIzq.inOrderEtiquetas(elementos);
        }
        elementos.add(etiqueta);
        if (hijoDer != null)
        {
            hijoDer.inOrderEtiquetas(elementos);
        }
    }

    public ElementoAB<T> menorClave(){
        if(hijoIzq == null){
            return this;
        }
        else{
            return hijoIzq.menorClave();
        }
    }

    public ElementoAB<T> mayorClave(){
        if(hijoDer == null){
            return this;
        }
        else{
            return hijoDer.mayorClave();
        }
    }
    public ElementoAB<T> obtenerPadre(Comparable hijo){
        if (this.etiqueta.compareTo(hijo) == 0) {
            return null;
        }
        int comparacion = this.etiqueta.compareTo(hijo);
        if (comparacion > 0)
        {
            if (hijoIzq != null) {
                if (hijoIzq.getEtiqueta().compareTo(hijo) == 0) {
                    return this;
                }
                return hijoIzq.obtenerPadre(hijo);
            }
        }
        if (comparacion < 0)
        {
            if (hijoDer != null)
            {
                if (hijoDer.getEtiqueta().compareTo(hijo) == 0){
                    return this;
                }
                return hijoDer.obtenerPadre(hijo);
            }
        }
        return null;
    }
}
