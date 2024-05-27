package com.generation.view.category;

import java.util.List;

import com.generation.model.Category;
import com.generation.view.IView;

public class CategoryViewItaImpl implements IView 
{
    private static CategoryViewItaImpl singleInstance ;

    public static CategoryViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new CategoryViewItaImpl();


        return singleInstance;
    }


    private CategoryViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Category e = (Category)o;
        res
        .append("id Categoria: ")
        .append(e.getId())
        .append(" , Nome Categoria: ")
        .append(e.getName());
        

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
