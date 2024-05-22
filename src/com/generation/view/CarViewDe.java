package com.generation.view;

import java.util.ArrayList;

import com.generation.model.Car;

public class CarViewDe implements View
{

    @Override
    public String render(Object o)
    {
        Car e = (Car)o;
        StringBuilder res  = new StringBuilder();
        res
        .append("Hallo, ich bin ein Mitarbeiter mit der MODELLO: ")
        .append(e.model)
        .append(" , mit dem PREZZO ")
        .append(e.price);

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
