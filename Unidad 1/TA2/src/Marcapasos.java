public class Marcapasos {
    Short PresionSanguinea;
    Short Frecuenciacardiaca;
    Short NivelAzucarSangre;
    Long FuerzaMaxima;
    Float TiempoMinEntreLatidos;
    Double BateríaRestante;
    String CódigoDelFabricante;

    public Marcapasos() { //Constructor con los valores máximos permitidos por la clase
        this.PresionSanguinea = 250; // Ocupa 2 bytes
        this.Frecuenciacardiaca = 226; // Ocupa 2 bytes
        this.NivelAzucarSangre = 1000; // Ocupa 2 bytes
        this.FuerzaMaxima = 3000000000L; // Ocupa 8 bytes
        this.TiempoMinEntreLatidos = 100.0f; // Ocupa 4 bytes
        this.BateríaRestante = 100.0d; // Ocupa 8 bytes
        this.CódigoDelFabricante = "ABCD1234"; // Si el máximo de caracteres va a ser 8 y cada caracter en java ocupa 2 bytes aproximadamente entonces esta variable ocuparía máximo 16 bytes
        //Solo teniendo en cuenta estos atributos ocupa 42 bytes de memoria
    }
}