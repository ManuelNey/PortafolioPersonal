package PD5.PD7_UT5_ConTrieHashMap.PD7;


import tdas.ILista;
import tdas.ListaOrdenada;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMapTelefonos implements INodoTrieTelefonos {

    private HashMap<Character, TNodoTrieHashMapTelefonos> hijos;
    private TAbonado dueno;
    private String numero;
    private boolean isValid;

    public TNodoTrieHashMapTelefonos()
    {
        hijos = new HashMap<>();
        isValid = false;
    }
    public ILista<TAbonado> buscarPorPaisYArea(String pais, String area)
    {
        TNodoTrieHashMapTelefonos nodo = this;
        for (int i = 0;i < 3; i++)
        {
            char digito = pais.charAt(i);
            if (!nodo.hijos.containsKey(digito))
            {
                return null;
            }
            nodo = nodo.hijos.get(digito);
        }
        for (int j = 0; j< 2; j++)
        {
            char digito = area.charAt(j);
            if (!nodo.hijos.containsKey(digito))
            {
                return null;
            }
            nodo = nodo.hijos.get(digito);
        }
        return nodo.recorrerHijos();
    }
    private ILista<TAbonado> recorrerHijos()
    {
        ILista<TAbonado> lista = new ListaOrdenada<>();
        if (this.isValid  && dueno != null)
        {
            lista.insertar(dueno,dueno.getNombre());
        }
        for (TNodoTrieHashMapTelefonos hijo : hijos.values()) {
            ILista<TAbonado> listaDelHijo = hijo.recorrerHijos();
            for (Object abonado : listaDelHijo) {
                TAbonado abonadoCasteado = (TAbonado) abonado;
                lista.insertar(abonadoCasteado, abonadoCasteado.getNombre());
            }
        }
        return lista;
    }
    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieHashMapTelefonos nodo = this;
        for (int i = 0; i < primerosDigitos.length(); i++)
        {
            char letra = primerosDigitos.charAt(i);
            if (!nodo.hijos.containsKey(letra))
            {
                return;
            }
            nodo = nodo.hijos.get(letra);
        }
        ILista listaDeLosHijos = (nodo.recorrerHijos());
        for (Object abonado : listaDeLosHijos) {
            TAbonado abonadoCasteado = (TAbonado) abonado;
            abonados.add(abonadoCasteado);
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (unAbonado.getTelefono().length() != 11)
        {
            System.out.println("Numero de largo equivocado");
            return;
        }
        String numerosValidos = "0123456789";
        for (int k = 0; k < unAbonado.getTelefono().length(); k++) {
            char digito = unAbonado.getTelefono().charAt(k);
            if (!numerosValidos.contains(String.valueOf(digito)))
            {
                return;
            }
        }
        TNodoTrieHashMapTelefonos nodo = this;
        for (int i = 0; i < unAbonado.getTelefono().length(); i++) {
            char letra = unAbonado.getTelefono().charAt(i);
            if (!nodo.hijos.containsKey(letra)) {
                nodo.hijos.put(letra, new TNodoTrieHashMapTelefonos());
            }
            nodo = nodo.hijos.get(letra);
        }
        nodo.isValid = true;
        nodo.dueno = unAbonado;
    }

}
