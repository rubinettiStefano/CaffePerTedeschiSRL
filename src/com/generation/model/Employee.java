package com.generation.model;

import java.util.ArrayList;

/**
 * Un employee è una persona che lavora per noi
 */
public class Employee extends Entity
{
    private String name,surname;

    public Employee(){}

    public Employee(String name, String surname)
    {
        super();
        this.name = name;
        this.surname = surname;
    }

    public Employee(Integer id, String name, String surname) 
    {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    @Override
    public  ArrayList<String> getErrors()
    {
        ArrayList<String> res = new ArrayList<>();

        if(name==null || name.isBlank())
            res.add("Nome non valido");

        if(surname==null || surname.isBlank())
            res.add("Surname non valido");

        return res;
    }
}
