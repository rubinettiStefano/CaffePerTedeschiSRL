package com.generation.view.product;

import java.util.List;

import com.generation.model.Employee;
import com.generation.view.IView;

public class ProductViewItaImpl implements IView
{

    //private static EmployeeViewItaImpl singleInstance =  new EmployeeViewItaImpl();
    // public static EmployeeViewItaImpl getSingleton()
    // {
    //     return singleInstance;
    // }

    
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
    public String render(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Employee e = (Employee)o;
        res
        .append("Ciao sono un impiegato con id: ")
        .append(e.getId())
        .append(" , con nome ")
        .append(e.getName())
        .append(" , con cognome ")
        .append(e.getSurname());

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
