package com.generation.model;

import java.util.ArrayList;

public class Category extends Entity
{
    private String name;
    private ArrayList<Product> products = new ArrayList<>();

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

    public Category(){}
    

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

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProducts(Product p)
    {
        products.add(p);
        p.setCategory(this);
    }

    public void removeProduct(Product p)
    {
        products.remove(p);
        p.setCategory(null);
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o) && o instanceof Category;
    }

    public ArrayList<Product> getProducts() 
    {
        return products;
    }

}
