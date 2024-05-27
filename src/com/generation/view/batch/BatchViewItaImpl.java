package com.generation.view.batch;

import java.util.List;

import com.generation.model.Batch;
import com.generation.view.IView;

public class BatchViewItaImpl implements IView
{
    private static BatchViewItaImpl singleInstance ;

    public static BatchViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new BatchViewItaImpl();


        return singleInstance;
    }


    private BatchViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Batch e = (Batch)o;
        res
        .append("Id contratto: ")
        .append(e.getId())
        .append(" , Data produzione: ")
        .append(e.getProductionDate())
        .append(" , con unità scartate: ")
        .append(e.getUnityDiscarded())
        .append(", Stato: ")
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
