package Ejercicio2;

import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lectorarchivos {
    public static void ObtenerLineas(String rutaArchivo) {
        ArrayList<String> linhas = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                linhas.add(lineaActual);
                lineaActual = br.readLine();
            }
            String string [] = new String[3];
            string[0] = linhas.get(0);
            string[1] = linhas.get(1);
            string[2] = linhas.get(2);
            int entero = Integer.parseInt(string[0]);
            float puntoFlotante = Float.parseFloat(string[1]);
            String stringleido = string[2];
            Float resultado1 = entero + puntoFlotante;
            int resultado2 = (int)puntoFlotante / entero ;
            float resto = Math.round((puntoFlotante % entero) * 100f) / 100f; //Utilizo esto para redondear a solo 2 cifras después de la coma
            System.out.println("El entero leído es: " + stringleido);
            System.out.println("El numero de punto flotante es: " + puntoFlotante);
            System.out.println("El string leído es: " + stringleido);
            System.out.println("El entero es: " + entero);
            System.out.println("¡Hola "+ stringleido +"! La suma de "+ entero + " y " + puntoFlotante+ " es " + resultado1);
            System.out.println("La división entera de "+ puntoFlotante + " y " + entero+ " es " + resultado2 + " su resto es "+ resto);

        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();

        }
        catch (NullPointerException e) {
            System.out.println("No se tiene la cantidad de lineas necesarias");
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        }
        finally {
            System.out.println("La ejecución ha terminado");
        }
    }
    public static void leerEntradaStdin(){
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduzca el radio de su circulo ");
        String radio = lector.nextLine();
        double rad = Double.parseDouble(radio); //Uso un double para tener la mayor precisión posible
        double area = Math.PI *rad * rad;
        double perimetro = 2 * Math.PI * rad;
        System.out.println("El radio es: " + radio);
        System.out.println("El perimetro es: " + perimetro);
        System.out.println("El area es: " + area);
    };
    public static void main(String[] args) {
        ObtenerLineas("src/main/java/Ejercicio2/entrada.txt");
        leerEntradaStdin();
    }
}
