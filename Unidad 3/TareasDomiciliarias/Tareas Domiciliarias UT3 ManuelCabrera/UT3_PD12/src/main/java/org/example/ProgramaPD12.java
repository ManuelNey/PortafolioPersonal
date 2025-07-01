package org.example;

import tdas.Conjunto;
import tdas.IConjunto;

import java.io.IOException;

public class ProgramaPD12 {

    public static void main(String[] args) throws IOException {
        ManejadorArchivos manejadorArchivos = new ManejadorArchivos();

        // instanciar curso BasicoIng...
        Conjunto<Alumno> BasicoIng = new Conjunto<>();
        // cargar alumnos del curso BasicoIng desde el archivo “basico-ing.txt”
        String[] lineasIng = manejadorArchivos.leerArchivo("src/main/java/org/example/basico-ing.txt");
        for (String linea : lineasIng) {
            String[] lineaArgumentos = linea.split(",");
            String codAlumno = lineaArgumentos[0];
            Integer codAlumnoValor = Integer.valueOf(codAlumno);
            String nombre = lineaArgumentos[1];
            BasicoIng.insertar(new Alumno(codAlumnoValor, nombre), codAlumnoValor);
        }


        // instanciar curso BasicoEmp...
        Conjunto<Alumno> BasicoEmp = new Conjunto<>();
        String[] lineasEmp = manejadorArchivos.leerArchivo("src/main/java/org/example/basico-emp.txt");
        for (String linea : lineasEmp) {
            String[] lineaArgumentos = linea.split(",");
            String codAlumno = lineaArgumentos[0];
            Integer codAlumnoValor = Integer.valueOf(codAlumno);
            String nombre = lineaArgumentos[1];
            BasicoEmp.insertar(new Alumno(codAlumnoValor, nombre), codAlumnoValor);
        }
        // cargar alumnos del curso BasicoEmp desde el archivo “basico-emp.txt”

        // generar el curso "integrador101" con los alumnos que están en condiciones de cursarlo
        IConjunto<Alumno> integrador101 = BasicoEmp.union(BasicoIng);
        // guardar en un archivo "integrador101.txt"  - IDEALMENTE ordenados por código de alumno -
        Alumno[] Alumnos = integrador101.toArray(Alumno.class); //Digo de que tipo son mis datos
        String[] lineas = new String[Alumnos.length];
        for (int i = 0; i < Alumnos.length; i++) {
            String linea = Alumnos[i].getCodigo() +","+ Alumnos[i].getNombre();
            lineas[i] = linea;
        }
        manejadorArchivos.escribirArchivo("src/main/java/org/example/Integrador101.txt", lineas);
        // generar el curso "exigente102" con los alumnos que están en condiciones de cursarlo 
        IConjunto<Alumno> exigente102 = BasicoEmp.interseccion(BasicoIng);
        // guardar en un archivo "integrador101.txt"  - IDEALMENTE ordenados por código de alumno -
        Alumno[] AlumnosExi = exigente102.toArray(Alumno.class); //Digo de que tipo son mis datos
        String[] lineasExi = new String[AlumnosExi.length];
        for (int i = 0; i < AlumnosExi.length; i++) {
            String linea = AlumnosExi[i].getCodigo() +","+ AlumnosExi[i].getNombre();
            lineasExi[i] = linea;
        }
        manejadorArchivos.escribirArchivo("src/main/java/org/example/Exigente102.txt", lineasExi);
        // guardar en un archivo "exigente102.txt" - IDEALMENTE ordenados por código de alumno -
    }

}
