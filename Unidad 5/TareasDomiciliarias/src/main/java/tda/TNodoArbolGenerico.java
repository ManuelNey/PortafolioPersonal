package tda;


public class TNodoArbolGenerico<T> {

    private Comparable etiqueta;
    private T dato;
    private TNodoArbolGenerico primerHijo;
    private TNodoArbolGenerico hermanoDerecho;

    public TNodoArbolGenerico(Comparable etiqueta, T dato) {
        this.etiqueta = etiqueta;
        this.dato = dato;
        this.primerHijo = null;
        this.hermanoDerecho = null;
    }

    public TNodoArbolGenerico(Comparable etiqueta) {
        this.etiqueta = etiqueta;
        this.dato = null;


    }

    public boolean insertar(String unaEtiqueta, T dato, String etiquetaPadre) {
        if (this.etiqueta.equals(etiquetaPadre)) {
            TNodoArbolGenerico nuevo = new TNodoArbolGenerico(unaEtiqueta, dato);
            if (this.primerHijo == null) {
                this.primerHijo = nuevo;
            } else {
                TNodoArbolGenerico actual = this.primerHijo;
                while (actual.hermanoDerecho != null) {
                    actual = actual.hermanoDerecho;
                }
                actual.hermanoDerecho = nuevo;
            }
            return true;
        }

        if (primerHijo != null) {
            boolean valor = primerHijo.insertar(unaEtiqueta, dato, etiquetaPadre);
            if (valor) {
                return true;
            }
        }

        if (this.hermanoDerecho != null) {
            return this.hermanoDerecho.insertar(unaEtiqueta, dato, etiquetaPadre);
        }

        return false;
    }

    public TNodoArbolGenerico buscar(String etiquetaBuscar) {
        if (this.etiqueta.equals(etiquetaBuscar)) {
            return this;
        }

        TNodoArbolGenerico nodo = null;
        if (this.primerHijo != null) {
            nodo = this.primerHijo.buscar(etiquetaBuscar);
        }

        if (nodo == null && this.hermanoDerecho != null) {
            nodo = this.hermanoDerecho.buscar(etiquetaBuscar);
        }

        return nodo;
    }

    public String listarIndentado(String identador) {
        StringBuilder texto = new StringBuilder(identador + this.etiqueta + "\n");
        if (this.primerHijo != null) {
            texto.append(this.primerHijo.listarIndentado(identador + "\t"));
        }
        if (this.hermanoDerecho != null) {
            texto.append(this.hermanoDerecho.listarIndentado(identador));
        }
        return texto.toString();
    }

}
