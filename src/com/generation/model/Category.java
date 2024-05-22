package com.generation.model;

import java.util.ArrayList;

public class Category extends Entity 
{
    private String name;

    public Category(String name) 
    {
        super();
        this.name = name;
    }

    public Category(Integer id, String name) 
    {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getErrors()
    {
        ArrayList<String> errors = new ArrayList<>();
        if( name == null)
            errors.add("Name is null");
        if(name.isBlank())
            errors.add("Name is blank");

        return errors;
    }

}
