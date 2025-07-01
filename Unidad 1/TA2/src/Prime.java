class Prime {
    public  boolean isPrime(long n) {
        boolean prime = true;
        for (long i = 3; i <= Math.sqrt(n); i += 2)
            if (n % i == 0) {
                prime = false;
                break;
            }
        if (( n%2 !=0 && prime && n > 2) || n == 2) {
            return true;
        } else {
            return false;
        }
    }
    public long Program(long num)
    {
        Boolean isPrime = isPrime(num);
        long result = 0;
        long i = 0;
        if (!isPrime)
        {
            i++;
        }
        while (i < num)
        {
            result += i;
            i += 2;
        }
        return result;
    }
    public static void main(String[] args){
        Prime p = new Prime();
        System.out.println(p.isPrime(5));
        System.out.println(p.Program(941));
    }
}