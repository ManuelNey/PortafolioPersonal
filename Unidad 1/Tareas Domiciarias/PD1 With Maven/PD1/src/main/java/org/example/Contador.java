package org.example;

public class Contador {
    private final int maxCont;
    private int incremento;
    private int contador;

    public Contador(int maxCont, int incremento, int contador) {
        this.maxCont = maxCont;
        this.incremento = incremento;
        this.contador = contador;
    }
    public void reset() {
        contador = 1;
    }
    public void DisplayContador() {
        while (contador <= maxCont) {
            System.out.println(contador);
            contador += incremento;
        }
    }

    public void DisplayContadorFor() {
        for (;contador <= maxCont; contador += incremento) {
            System.out.println(contador);
        }
    }
    public void DisplayContadorDoWhile() {
        do{
            System.out.println(contador);
            contador += incremento;
        } while (contador <= maxCont);
    }
    public static void main (String[] args) {
        Contador contador1 = new Contador(8, 1, 1);
        contador1.DisplayContador();
        contador1.reset();
        contador1.DisplayContadorFor();
        contador1.reset();
        contador1.DisplayContadorDoWhile();
    }
}
