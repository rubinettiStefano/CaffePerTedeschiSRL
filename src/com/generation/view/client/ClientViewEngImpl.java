package com.generation.view.client;

import java.util.List;

import com.generation.model.Client;
import com.generation.view.IView;

public class ClientViewEngImpl implements IView
{
   private static ClientViewEngImpl singleInstance ;

    public static ClientViewEngImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ClientViewEngImpl();


        return singleInstance;
    }

    private ClientViewEngImpl(){}

    @Override
    public String render(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Client e = (Client)o;
        res
        .append("Hi, i am a client with id: ")
        .append(e.getId())
        .append(", with Name: ")
        .append(e.getLegalName())
        .append(" , with Address: ")
        .append(e.getAddress()+", ")
        .append(e.getCity()+", ")
        .append(e.getCountry());

        return res.toString();
    }

    @Override
    public String render(List<Object> l)
    {
        StringBuilder res = new StringBuilder();

        for(Object e:l)
            res.append(render(e)).append("\n");

        return res.toString();
    }
}
