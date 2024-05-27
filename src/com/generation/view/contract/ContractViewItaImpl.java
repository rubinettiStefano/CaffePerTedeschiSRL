package com.generation.view.contract;

import java.util.List;

import com.generation.model.Contract;
import com.generation.view.IView;

public class ContractViewItaImpl implements IView
{
    private static ContractViewItaImpl singleInstance ;

    public static ContractViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ContractViewItaImpl();


        return singleInstance;
    }


    private ContractViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Contract e = (Contract)o;
        res
        .append("Id contratto: ")
        .append(e.getId())
        .append(" , Data Accettazione: ")
        .append(e.getAcceptedOn())
        .append(" , con Prezzo per Unità: ")
        .append(e.getUnitPrice())
        .append(", Quantità: ")
        .append(e.getQuantity());

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
