

public class Factorial {
    public static int factorial(int num){
        int i = 1;
        int factor = 1;
        for (i = 1; i <= num; i++){
            factor *= i;
        }
        return factor;
    }
    public static void main(String[] args) {
        int N = 3;
        System.out.println(factorial(N));
    }


}