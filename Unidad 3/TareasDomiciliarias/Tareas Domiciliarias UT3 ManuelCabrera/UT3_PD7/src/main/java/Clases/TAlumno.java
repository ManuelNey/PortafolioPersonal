package Clases;

public class TAlumno {
    private int cedula;
    private String nombre;
    private String apellido;

    public TAlumno(int cedula, String nombre, String apellido) {
        if (cedula > 9999 || cedula < 0) {
            throw new IllegalArgumentException("Cedula no válida");
        }
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }
    

}
