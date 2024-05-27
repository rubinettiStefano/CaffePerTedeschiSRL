package com.generation.view.contract;

import java.util.List;

import com.generation.model.Contract;
import com.generation.view.IView;

public class ContractViewEngImpl implements IView
{
    private static ContractViewEngImpl singleInstance ;

    public static ContractViewEngImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ContractViewEngImpl();


        return singleInstance;
    }


    private ContractViewEngImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Contract e = (Contract)o;
        res
        .append("Contract id: ")
        .append(e.getId())
        .append(" , Accepted Date: ")
        .append(e.getAcceptedOn())
        .append(" , Unity price: ")
        .append(e.getUnitPrice())
        .append(", Quantity: ")
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
