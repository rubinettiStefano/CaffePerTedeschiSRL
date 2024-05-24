package com.generation.esempiointerfaccia;

import java.util.Comparator;

import com.generation.model.Car;

public class ComparaMacchineAlfabetico implements Comparator<Car>
{

    @Override
    public int compare(Car o1, Car o2) 
    {
        return o1.model.compareTo(o2.model);
    }

}
