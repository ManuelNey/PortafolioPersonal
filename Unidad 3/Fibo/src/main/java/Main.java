//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        for (int i = 0; i < 10; i++) {
            System.out.println(fibo.fibbonaziPosicion(i));
        }
    }
}