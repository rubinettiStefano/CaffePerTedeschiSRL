package com.generation.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends Entity
{
    private String name;
    private List<Product> products = new ArrayList<>();

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

    public List<String> getErrors()
    {
        ArrayList<String> errors = new ArrayList<>();
        if( name == null)
            errors.add("Name is null");
        if(name.isBlank())
            errors.add("Name is blank");

        return errors;
    }

    public void setProducts(List<Product> products) 
    {
        if(products!=null)
            for(Product p : products)
                addProduct(p);
    }

    public void addProduct(Product p)
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

    public List<Product> getProducts() 
    {
        return products;
    }

}
