package com.generation.view.product;

import java.util.List;

import com.generation.model.Product;
import com.generation.view.IView;

public class ProductViewEngImpl implements IView
{
    private static ProductViewEngImpl singleInstance ;

    public static ProductViewEngImpl getSingleton()
    {
        if(singleInstance==null)
            singleInstance = new ProductViewEngImpl();


        return singleInstance;
    }


    private ProductViewEngImpl(){}

    @Override
    public String render(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Product e = (Product)o;
        res
        .append("Product id: ")
        .append(e.getId())
        .append(" , with name: ")
        .append(e.getName())
        .append(" , with description ")
        .append(e.getDescription())
        .append(", with gross weight: ")
        .append(e.getGrossWeight())
        .append("( Product Weight: "+e.getWeight()+")");

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
