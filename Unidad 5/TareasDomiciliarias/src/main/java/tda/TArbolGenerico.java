package tda;

public class TArbolGenerico<T> {

    private TNodoArbolGenerico raiz;

    public boolean insertar(String unaEtiqueta, Object dato, String etiquetaPadre) {
        if (unaEtiqueta == null || etiquetaPadre == null) {
            return false;
        }

        if (etiquetaPadre.equals("")) {
            if (raiz == null) {
                raiz = new TNodoArbolGenerico(unaEtiqueta, dato);
                return true;
            }
            return false;
        }

        if (raiz == null) return false;

        return raiz.insertar(unaEtiqueta, dato, etiquetaPadre);
    }

    public TNodoArbolGenerico buscar(String etiquetaBuscar) {
        if (raiz == null) return null;
        return raiz.buscar(etiquetaBuscar);
    }

    public String listarIndentado() {
        if (raiz != null) {
            return raiz.listarIndentado("");
        }
        return "El arbol no tiene elementos";
    }
}
