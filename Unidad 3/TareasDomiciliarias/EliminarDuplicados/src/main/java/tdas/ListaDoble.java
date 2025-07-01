package tdas;

public class ListaDoble <T> implements ILista<T> {

    private class Nodo<T> {

        private final Comparable etiqueta;
        private T dato;
        private Nodo<T> siguiente = null;
        private Nodo<T> anterior = null;

        public Nodo(Comparable etiqueta, T dato ) {
            this.etiqueta = etiqueta;
            this.dato = dato;
        }
    }
    private Integer cantidad = 0;
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    public T getPrimero() {
        if (primero == null || primero.dato == null) {
            return null;
        }
        return primero.dato;
    }
    protected Nodo<T> getSiguiente(Nodo<T> actual) {
        return actual.siguiente;
    }

    public ListaDoble() {
        primero = null;
    }

    @Override
    public void insertar(T dato, Comparable clave) {

        Nodo<T> nuevoNodo = new Nodo<T>(clave, dato);
        if (primero == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            nuevoNodo.siguiente = primero;
            primero.anterior = nuevoNodo;
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
            if (primero != null) {
                primero.anterior = null;
            } else {
                ultimo = null; //Establezco que el último es null si el primero también lo es
            }
            cantidad--;
            return true;
        }
        Nodo<T> actual = primero; //Defino a partir de cual voy a comparar (no puede ser el primero puesto que esa condición ya la verificamos)
        while (actual != null) {
            if (actual.etiqueta.equals(clave)) {
                Nodo<T> siguiente = actual.siguiente;
                Nodo<T> anterior = actual.anterior;
                if (siguiente != null) {
                    siguiente.anterior = anterior; //Conecto el puntero del siguiente hacia el anterior
                } else {
                    ultimo = anterior; //elimino el ultimo nodo
                }
                anterior.siguiente = siguiente; //Conecto el puntero del anterior al siguiente
                cantidad--;
                return true;
            }
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
