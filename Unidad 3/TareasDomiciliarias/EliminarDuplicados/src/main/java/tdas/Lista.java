package tdas;

public class Lista<T> implements ILista<T> {

    private class Nodo<T> {

        private final Comparable etiqueta;
        private T dato;
        private Nodo<T> siguiente = null;
    
        public Nodo(Comparable etiqueta, T dato ) {
            this.etiqueta = etiqueta;
            this.dato = dato;
        }
    }
    private Integer cantidad = 0;
    private Nodo<T> primero;
    public T getPrimero() {
        if (primero == null || primero.dato == null) {
            return null;
        }
        return primero.dato;
    }
    protected Nodo<T> getSiguiente(Nodo<T> actual) {
        return actual.siguiente;
    }

    public Lista() {
        primero = null;
    }

    @Override
    public void insertar(T dato, Comparable clave) {

        Nodo<T> nuevoNodo = new Nodo<T>(clave, dato);
        if (primero == null) {
            primero = nuevoNodo;
        } else {
            nuevoNodo.siguiente = primero;
            primero = nuevoNodo;
        }
        cantidad++;
    }

    @Override
    public T buscar(Comparable clave) {
        Nodo<T> actual = primero;

        while (actual != null) {
            if (actual.etiqueta.equals(clave)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }

        return null;
    }
    @Override
    public boolean eliminar(Comparable clave) {
        if (primero == null){
            return false;
        }
        if (primero.etiqueta.equals(clave)) {
            primero = primero.siguiente;
            cantidad--;
            return true;
        }
        Nodo<T> actual = primero.siguiente; //Defino a partir de cual voy a comparar (no puede ser el primero puesto que esa condición ya la verificamos)
        Nodo<T> anterior = primero;
        while (actual != null) {
            if (actual.etiqueta.equals(clave)) {
                anterior.siguiente = actual.siguiente;
                cantidad--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.etiqueta);
            sb.append(" ");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    @Override
    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.etiqueta);
            sb.append(separador);
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    @Override
    public int cantElementos() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return primero == null; //Si el primero está en null la lista está vacío
    }
    public void EliminarDuplicados(){
        Nodo<T> NodoActual = this.primero;
        while (NodoActual != null) {
            Nodo<T> Extra = NodoActual.siguiente;
            while (Extra != null)
            {
                if (NodoActual.etiqueta.equals(Extra.etiqueta)) {
                    this.eliminar(Extra.etiqueta);
                }
            }
            NodoActual = NodoActual.siguiente;
        }
    }
}
