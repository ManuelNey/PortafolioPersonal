package UT3_TA2;

import tdas.Lista;
import tdas.ILista;
import tdas.ListaOrd;
import utils.ManejadorArchivosGenerico;

import java.util.Arrays;


public class Almacen extends Lista implements IAlmacen {
    private Lista<IProducto> listaProductos;
    private int cantidadProductos;
    private int montoTotal;
    private String direccion;
    private String telefono;
    private String nombre;


    public Almacen(String direccion, String telefono, String nombre) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre = nombre;
        this.cantidadProductos = 0;
        this.montoTotal = 0;
        this.listaProductos = new Lista<>();
    }
    public String getDireccion() {return direccion;}
    public String getTelefono() {return telefono;}
    public String getNombre() {return nombre;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public Lista<IProducto> getListaProductos()
    {
        return listaProductos;
    }

    public boolean insertarProducto(IProducto producto) {
        if (producto == null){
            return false;
        }
        if(buscarPorCodigo(producto.getCodProducto()) != null){
            return false;
        }
        listaProductos.insertar(producto, producto.getCodProducto());
        cantidadProductos++;
        montoTotal += producto.getPrecioUnitario() * producto.getStock();
        return true;
    }

    public boolean eliminarProducto(Comparable clave) {

        if (clave == null)
        {
            return false;
        }
        return listaProductos.eliminar(clave);
    }
    public Boolean agregarStock(Comparable codProducto, Integer cantidad) {
        if (codProducto == null || cantidad == null || cantidad <= 0) {
            return false;
        }

        IProducto producto = buscarPorCodigo(codProducto);
        if (producto == null) {
            return false;
        }

        boolean fueAgregado = producto.agregarCantidadStock(cantidad);
        if (fueAgregado) { //Reviso si el producto fue agregado así se incrementar el monto
            montoTotal += producto.getPrecioUnitario() * cantidad;
        }
        return fueAgregado;
    }
    public boolean restarStock(Comparable comparable, Integer cantidad) {
        if (comparable == null || cantidad == null || cantidad <= 0) {
            return false;
        }
        IProducto producto = buscarPorCodigo(comparable);
        if (producto == null) {
            return false;
        }
        if (producto.getStock() < cantidad) {
            return false;
        }

        producto.restarCantidadStock(cantidad);
        montoTotal -= (producto.getPrecioUnitario() * cantidad);
        return true;
    }
    public IProducto buscarPorCodigo(Comparable codProducto) {
        if (codProducto == null)
        {
            return null;
        }
        return listaProductos.buscar(codProducto);
    }
    public String imprimirProductos() {
        if (listaProductos.esVacia())
        {
            return null;
        }
        return listaProductos.imprimir();
    }
    public String imprimirSeparador(String separador) {
        if (separador == null || separador.isEmpty() || listaProductos.esVacia())
        {
            return null;
        }
        return listaProductos.imprimir(separador);
    }
    public int obtenerStockPorCodigo(Comparable codProducto) {
        if (codProducto == null) {
            return -1;  //Uso el -1 para indicar que no se pudo hacer
        }
        IProducto producto = buscarPorCodigo(codProducto);
        if (producto != null) {
            return producto.getStock();
        }
        return -1;
    }

    public String listarOrdenadoPorNombre()
    {
        if (listaProductos == null || listaProductos.esVacia()) {
            return "No hay productos guardados";
        }
        Object[] arreglo = listaProductos.toArray(); //No puedo castear toda la lista de objetos a IProductos necesito pasarlos uno por uno
        IProducto[] producto = new IProducto[arreglo.length];
        for (int i = 0; i < arreglo.length; i++) {
            producto[i] = (IProducto) arreglo[i];
        }
        // Si no puedo usar Arrays.sort(producto); :
        ListaOrd<IProducto> listaOrd = new ListaOrd<>();
        for (int i = 0; i < producto.length; i++) {
            listaOrd.insertar(producto[i], producto[i].getDescripcion()); //Ahora las voy a ordenar por nombre.
        }

        StringBuilder sb = new StringBuilder();
        Object[] arregloOrdenado = listaOrd.toArray();
        for (Object objeto : arregloOrdenado) {
            IProducto p = (IProducto) objeto; //Aqui pasa lo mismo, necesito castearlo
            sb.append(p.getDescripcion()).append(" - Stock: ").append(p.getStock()).append("\n");
        }
        return sb.toString();
    }

    public IProducto buscarPorDescripcion(String nombre) {
        if (listaProductos == null || listaProductos.esVacia()) {
            return null;
        }
        Object[] arreglo = listaProductos.toArray();
        IProducto[] producto = new IProducto[arreglo.length];
        for (int i = 0; i < arreglo.length; i++) {
            producto[i] = (IProducto) arreglo[i];
        }
        for (int i = 0; i < producto.length; i++) {
            if (producto[i].getDescripcion().equals(nombre)) {
                return producto[i];
            }
        }
        return null;
    }


    public int ValorEconomicoStock(String ruta){
        ManejadorArchivosGenerico manejadorArchivosGenerico = new ManejadorArchivosGenerico();
        String[] lineas = manejadorArchivosGenerico.leerArchivo(ruta);
        int suma=0;
        for(int i=0; i<lineas.length; i++){
            String linea = lineas[i];
            String[] elementos=linea.split(",");
            Comparable codigoProducto = elementos[0];
            String descripcionDelProducto = elementos[1];
            int precioUnitario = Integer.parseInt(elementos[2]);
            int cantidadDeProductos = Integer.parseInt(elementos[3]);

            Producto producto = new Producto(codigoProducto, descripcionDelProducto, precioUnitario,
                    cantidadDeProductos);
            insertarProducto(producto);
            suma += producto.getPrecioUnitario() * producto.getStock();
        }
        return suma;
    }
    public int ReducirStockVenta(String ruta){
        ManejadorArchivosGenerico manejadorArchivosGenerico = new ManejadorArchivosGenerico();
        String[] lineas = manejadorArchivosGenerico.leerArchivo(ruta);
        int monto=0;
        for(int i=0; i<lineas.length; i++){
            String linea = lineas[i];
            String[] elementos=linea.split(",");
            Integer codigoProducto = Integer.parseInt(elementos[0]);
            int cantidadDeProductos = Integer.parseInt(elementos[3]);
            IProducto producto = buscarPorCodigo(codigoProducto);
            int cantidadDisponible = producto.getStock();
            if (cantidadDisponible < cantidadDeProductos) {
                cantidadDeProductos = cantidadDisponible;
            }
            restarStock(codigoProducto, cantidadDeProductos);
            monto += producto.getPrecioUnitario() * cantidadDeProductos;
        }
        return monto;
    }

    public int cantidadProductos() {
        return cantidadProductos;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

}
