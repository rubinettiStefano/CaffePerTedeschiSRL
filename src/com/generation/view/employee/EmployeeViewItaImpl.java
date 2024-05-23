package com.generation.view.employee;

import java.util.List;

import com.generation.model.Employee;
import com.generation.view.IView;

public class EmployeeViewItaImpl implements IView
{

    //private static EmployeeViewItaImpl singleInstance =  new EmployeeViewItaImpl();
    // public static EmployeeViewItaImpl getSingleton()
    // {
    //     return singleInstance;
    // }

    
    private static EmployeeViewItaImpl singleInstance ;

    public static EmployeeViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new EmployeeViewItaImpl();


        return singleInstance;
    }


    private EmployeeViewItaImpl(){}

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
