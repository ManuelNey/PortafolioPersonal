package Ejercicio1;

public class Tablero {
    public static void main(String[] args) {
        mostrarTablero(7,7);
    }
    public static void mostrarTablero(int largo, int ancho) {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                System.out.print("# ");
            }
            System.out.println();
        }
    }
}
