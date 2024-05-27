package com.generation.view.client;

import java.util.List;

import com.generation.model.Client;
import com.generation.view.IView;

public class ClientViewItaImpl implements IView
{
    private static ClientViewItaImpl singleInstance ;

    public static ClientViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ClientViewItaImpl();


        return singleInstance;
    }

    private ClientViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Client e = (Client)o;
        res
        .append("Ciao sono un cliente con id: ")
        .append(e.getId())
        .append(", con Nome: ")
        .append(e.getLegalName())
        .append(" , con indirizzo: ")
        .append(e.getAddress()+", ")
        .append(e.getCity()+", ")
        .append(e.getCountry());

        return res.toString();
    }

    @Override
    public String renderAll(List<Object> l)
    {
        StringBuilder res = new StringBuilder();

        for(Object e:l)
            res.append(renderOne(e)).append("\n");

        return res.toString();
    }
}
