package UT3_TA2;

public class Producto implements IProducto{
    private Comparable codigoProducto;
    private String descripcionDelProducto;
    private int precioUnitario;
    private int cantidadDeProductos;

    public Producto(Comparable codigoProducto, String descripcionDelProducto, int precioUnitario, int cantidadDeProductos) {
        if (codigoProducto == null || descripcionDelProducto == null)
        {
            throw new IllegalArgumentException("Debes ingresar el codigo del producto y su descripción");
        }
        if (codigoProducto.toString().length() > 20) {
            throw new IllegalArgumentException("El código del producto no puede tener más de 20 caracteres.");
        }

        if (descripcionDelProducto.length() > 50) { //Lo extendí a 50 porque hay productos de los txt que superan esta cantidad
            throw new IllegalArgumentException("La descripción del producto no puede tener más de 30 caracteres.");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        if (cantidadDeProductos < 0) {
            throw new IllegalArgumentException("El cantidad de productos no puede ser negativo.");
        }
        this.codigoProducto = codigoProducto;
        this.descripcionDelProducto = descripcionDelProducto;
        this.precioUnitario = precioUnitario;
        this.cantidadDeProductos = cantidadDeProductos;
    }

    public Comparable getCodProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Comparable codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcionDelProducto;
    }

    public void setDescripcion(String descripcionDelProducto) {
        this.descripcionDelProducto = descripcionDelProducto;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precio) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getStock() {
        return cantidadDeProductos;
    }
    public Boolean agregarCantidadStock(Integer stock) {
        if (stock == null || stock < 0) {
            return false;
        }
        this.cantidadDeProductos += stock;
        return true;
    }
    public void restarCantidadStock(Integer stock) {
        if (stock == null || stock < 0) { // Sirve para evitar casos donde el stock sea negativo
            return;
        }
        if (cantidadDeProductos < stock) {
            cantidadDeProductos = 0;
            return;
        }
        this.cantidadDeProductos -= stock;
    }
    public void setCantidadDeProductos(int cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
    }

}
