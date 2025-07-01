package tdas;

public interface IConjunto<T> extends ILista<T>{
    public IConjunto<T> union(IConjunto<T> otroConjunto);
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto);
    public IConjunto<T> unionOrdenNALa2(IConjunto<T> otroConjunto);
    public IConjunto<T> interseccionOrdenNALa2(IConjunto<T> otroConjunto);
}
