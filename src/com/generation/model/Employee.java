package com.generation.model;

import java.util.ArrayList;


/**
 * Un employee è una persona che lavora per noi
 */
public class Employee extends Entity 
{
    private String name,surname;

    private ArrayList<Product> products = new ArrayList<>();


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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product p)
    {
        products.add(p);
        p.setEmployee(this);
    }
    
    public void removeProduct(Product p)
    {
        products.remove(p);
        p.setEmployee(null);
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

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o) && o instanceof Employee;
    }

    
}
