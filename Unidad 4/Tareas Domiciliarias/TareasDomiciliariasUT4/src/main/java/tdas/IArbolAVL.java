package tdas;

import java.io.Serializable;
import java.util.List;

public interface IArbolAVL<T> {
    public boolean insertar(Comparable etiqueta, T unDato);

    public T buscar(Comparable unaEtiqueta);

    public void eliminar(Comparable unaEtiqueta);

    public List<T> preOrden();

    public List<T> inOrden();


    public List<T> postOrden();

    public boolean esVacio();

}
