package com.generation.view.product;

import java.util.List;

import com.generation.model.Product;
import com.generation.view.IView;

public class ProductViewItaImpl implements IView
{
    private static ProductViewItaImpl singleInstance ;

    public static ProductViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ProductViewItaImpl();


        return singleInstance;
    }


    private ProductViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Product e = (Product)o;
        res
        .append("Prodotto con id: ")
        .append(e.getId())
        .append(" , con nome: ")
        .append(e.getName())
        .append(" , con descrizione ")
        .append(e.getDescription())
        .append(", con Peso Imballaggio: ")
        .append(e.getGrossWeight())
        .append("( Peso Prodotto: "+e.getWeight()+")");

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
