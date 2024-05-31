package com.generation.controller.idimenticati;

public class StudentSpecializzato extends Student
{

    @Override
    public String presentazione()
    {
        return "Ciao ti sovrascrivo e mi chiamo "+name;
    }
}
