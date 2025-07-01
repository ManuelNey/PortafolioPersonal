package UT2_PD3;

public class Respuesta515 {
    public static void main(String[] args) {
        System.out.println(Fragmento1(10));//Tiempo en ejecucion real=O(n**4)

    }
    public static int Fragmento1(int n){//O(n**4)
        int suma=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<i*i;j++){
                if(j%i==0){
                    for(int k=0;k<j;k++){
                        suma++;
                    }
                }
            }
        }
        return suma;
    }
}
