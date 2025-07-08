package UT4_TA9;

import tdas.IElementoAVL;

import java.util.LinkedList;

public class Almacen implements IAlmacen {

    private String nombre;
    private String direccion;
    private String telefono;
    private int montoTotalStock;

    private TArbolBB<Producto> productos;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new TArbolBB<Producto>();
        montoTotalStock = 0;
    }
    public int getMontoTotalStock()
    {
        return montoTotalStock;
    }

    @Override
    public void insertarProducto(Producto unProducto) {
        if (unProducto != null ) {
            if (productos.buscar(unProducto.getEtiqueta()) == null) {
                TElementoAB<Producto> nuevoProducto = new TElementoAB(unProducto.getEtiqueta(), unProducto);
                productos.insertar(nuevoProducto);
                int valorAgregado = unProducto.getStock() * unProducto.getPrecio();
                montoTotalStock += valorAgregado;
            }
        }
    }

  

    @Override
    public String imprimirProductos() {
        Lista listaProductos = productos.inorden();
        listaProductos.imprimir();
        return listaProductos.imprimir("\n");
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        if (clave == null || cantidad == null || cantidad <= 0) {
            return false;
        }

        TElementoAB<Producto> nodoProducto = productos.buscar(clave);
        if (nodoProducto != null) {
            Producto producto = nodoProducto.getDatos();
            int precioProducto = producto.getPrecio();
            montoTotalStock += precioProducto * cantidad;
            producto.agergarStock(cantidad);
            return true;
        }

        return false;
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        if (clave == null || cantidad == null || cantidad <= 0) {
            return 0;
        }

        TElementoAB<Producto> nodoProducto = productos.buscar(clave);

        if (nodoProducto != null) {
            Producto producto = nodoProducto.getDatos();
            int precioProducto = producto.getPrecio();
            montoTotalStock -= precioProducto * cantidad;
            return producto.restarStock(cantidad);
        }

        return 0;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB productobuscado = productos.buscar(clave);
        if (productobuscado != null) {
            return (Producto) productobuscado.getDatos();
        }
        return null;
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        TElementoAB<Producto> nodoProducto = productos.buscar(clave);
        if (nodoProducto == null) {
            return false;
        }
        Producto producto = nodoProducto.getDatos();
        montoTotalStock -= producto.getStock() * producto.getPrecio();
        productos.eliminar(clave);
        return true;
    }


   
 
   


  

   

   

   

}
