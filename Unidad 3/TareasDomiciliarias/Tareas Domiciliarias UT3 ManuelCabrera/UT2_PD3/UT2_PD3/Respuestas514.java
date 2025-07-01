package UT2_PD3;

public class Respuestas514 {
    public static void main(String[] args) {
        System.out.println(Fragmento1(10));//Tiempo de ejecucion real=n
        System.out.println(Fragmento2(10));//Tiempo de ejecucion real=n**2
        System.out.println(Fragmento3(10));//Tiempo de ejecucion real=2N
        System.out.println(Fragmento4(10));//Tiempo de ejecucion real=N**3
        System.out.println(Fragmento5(10));//Tiempo de ejecucion real=N**2
        System.out.println(Fragmento6(10));//Tiempo de ejecucion real=N**3

    }

    public static int Fragmento1(int n){//Tiempo de ejecucion O(n)
        int suma=0;
        for(int i=0;i<n;i++){
            suma++;
        }
        return suma;
    }

    public static int Fragmento2(int n){//Tiempo de ejecucion O(n**2)
        int suma=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                suma++;
            }
        }
        return suma;
    }

    public static int Fragmento3(int n){//Tiempo de ejecucion O(2N)
        int suma=0;
        for(int i=0;i<n;i++){
            suma++;
        }
        for(int j=0;j<n;j++){
            suma++;
        }
        return suma;
    }

    public static int Fragmento4(int n){//Tiempo de ejecucion O(n**3)
        int suma=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n*n;j++){
                suma++;
            }
        }
        return suma;
    }

    public static int Fragmento5(int n){//Tiempo de ejecucion O(n**2)
        int suma=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                suma++;
            }
        }
        return suma;
    }

    public static int Fragmento6(int n){//Tiempo de ejecucion O(n**3)
        int suma=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<j;k++){
                    suma++;
                }
            }
        }
        return suma;
    }

}
