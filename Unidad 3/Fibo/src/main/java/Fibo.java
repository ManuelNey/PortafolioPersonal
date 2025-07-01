public class Fibo {
    public int fibbonaziPosicion(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibbonaziPosicion (n - 1) + fibbonaziPosicion (n - 2);
    }
}
