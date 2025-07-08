package org.example.TDA;

import java.util.LinkedList;

public class TCaminos {
    
    private LinkedList<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public String imprimirCaminos(){
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos){
            sb.append(camino.imprimirEtiquetas()+"\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public LinkedList<TCamino> getCaminos() {
        return caminos;
    }
    public boolean agregarCaminos(TCamino camino)
    {
        if (camino == null)
        {
            return false;
        }
        return caminos.add(camino);
    }
    
}
