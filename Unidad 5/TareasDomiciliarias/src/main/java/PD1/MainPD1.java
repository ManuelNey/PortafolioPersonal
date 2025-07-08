package PD1;

import tda.TArbolGenerico;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainPD1 {
    public static void main(String[] args) {
        TArbolGenerico arbol = new TArbolGenerico();

        arbol.insertar("Rectoría", new PartesFacultad("Rectoría"), "");
        arbol.insertar("Vicerrectoría Del Medio Universitario", new PartesFacultad("Vicerrectoría del Medio Universitario"), "Rectoría");
        arbol.insertar("Vicerrectoría Académica", new PartesFacultad("Vicerrectoría Académica"), "Rectoría");
        arbol.insertar("Vicerrectoría Administrativa", new PartesFacultad("Vicerrectoría Administrativa"), "Rectoría");
        arbol.insertar("Facultad De Ciencias Empresariales", new PartesFacultad("Facultad de Ciencias Empresariales"), "Vicerrectoría Académica");
        arbol.insertar("Facultad De Ciencias Humanas", new PartesFacultad("Facultad de Ciencias Humanas"), "Vicerrectoría Académica");
        arbol.insertar("Facultad De Ingeniería Y Tecnologías", new PartesFacultad("Facultad de Ingeniería y Tecnologías"), "Vicerrectoría Académica");
        arbol.insertar("Facultad De Psicología", new PartesFacultad("Facultad de Psicología"), "Vicerrectoría Académica");
        arbol.insertar("Departamento De Informática Y Ciencias De La Computación", new PartesFacultad("Departamento de Informática y Ciencias de la Computación"), "Facultad De Ingeniería Y Tecnologías");
        arbol.insertar("Departamento De Ingeniería Eléctrica", new PartesFacultad("Departamento de Ingeniería Eléctrica"), "Facultad De Ingeniería Y Tecnologías");
        arbol.insertar("Departamento De Matemáticas", new PartesFacultad("Departamento de Matemáticas"), "Facultad De Ingeniería Y Tecnologías");

        System.out.println(arbol.listarIndentado());
    }
}