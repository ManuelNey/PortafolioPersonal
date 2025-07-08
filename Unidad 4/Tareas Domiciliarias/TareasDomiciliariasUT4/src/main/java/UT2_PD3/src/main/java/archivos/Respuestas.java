package archivos;
public class Respuestas {

    public static void main(String[] args) {
        int n = 5000;
        int suma;

        // Fragmento #1
        suma = 0;
        for (int i = 0; i < n; i++) {
            suma++; // O(N)
        }
        System.out.println("suma = " + suma); //De orden O(N)

        // Fragmento #2
        suma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                suma++; // O(N^2)
            }
        }
        System.out.println("suma = " + suma); //De orden O(N**2)

        // Fragmento #3
        suma = 0;
        for (int i = 0; i < n; i++) {
            suma++; // Primer bucle
        }
        for (int j = 0; j < n; j++) {
            suma++; // Segundo bucle
        }
        System.out.println("suma = " + suma); //Es de orden O(N) ya que O(N+N) sigue siendo orden N

        // Fragmento #4
        suma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * n; j++) {
                suma++;
            }
        }
        System.out.println("suma = " + suma); //Tiene un for de N anidado a uno de N**2 es de O(N**3)

        // Fragmento #5
        suma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { //Depende del anterior, siendo los números triangulares, si es n=5 suma 0+1+2+3+4
                suma++;
            }
        }
        System.out.println("suma = " + suma); //Es de orden O(n**2) aunque tiene menor que el for anidado en otro for

        // Fragmento #6
        suma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * n; j++) {
                for (int k = 0; k < j; k++) {
                    suma++; // es un anidado de N**2 en un N**2 ya que depende del N**2 Y está anidado en un N. Es de orden O(N**5)
                }
            }
        }
        System.out.println("suma = " + suma);
        suma = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i * i; j++) {
                if (j % i == 0) {
                    for (int k = 0; k < j; k++) {
                        suma++;
                    }
                }
            }
        }
        System.out.println("suma = " + suma); //Termina siendo un O(n**4) pero podría llegar a crecer como un O(n**5) si el número es demasiado grande
    }

}
