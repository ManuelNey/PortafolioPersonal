public class NumberHolder {
    public int anInt;
    public float aFloat;
    public static void main (String[] args) {
        NumberHolder numberHolder = new NumberHolder();
        numberHolder.anInt = 10;
        numberHolder.aFloat = 30.08f;
        System.out.println("numberHolder.anInt: " + numberHolder.anInt);
        System.out.println("numberHolder.aFloat: " + numberHolder.aFloat);
    }
}