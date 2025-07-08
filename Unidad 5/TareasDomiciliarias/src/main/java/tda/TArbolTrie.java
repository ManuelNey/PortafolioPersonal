package tda;

import utils.ManejadorArchivosGenerico;

import java.util.LinkedList;

public class TArbolTrie  {

    private TNodoTrie raiz;

    public void insertar(String palabra) {
        String p = normalizar(palabra);
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(p);
    }

    public void insertarConPaginas(String palabra, Integer paginas)
    {
        String p = normalizar(palabra);
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(p, paginas);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    public int buscarPalabra(String palabra) {
        palabra = normalizar(palabra);
        if (raiz == null) {
            return 0;
        }
        return raiz.buscar(palabra);
    }

    public LinkedList<Integer> buscarPaginas(String palabra) {
        palabra = normalizar(palabra);
        if (raiz == null) {
            return null;
        }
        return raiz.buscarPaginas(palabra);
    }

    public LinkedList<String> predecir(String prefijo) {
        prefijo = normalizar(prefijo);
        if (raiz == null || prefijo == null) { // caso especial:prefijo vacio?
            return null;
        }
        LinkedList<String> palabras = new LinkedList<>();

        raiz.predecir(prefijo, palabras);

        return palabras;
    }

    protected static String normalizar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }

        palabra = palabra.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);

            switch (letra) {
                case 'á':
                    sb.append('a');
                    break;
                case 'é':
                    sb.append('e');
                    break;
                case 'í':
                    sb.append('i');
                    break;
                case 'ó':
                    sb.append('o');
                    break;
                case 'ú':
                    sb.append('u');
                    break;
                case 'ñ':
                    sb.append('n');
                    break;
                default:
                    if ((letra >= 'a' && letra <= 'z') || letra == ' ') {
                        sb.append(letra);
                    }
                    break;
            }
        }

        return sb.toString();
    }
    public void indizarLibro(String rutaArchivo)
    {
        ManejadorArchivosGenerico manejadorArchivosGenerico = new ManejadorArchivosGenerico();
        String[] lineas = manejadorArchivosGenerico.leerArchivo(rutaArchivo);
        int cantidadDePaginas = (int) Math.ceil((double) lineas.length / 50);
        int lineaActual = 0;
        int paginaActual = 1;
        for (String linea: lineas)
        {
            lineaActual++;
            String[] palabras = normalizar(linea).split(" ");
            for (String palabra: palabras)
            {
                if (!palabra.isEmpty())
                {

                    System.out.println("intenté agregar: " + palabra+".    " + "En la pagina:"+paginaActual + "en la linea "+lineaActual);
                    this.insertarConPaginas(palabra, paginaActual);
                }
            }
            if (lineaActual > 50)
            {
                paginaActual++;
                lineaActual = 0;
            }
        }
    }
    public void imprimirIndice()
    {
        if (raiz == null)
        {
            return;
        }
        raiz.listarPalabras(new StringBuilder());
    }
}
