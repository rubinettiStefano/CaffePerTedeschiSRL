package com.generation.controller.idimenticati;


public class Finalmente 
{
    
    public static void main(String[] args) 
    {
        final int a = 10;//davanti a primitivi ne impedisce la modifica 
        
        final Student s = new Student();//davanti a variabili oggetto, la variabile
        //non può cambiare oggetto a cui è collegata, ma l'oggetto in se può essere modificato
        s.name = "stefano";


    }
}
