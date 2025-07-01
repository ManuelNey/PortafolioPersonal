package Ejercicio1;
import java.util.Random;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EnumTest {
    public enum day {
        Domingo, Lunes, Martes, Miércoles,
        Jueves, Viernes, Sábado
    }
    public static void main(String[] args) {
        day[] days = day.values();
        Random rand = new Random();
        day diaElegido = days[rand.nextInt(days.length)];
        switch (diaElegido) {
            case Lunes:
                System.out.println("Es lunes, tenés mala suerte");
                break;
            case Miércoles:
                System.out.println("Ni bien ni mal");
                break;
            case Viernes:
                System.out.println("Un esfuercito más, vamo arriba");
                break;
            case Sábado:
                System.out.println("Llegó el mejor día!!!");
                break;
            default:
                System.out.println("El dia es: " + diaElegido);
        }
    }
}