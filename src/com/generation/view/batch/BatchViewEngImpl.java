package com.generation.view.batch;

import java.util.List;

import com.generation.model.Batch;
import com.generation.view.IView;

public class BatchViewEngImpl implements IView
{
    private static BatchViewEngImpl singleInstance ;

    public static BatchViewEngImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new BatchViewEngImpl();


        return singleInstance;
    }


    private BatchViewEngImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Batch e = (Batch)o;
        res
        .append("Id contract: ")
        .append(e.getId())
        .append(" , Production Date: ")
        .append(e.getProductionDate())
        .append(" , Discarded units: ")
        .append(e.getUnityDiscarded())
        .append(", Status: ")
        .append(e.getStatus());

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
