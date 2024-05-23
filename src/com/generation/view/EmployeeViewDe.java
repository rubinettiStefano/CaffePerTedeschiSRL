package com.generation.view;

import java.util.ArrayList;

import com.generation.model.Employee;

public class EmployeeViewDe implements View
{

    @Override
    public String render(Object o)
    {
        Employee e = (Employee)o;
        StringBuilder res  = new StringBuilder();
        res
        .append("Hallo, ich bin ein Mitarbeiter mit der ID: ")
        .append(e.getId())
        .append(" , mit dem Namen ")
        .append(e.getName())
        .append(" , mit dem Nachnamen ")
        .append(e.getSurname());

        return res.toString();
    }

    @Override
    public String render(ArrayList<Object> l)
    {
        StringBuilder res = new StringBuilder();

        for(Object e:l)
            res.append(render(e)).append("\n");

        return res.toString();
    }

}
