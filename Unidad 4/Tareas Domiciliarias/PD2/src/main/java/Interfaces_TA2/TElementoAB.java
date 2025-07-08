// TElementoAB.java
package Interfaces_TA2;

public class TElementoAB<T> implements IElementoAB<T> {
    private Comparable etiqueta;
    private T datos;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;

    public TElementoAB(Comparable etiqueta, T datos) {
        this.etiqueta = etiqueta;
        this.datos = datos;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    @Override
    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> hijo) {
        this.hijoIzq = hijo;
    }

    @Override
    public void setHijoDer(TElementoAB<T> hijo) {
        this.hijoDer = hijo;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        TElementoAB<T> actual = this;
        if (actual != null) {
            if (actual.etiqueta.compareTo(unaEtiqueta) == 0) {
                return actual;
            } else if (actual.etiqueta.compareTo(unaEtiqueta) > 0) {
                return hijoIzq.buscar(unaEtiqueta);
            }
            return hijoDer.buscar(unaEtiqueta);
        }
        return null;
    }

    @Override
    public boolean insertar(TElementoAB<T> elemento) {
        if (elemento.getEtiqueta().compareTo(etiqueta) == 0) { //No pueden haber repetidos
            return false;
        } else if (elemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq == null) { //Si no está ocupado el hijo iquierdo lo inserto
                hijoIzq = elemento;
                return true;
            }
            return hijoIzq.insertar(elemento); // Busco dentro del hijo isquierdo
        } else {
            if (hijoDer == null) {
                hijoDer = elemento;
                return true;
            }
            return hijoDer.insertar(elemento);
        }
    }

    @Override
    public String preOrden() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(etiqueta);
        stringBuilder.append(" ");
        if (hijoIzq != null) {
            stringBuilder.append(hijoIzq.preOrden());
        }
        if (hijoDer != null) {
            stringBuilder.append(hijoDer.preOrden());
        }
        return stringBuilder.toString();
    }

    @Override
    public String inOrden() {
        StringBuilder stringBuilder = new StringBuilder();
        if (hijoIzq != null) {
            stringBuilder.append(hijoIzq.inOrden());
        }
        stringBuilder.append(etiqueta);
        if (hijoDer != null) {
            stringBuilder.append(hijoDer.inOrden());
        }
        return stringBuilder.toString();
    }

    @Override
    public String postOrden() {
        StringBuilder stringBuilder = new StringBuilder();
        if (hijoIzq != null) {
            stringBuilder.append(hijoIzq.inOrden());
        }
        if (hijoDer != null) {
            stringBuilder.append(hijoDer.inOrden());
        }
        stringBuilder.append(etiqueta);
        return stringBuilder.toString();
    }

    @Override
    public TElementoAB<T> eliminar(Comparable unaEtiqueta) {
        int comparacion = unaEtiqueta.compareTo(etiqueta);
        if (comparacion > 0) {
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
        } else if (comparacion < 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
        } else { //Si es igual la clave
            if (hijoIzq == null && hijoDer == null) { //Solo borro su referencia
                return null;

            } else if (hijoDer == null) {
                return hijoIzq;
            }
            else if (hijoIzq == null) {
                return hijoDer;
            }
            else {
                TElementoAB<T> nuevoPadre = buscarMenor(hijoDer); //Agarro del arbol de la derecha, busco al menor de la derecha
                etiqueta = nuevoPadre.getEtiqueta();
                datos = nuevoPadre.getDatos();
                hijoDer = hijoDer.eliminar(nuevoPadre.getEtiqueta());
            }
        }
        return this;
    }
    private TElementoAB<T> buscarMenor(TElementoAB<T> elemento) {
        if (elemento == null) {
            return null;
        }
        while (elemento.hijoIzq != null) {
            elemento = elemento.hijoIzq;
        }
        return elemento;
    }
    public int getTamano() {
        int contador = 0;
        if (hijoIzq != null) {
            contador = hijoIzq.getTamano();
        }
        if (hijoDer != null) {
            contador = hijoDer.getTamano();
        }
        return 1 + contador;


    }
    public int getAltura() {
        int alturaIzq = 0;
        int alturaDer = 0;
        if (hijoIzq != null)
        {
            alturaIzq = hijoIzq.getAltura();
        }
        if (hijoDer != null)
        {
            alturaDer = hijoDer.getAltura();
        }
        return Math.max(alturaDer, alturaIzq);
    }
}