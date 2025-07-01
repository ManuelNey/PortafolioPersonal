package org.example;

//Aqui se encuentra solo la clase alumno, el resto de métodos del ejercicio 4 se encuentran en MetodosExtras_Ej4
public class Alumno {
    private String nombre;
    public Alumno(String nombre) {
        this.nombre = nombre;
    }
    public String getNombreAdmiracion() {
        return nombre.concat("!");
    }
    public static void main (String[] args) { //a) Alumno no puede ser creado en un metodo estatico
        Alumno alumno = new Alumno("Pepe"); // Al momento de querer llamar el constructo el atributo de nombre queda en null, ya que nunca se le asigna nada
        System.out.println(alumno.getNombreAdmiracion()); // No se puede concatenar un null más cualquier string
    }
}