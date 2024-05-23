package com.generation.esempiointerfaccia;

public abstract class SalvataggioSuFileFactory 
{
    //questo metodo restituisce un tipo concreto da mettere dentro una variabile di tipo interfaccia
    public static SalvataggioSuFile getInstance()
    {
        double rand = Math.random();
        if(rand>0.5)
            return new SalvaSuFileCsv();

        return new SalvaSuFileACapo();

    }

    public static SalvataggioSuFile getInstance(String type)
    {
        if(type.equals("txt"))
            return new SalvaSuFileACapo();
        
        return new SalvaSuFileCsv();
    }
}
