package PD5.PD7_UT5_ConTrieHashMap.PD7;

import tdas.ILista;

import java.util.LinkedList;


public class TTrieHashMapTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieHashMapTelefonos raiz;

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        if (raiz == null)
        {
            return null;
        }
        ILista<TAbonado> lista = raiz.buscarPorPaisYArea(pais, area);
        LinkedList<TAbonado> resultado = new LinkedList<>();
        if (lista == null)
        {
            return resultado;
        }
        for (TAbonado abonado : lista) {
            resultado.add(abonado);
        }
        return resultado;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (unAbonado == null)
        {
            return;
        }
        if (raiz == null)
        {
            raiz = new TNodoTrieHashMapTelefonos();
        }
        raiz.insertar(unAbonado);
    }

    public void buscarPorPrefijo(String prefijo, LinkedList<TAbonado> listaACargar)
    {
        if (prefijo == null || raiz == null || listaACargar == null)
        {
            return;
        }
        raiz.buscarTelefonos(prefijo, listaACargar);
    }
    
}
