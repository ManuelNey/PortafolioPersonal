package PD7;

import tda.ILista;
import tda.ListaOrdenada;

import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private TNodoTrieTelefonos[] hijos;
    private TAbonado dueno;
    private String numero;
    private boolean isValid;

    public TNodoTrieTelefonos()
    {
        hijos = new TNodoTrieTelefonos[10];
        isValid = false;
    }
    public ILista<TAbonado> buscarPorPaisYArea(String pais, String area)
    {
        TNodoTrieTelefonos nodo = this;
        for (int i = 0;i < 3; i++)
        {
            int indice = pais.charAt(i) - 48;
            if (nodo.hijos[indice] == null)
            {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        for (int j = 0; j< 2; j++)
        {
            int indice = area.charAt(j) -48;
            if (nodo.hijos[indice] == null)
            {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo.recorrerHijos();
    }
    private ILista<TAbonado> recorrerHijos()
    {
        ILista<TAbonado> lista = new ListaOrdenada<>();
        if (this.isValid)
        {
            lista.insertar(dueno,dueno.getNombre());
        }
        TNodoTrieTelefonos nodo = this;
        for (int i = 0; i < 10; i++)
        {
            if (nodo.hijos[i] != null)
            {
                ILista listaDelHijo = (nodo.hijos[i].recorrerHijos());
                for (Object abonado : listaDelHijo) {
                    TAbonado abonadoCasteado = (TAbonado) abonado;
                    lista.insertar(abonadoCasteado, abonadoCasteado.getNombre());
                }
            }
        }
        return lista;
    }
    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieTelefonos nodo = this;
        for (int i = 0; i < primerosDigitos.length(); i++)
        {
            int indice = primerosDigitos.charAt(i) - 48;
            if (nodo.hijos[indice] == null)
            {
                return;
            }
            nodo = nodo.hijos[indice];
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
        TNodoTrieTelefonos nodo = this;
        for (int i = 0; i < hijos.length; i++)
        {
            int indice = unAbonado.getTelefono().charAt(i) - 48;
            if (nodo.hijos[indice] == null)
            {
                nodo.hijos[indice] = new TNodoTrieTelefonos();
                nodo.hijos[indice].numero = String.valueOf(indice);
            }
            nodo = nodo.hijos[indice];
        }
        nodo.isValid = true;
        nodo.dueno = unAbonado;
    }

    
}
