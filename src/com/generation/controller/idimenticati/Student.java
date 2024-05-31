package com.generation.controller.idimenticati;

import java.util.ArrayList;
import java.util.List;

public final class Student 
{
    public String name;
    public List<Integer> grades = new ArrayList<>();
    public boolean immunita;

    public boolean sonoInsufficiente()
    {
        for(int g:grades)
            if(g<6)
                return true;
        
        return false;
    }

    public  String presentazione()
    {
        return "ciao mi chiamo "+name;
    }
}
