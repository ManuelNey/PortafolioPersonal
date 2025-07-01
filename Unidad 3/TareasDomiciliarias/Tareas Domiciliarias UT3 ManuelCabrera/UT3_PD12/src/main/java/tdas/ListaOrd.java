package tdas;

public class ListaOrd<T>  extends Lista<T> {

    @Override
    public void insertar(T dato, Comparable clave) {
        Nodo<T> nuevoNodo = new Nodo<T>(clave, dato);

        // Si la lista está vacía o la clave es menor que la del primero
        if (this.primero == null || clave.compareTo(primero.etiqueta) < 0) {
            nuevoNodo.siguiente = primero;
            primero = nuevoNodo;
        } else {
            Nodo<T> actual = primero;
            // Buscamos el lugar adecuado para insertar
            while (actual.siguiente != null && clave.compareTo(actual.siguiente.etiqueta) > 0) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        cantidad++;
    }
}
