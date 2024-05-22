package com.generation.view;

import com.generation.model.Car;

public class Prova2 
{
    static View view = new CarViewDe();
    public static void main(String[] args) 
    {

        // Employee e = new Employee(2,"Stefano", "Rubinetti");
        Car c =  new Car();
        c.model = "PUNTO";
        c.price=10000;

        String output = view.render(c);

        System.out.println(output);
    }

}
