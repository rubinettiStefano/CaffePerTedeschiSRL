package com.generation.view;

import java.util.ArrayList;

import com.generation.model.Employee;

public class EmployeeViewIta implements EmployeeView
{
    @Override
    public String render(Employee e)
    {
        StringBuilder res  = new StringBuilder();
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
    public String render(ArrayList<Employee> l)
    {
        StringBuilder res = new StringBuilder();

        for(Employee e:l)
            res.append(render(e)).append("\n");

        return res.toString();
    }
}
