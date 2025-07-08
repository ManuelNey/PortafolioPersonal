package UT4_TA9;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Almacen macroMercado = new Almacen("MacroMercado");


        // cargar los productos desde el archivo "altasprueba.txt"

        String ruta = "src/main/java/UT4_TA9/altasPrueba.txt";
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(ruta);
        for (int i = 0; i < lineas.length; i++)
        {
            String[] linea = lineas[i].split(",");
            Comparable codigo = linea[0];
            String descripcion = linea[1];
            int precio = Integer.parseInt(linea[2]);
            int cantidad = Integer.parseInt(linea[3]);
            Producto nuevoProducto = new Producto(codigo, descripcion);
            nuevoProducto.setStock(cantidad);
            nuevoProducto.setPrecio(precio);
            Producto producto = macroMercado.buscarPorCodigo(codigo);
            if ( producto != null)
            {
               macroMercado.agregarStock(codigo, cantidad);
            }
            else {
                macroMercado.insertarProducto(nuevoProducto);
            }
        }
        // listar los productos ordenados por codigo, junto con su cantidad existente
        System.out.println(macroMercado.imprimirProductos());
        //emitir el valor del stock
        System.out.println(macroMercado.getMontoTotalStock());
        int montoTotal = macroMercado.getMontoTotalStock();

        String rutaVentas = "src/main/java/UT4_TA9/ventasPrueba.txt";
        String[] lineasVentas = ManejadorArchivosGenerico.leerArchivo(rutaVentas);

        // simular las ventas a partir del archivo "ventaspruebas.txt"
        for (String linea : lineasVentas)
        {
            String[] partes = linea.split(",");
            Comparable codigo = partes[0];
            int cantidadVendida = Integer.parseInt(partes[1]);

            Producto producto = macroMercado.buscarPorCodigo(codigo);
            if (producto != null && producto.getStock() >= cantidadVendida)
            {
                macroMercado.restarStock(codigo, cantidadVendida);

            }
            else
            {
                System.out.println("No se pudo vender " + cantidadVendida + " unidades del producto " + codigo);
            }
        }

        int montoTotalVendido = montoTotal - macroMercado.getMontoTotalStock();
        int montoConVentas = macroMercado.getMontoTotalStock();
        System.out.println(montoTotalVendido);
      // simular la eliminación de productos a partir del archivo "elimPrueba.txt"
        String rutaEliminar = "src/main/java/UT4_TA9/elimPrueba.txt";
        String[] codigosEliminar = ManejadorArchivosGenerico.leerArchivo(rutaEliminar);

        for (String codigoStr : codigosEliminar)
        {
            Comparable codigo = codigoStr.trim();
            Producto producto = macroMercado.buscarPorCodigo(codigo);
            if (producto != null)
            {
                macroMercado.eliminarProducto(codigo);
            }
            else
            {
                System.out.println("No se encontró el producto con código " + codigo + " para eliminar.");
            }
        }
        System.out.println(macroMercado.imprimirProductos());
      // listar los productos ordenados por codigo, junto con su cantidad existente
        int DiferenciaEliminar = macroMercado.getMontoTotalStock() - montoConVentas;
        System.out.println(montoTotalVendido);
        System.out.println(macroMercado.getMontoTotalStock());
    }
}

