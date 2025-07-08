package tdas;

import java.util.LinkedList;
import java.util.List;

public class ElementoAVL<T> implements IElementoAVL<T> {
    private int altura;
    private Comparable etiqueta;
    private T dato;
    private IElementoAVL<T> hijoIzq, hijoDer;

    public ElementoAVL(Comparable etiqueta, T dato) {
        this.etiqueta = etiqueta;
        this.dato = dato;
        this.altura = 0;
    }

    public int getAltura() {
        return altura;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public T getDatos() {
        return dato;
    }

    public IElementoAVL<T> getHijoIzq() {
        return hijoIzq;
    }

    public IElementoAVL<T> getHijoDer() {
        return hijoDer;
    }

    public void setHijoIzq(IElementoAVL<T> nuevoHijoIzq) {
        if (nuevoHijoIzq == null) {
            hijoIzq = null;
        } else {
            hijoIzq = nuevoHijoIzq;
        }
    }

    public void setHijoDer(IElementoAVL<T> nuevoHijoDer) {
        this.hijoDer = nuevoHijoDer;
    }

    private static int obtenerAlturaDadoUnNodo(IElementoAVL elemento) {
        if (elemento == null) {
            return -1;
        }
        return elemento.getAltura();
    }

    public void actualizarAltura() {
        altura = 1 + Math.max(obtenerAlturaDadoUnNodo(hijoIzq), obtenerAlturaDadoUnNodo(hijoDer));
    }

    public IElementoAVL<T> buscar(Comparable unElemento) {
        if (unElemento == null) {
            return null;
        }
        int comparacion = etiqueta.compareTo(unElemento);
        if (comparacion == 0) {
            return this;
        } else if (comparacion > 0) {
            if (hijoIzq == null) {
                return null;
            }
            return hijoIzq.buscar(unElemento);
        } else {
            if (hijoDer == null) {
                return null;
            }
            return hijoDer.buscar(unElemento);
        }
    }

    public IElementoAVL<T> insertar(IElementoAVL<T> nuevoElemento) {
        int comparable = etiqueta.compareTo(nuevoElemento.getEtiqueta());
        if (comparable == 0) {
            return this;
        } else if (comparable < 0) {
            if (hijoDer == null) {
                hijoDer = nuevoElemento;
            } else {
                hijoDer = hijoDer.insertar(nuevoElemento);
            }
        } else {
            if (hijoIzq == null) {
                hijoIzq = nuevoElemento;
            } else {
                hijoIzq = hijoIzq.insertar(nuevoElemento);
            }
        }
        actualizarAltura();

        return balancear();

    }

    public IElementoAVL<T> eliminar(Comparable unaEtiqueta) {
        int comparable = this.etiqueta.compareTo(unaEtiqueta);
        if (comparable > 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
                actualizarAltura();
                return balancear();
            }
            return this;
        } else if (comparable < 0) {
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
                actualizarAltura();
                return balancear();
            }
            return this;
        } else {
            IElementoAVL nuevoNodoConEliminacion = this.quitarNodo();
            if (nuevoNodoConEliminacion != null) {
                actualizarAltura();
                return ((ElementoAVL<T>) nuevoNodoConEliminacion).balancear();
            } else {
                return nuevoNodoConEliminacion;
            }
        }
    }

    public IElementoAVL<T> quitarNodo() {
        if (hijoIzq == null) {
            return hijoDer;
        }
        if (hijoDer == null) {
            return hijoIzq;
        }
        IElementoAVL<T> elHijo = hijoIzq;
        IElementoAVL<T> elPadre = this;
        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }
        if (elPadre == this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }
        elHijo.setHijoDer(hijoDer);
        return elHijo;

    }

    public boolean isDesbalanced() {
        int diferencia = obtenerAlturaDadoUnNodo(hijoIzq) - obtenerAlturaDadoUnNodo(hijoDer);
        boolean result = false;
        if (diferencia > 1 || diferencia < -1) {
            return true;
        }
        boolean hijoDerBool = false;
        boolean hijoIzqBool = false;
        if (hijoIzq != null) {
            hijoIzqBool = hijoIzq.isDesbalanced();
        }
        if (hijoDer != null) {
            hijoDerBool = hijoDer.isDesbalanced();
        }
        result = result || hijoDerBool || hijoIzqBool;
        return result;
    }

    private IElementoAVL<T> balancear() {
        int diferencia = obtenerAlturaDadoUnNodo(hijoIzq) - obtenerAlturaDadoUnNodo(hijoDer);
        if (diferencia > 1) {
            IElementoAVL elementoIzq = this.hijoIzq;
            if (obtenerAlturaDadoUnNodo(elementoIzq.getHijoDer()) > obtenerAlturaDadoUnNodo(elementoIzq.getHijoIzq())) {
                return rotarLR(this);
            } else {
                return rotarLL(this);
            }
        }
        else if (diferencia < -1) {
            IElementoAVL elementoDer = this.hijoDer;
            if (obtenerAlturaDadoUnNodo(elementoDer.getHijoIzq()) > obtenerAlturaDadoUnNodo(elementoDer.getHijoDer())) {
                return rotarRL(this);
            } else {
                return rotarRR(this);
            }
        }
        return this;
    }

    private IElementoAVL<T> rotarLL(IElementoAVL<T> k2) {
        IElementoAVL k1 = k2.getHijoIzq();
        k2.setHijoIzq(k1.getHijoDer());
        k1.setHijoDer(k2);
        k2.actualizarAltura();
        k1.actualizarAltura();
        return k1;
    }

    private IElementoAVL<T> rotarRR(IElementoAVL<T> k1) {
        IElementoAVL<T> k2 = k1.getHijoDer();
        k1.setHijoDer(k2.getHijoIzq());
        k2.setHijoIzq(k1);
        k1.actualizarAltura();
        k2.actualizarAltura();
        return k2;
    }

    private IElementoAVL rotarLR(IElementoAVL k3) {
        k3.setHijoIzq(rotarRR(k3.getHijoIzq()));
        IElementoAVL<T> result = rotarLL(k3);
        result.actualizarAltura();
        return result;
    }

    private IElementoAVL rotarRL(IElementoAVL k1) {
        k1.setHijoDer(rotarLL(k1.getHijoDer()));
        IElementoAVL<T> result = rotarRR(k1);
        result.actualizarAltura();
        return result;
    }

    public void preOrden(LinkedList<T> unaLista) {
        unaLista.add(this.dato);
        if (hijoIzq != null) {
            hijoIzq.preOrden(unaLista);
        }
        if (hijoDer != null) {
            hijoDer.preOrden(unaLista);
        }
    }

    public void postOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.preOrden(unaLista);
        }
        if (hijoDer != null) {
            hijoDer.preOrden(unaLista);
        }
        unaLista.add(this.dato);
    }

    public void inOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.preOrden(unaLista);
        }
        unaLista.add(this.dato);
        if (hijoDer != null) {
            hijoDer.preOrden(unaLista);
        }
    }
}
