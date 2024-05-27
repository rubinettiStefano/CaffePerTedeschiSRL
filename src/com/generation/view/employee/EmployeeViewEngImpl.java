package com.generation.view.employee;

import java.util.List;

import com.generation.model.Employee;
import com.generation.view.IView;

public class EmployeeViewEngImpl implements IView
{
    private static EmployeeViewEngImpl singleInstance ;

    public static EmployeeViewEngImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new EmployeeViewEngImpl();


        return singleInstance;
    }

    private EmployeeViewEngImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Employee e = (Employee)o;
        res
        .append("Hi, i am an employee with id: ")
        .append(e.getId())
        .append(" , with name ")
        .append(e.getName())
        .append(" , with surname ")
        .append(e.getSurname());

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
