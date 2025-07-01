package UT3_TA2;

public interface IProducto {

    /**
     * Retorna el codigo del Producto.
     *
     * @return codigo del Producto.
     */
    public Comparable getCodProducto();

    /**
     * Retorna el precio unitario del Producto.
     *
     * @return precio del Producto.
     */
    public Integer getPrecioUnitario();

    public void setPrecioUnitario(Integer precio);

    /**
     * Retorna el stock del Producto.
     *
     * @return stock del Producto.
     */
    public Integer getStock();

    public Boolean agregarCantidadStock(Integer stock);

    public void restarCantidadStock(Integer stock);

    /**
     * Retorna la descripcion/nombre del Producto.
     *
     * @return descripci�n del Producto.
     */
    public String getDescripcion();

    public void setDescripcion(String nombre);

}
