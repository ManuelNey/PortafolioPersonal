package Test_UT2_PD2;

import UT4_TA9.Almacen;
import UT4_TA9.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlmacen {
    @Test
    public void testAgregarNuevoProducto() {
        Almacen almacen = new Almacen("Don Juan");
        Producto p = new Producto("milk","Leche de almendras");
        p.setStock(10);
        almacen.insertarProducto(p);
        Producto buscado = almacen.buscarPorCodigo("milk");
        assertNotNull(buscado);
        assertEquals(10, buscado.getStock());
        assertEquals(100, buscado.getPrecio());
    }
    @Test
    public void testAgregarStockExistente() {
        Almacen almacen = new Almacen("Tata");
        Producto p = new Producto(10, "Coca");
        p.setStock(10);
        almacen.insertarProducto(p);
        Producto repetido = new Producto(10, "Coca");
        repetido.setStock(5);
        almacen.agregarStock(10, 5);
        assertEquals(15, almacen.buscarPorCodigo(10).getStock());
    }
    @Test
    public void testVenderProducto() {
        Almacen almacen = new Almacen("Papas");
        Producto p = new Producto("007", "Helado");
        p.setStock(50);
        p.setPrecio(200);
        almacen.insertarProducto(p);
        int valoralmacenActual = almacen.getMontoTotalStock();

        almacen.restarStock("007", 10);
        int valoralmacenAlVender = valoralmacenActual - almacen.getMontoTotalStock();
        assertEquals(2000, valoralmacenAlVender);
        assertEquals(40, almacen.buscarPorCodigo("007").getStock());
    }
    @Test
    public void testEliminarProducto() {
        Almacen almacen = new Almacen("Don Pablo");
        Producto p = new Producto(12, "Papitas");
        p.setStock(20);
        p.setPrecio(100);
        almacen.insertarProducto(p);

        almacen.eliminarProducto(12);
        int montoTotal = almacen.getMontoTotalStock();
        assertEquals(0, montoTotal); //Se caducan por ejemplo todos las papitas
        assertNull(almacen.buscarPorCodigo(12));
    }
}
