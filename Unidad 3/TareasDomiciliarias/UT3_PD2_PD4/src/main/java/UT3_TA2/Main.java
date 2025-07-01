package UT3_TA2;

import utils.ManejadorArchivosGenerico;


public class Main {
    public static void main(String[] args) {
        ManejadorArchivosGenerico manejadorArchivos = new ManejadorArchivosGenerico();
        Almacen almacen = new Almacen("un lugar", "911", "Almacen");
        System.out.println("Bienvenido al sistema de gestion de productos\n");
        int montoInicial = almacen.getMontoTotal();
        String[] archivo = manejadorArchivos.leerArchivo("src/main/java/UT3_TA2/altas.txt");
        for (int i = 0; i < archivo.length; i++) {
            String[] datos = archivo[i].trim().split(",");
            if (datos.length < 4) {
                System.out.println("Línea con formato incorrecto: " + datos);
                continue;
            }
            int codigoProducto = Integer.parseInt(datos[0]);
            String descripcionDelProducto = datos[1];
            int precioUnitario = Integer.parseInt(datos[2]);
            int cantidadDeProductos = Integer.parseInt(datos[3]);

            Producto producto = new Producto(codigoProducto, descripcionDelProducto, precioUnitario,
                    cantidadDeProductos);
            almacen.insertarProducto(producto);
        }
        int montoNuevo = almacen.getMontoTotal() - montoInicial;
        System.out.printf("Se ha incrementado el monto en: %d\n", montoNuevo);  // Usamos printf para formatear la salida
        String[] lineas = manejadorArchivos.leerArchivo("src/main/java/UT3_TA2/ventas.txt");
        int monto=0;
        for(int i=0; i<lineas.length; i++){
            String linea = lineas[i];
            String[] elementos=linea.split(",");
            if (elementos.length < 2) {
                System.out.println("Línea con formato incorrecto " + linea); //Se tiene que lanzar una excepcion por mal formato
                continue;
            }
            Integer codigoProducto = Integer.parseInt(elementos[0]);
            int cantidadDeProductos = Integer.parseInt(elementos[1]);
            IProducto producto = almacen.buscarPorCodigo(codigoProducto);
            if (producto == null) {
                System.err.println("El producto no existe con el codigo: " + codigoProducto);
                continue; //va al sigueinte proceso del for y ya no busca el producto
            }
            int cantidadDisponible = producto.getStock();
            if (cantidadDisponible < cantidadDeProductos) {
                cantidadDeProductos = cantidadDisponible;
            }
            almacen.restarStock(codigoProducto, cantidadDeProductos);
            monto += producto.getPrecioUnitario() * cantidadDeProductos;
        }
        System.out.println("Se ha reducido el monto en: " + monto);
        System.out.println(almacen.listarOrdenadoPorNombre());
        IProducto producto = almacen.buscarPorDescripcion("ADES� NARANJA�");
        if (producto != null) {
            System.out.println(producto.getCodProducto());
        } else {
            System.out.println("Producto no encontrado por descripción.");
        }
    }

}
