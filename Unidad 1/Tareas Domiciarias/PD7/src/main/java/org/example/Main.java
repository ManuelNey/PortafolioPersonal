package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String s = "1000";
        while (s != "1000") {
            s += "0";
            System.out.println("Aun no terminé");
        }
        //En Java, el operador == lo que hace es comparar las referencias de memoria y no el contenido de los objetos.
        //Por esto al sumarle a s "0" se crea una nueva referencia con su respectivo espacio de memoria (ya que los strings son inmutables)
        // por eso se tiene un nuevo valor pero que nunca va a tener la misma referencia que el "1000" del while
        //Si queremos comparar valores de strings en java tendríamos que usar el metodo equals
        // En C# el operador == con los string sí compara el contenido que poseen estas y no las referencias:
    }
}