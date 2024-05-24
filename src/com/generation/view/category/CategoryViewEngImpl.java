package com.generation.view.category;

import java.util.List;

import com.generation.model.Category;
import com.generation.view.IView;

public class CategoryViewEngImpl implements IView
{
    private static CategoryViewEngImpl singleInstance ;

    public static CategoryViewEngImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new CategoryViewEngImpl();


        return singleInstance;
    }


    private CategoryViewEngImpl(){}

    @Override
    public String render(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Category e = (Category)o;
        res
        .append("id Category: ")
        .append(e.getId())
        .append(" , Category Name: ")
        .append(e.getName());
        

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
