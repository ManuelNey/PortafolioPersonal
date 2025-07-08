package UT2_PD2;

public class Exponencial {
    public float calcularExponencial(float base, float exponente) {
        if (exponente == 0) {
            return 1;
        }
        if (exponente < 0) {
            return 1 / calcularExponencial(base, -exponente );
        }
        else
            if (exponente - 1 >= 0) //Verifico que no me pase de los negativos si tengo un número con coma
            {
                return calcularExponencial(base, exponente -1) * base;
            }
            else
            {
                float diferencia = 1 - exponente;
                return calcularExponencial(base, exponente -diferencia) * (float) Math.pow(base, diferencia); //Con esto divido la parte entera del exponente de la parte con decimal
            }
    }
}
