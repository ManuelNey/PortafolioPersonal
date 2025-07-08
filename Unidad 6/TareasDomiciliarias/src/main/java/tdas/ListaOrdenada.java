package tdas;

public class ListaOrdenada<T> extends Lista<T> {
    @Override
    public boolean insertar(T dato, Comparable clave)
    {
        Nodo<T> nuevo = new Nodo<T>(clave, dato);
        if (primero == null || primero.etiqueta.compareTo(clave) > 0)
        {
            nuevo.siguiente = primero;
            primero = nuevo;
            return true;
        }

        Nodo<T> actual = primero;
        while (actual.siguiente != null && actual.siguiente.etiqueta.compareTo(clave) <= 0)
        {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        return true;
    }
}
