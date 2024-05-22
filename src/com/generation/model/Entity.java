package com.generation.model;

import java.util.ArrayList;

/**
 * È una classe ASTRATTA che serve da base per tutte le classi modello
 */
public abstract class Entity 
{
    protected Integer id;//protected -> accessibile nello stesso package e in tutte le SOTTOCLASSI, dovunque esse siano


    public Entity(){}

    public Entity(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce TRUE se l'oggetto può interagire con il db
     * G.I.G.O.
     * Garbage In Garbage Out
     * Se facciamo entrare spazzatura nel db
     * Il nostro programma produrrà spazzatura
     */
    public boolean isValid()
    {
        return getErrors().size()==0;
    }    

    /**
     * Restituisce una lista con Stringhe di errore
     * Se la lista è vuota non ci sono errori
     */
    public abstract ArrayList<String> getErrors();//dovrà essere sovrascritto da chi estende questa classe
    
    @Override
    public int hashCode()
    {
        return id;
    }


    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Entity))
            return false;
    
        return o.hashCode() == this.hashCode();
    }

}
