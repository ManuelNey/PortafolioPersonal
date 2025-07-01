package tdas;

public class Pila<T>  {


    private class Nodo<T> {

        private T dato;
        private Nodo<T> abajo = null;

        public Nodo(T dato ) {
            this.dato = dato;
        }
    }

    private Nodo<T> tope;
    private int cantidadElemento = 0;

    public Pila() {
        cantidadElemento = 0;
    }

    public void push(T dato) {

        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.abajo = tope;
        tope = nuevoNodo;
        cantidadElemento++;
    }

    public T pop() {
        if (tope == null){
            return null;
        }
        Nodo<T> valor = tope;
        tope = tope.abajo;
        cantidadElemento--;
        return valor.dato;
    }

    public int cantElementos() {
        return cantidadElemento;
    }

    public boolean esVacia() {
        return tope == null; //Si el primero está en null la lista está vacío
    }
    public String peek() {
        if (esVacia()) {return null;}
        return tope.dato.toString();
    }
}
