package tda;

public class ListaEnlazadaSimple<T> implements ILista<T> {

    private int cantidad = 0;
    private INodo<T> primero;

    public ListaEnlazadaSimple() {
        this.primero = null;
    }

    public void insertar(INodo<T> nodo) {
        if (primero == null) {
            primero = nodo;
        } else {
            INodo<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
        cantidad++;
    }

    @Override
    public void insertar(Comparable etiqueta, T dato) {
        INodo<T> nuevoNodo = new Nodo<>(etiqueta, dato);
        insertar(nuevoNodo); // Reutilizo la funcion que hice antes
    }

    @Override
    public INodo<T> buscar(Comparable clave) {
        INodo<T> actual = primero;
        String claveLimpia = clave.toString()
                .replace("ZWNBSP", "")
                .trim();
        while (actual != null) {
            if (actual.getEtiqueta().compareTo(claveLimpia) == 0) {
                return actual;
            }
            System.out.println("Comparando:" + actual.getEtiqueta() + "con" + clave);
            System.out.println("Clase etiqueta: " + actual.getEtiqueta().getClass());
            System.out.println("Clase clave: " + clave.getClass());
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (primero == null) {
            return false;
        }

        if (primero.getEtiqueta().equals(clave)) {
            primero = primero.getSiguiente();
            cantidad--;
            return true;
        }

        INodo<T> anterior = primero;
        INodo<T> actual = primero.getSiguiente();

        while (actual != null) {
            if (actual.getEtiqueta().equals(clave)) {
                anterior.setSiguiente(actual.getSiguiente());
                cantidad--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        INodo<T> actual = primero;

        while (actual != null) {
            sb.append(actual.getEtiqueta()).append(" ");
            actual = actual.getSiguiente();
        }

        return sb.toString().trim();
    }

    @Override
    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        INodo<T> actual = primero;

        while (actual != null) {
            sb.append(actual.getEtiqueta());
            actual = actual.getSiguiente();
            if (actual != null) {
                sb.append(separador);
            }
        }

        return sb.toString();
    }

    @Override
    public int cantElementos() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public void setPrimero(INodo<T> unNodo) {
        this.primero = unNodo;
    }
    public T[] GetArray() {
        Object[] array = new Object[cantidad];
        INodo<T> actual = primero;
        for (int i = 0; i < array.length; i++) {
            array[i] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return (T[]) array;
    }
}
