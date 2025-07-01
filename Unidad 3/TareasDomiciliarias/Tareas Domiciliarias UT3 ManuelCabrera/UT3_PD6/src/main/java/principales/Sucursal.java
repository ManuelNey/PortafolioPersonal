package principales;

public class Sucursal {
    private String ciudad;
    public Sucursal(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) {
            throw new IllegalArgumentException("El ciudad no puede estar vacio");
        }
        this.ciudad = ciudad;
    }
    public String getCiudad() {
        return ciudad;
    }
}
