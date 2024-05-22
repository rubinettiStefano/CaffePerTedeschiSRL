package com.generation.model;

import java.util.ArrayList;

public class Client extends Entity
{
    private String legalName;
    private String address;
    private String city;
    private String country;

    public Client(){}

    public Client(String legalName, String address, String city, String country) 
    {
        super();
        this.legalName = legalName;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Client(Integer id, String legalName, String address, String city, String country) 
    {
        super(id);
        this.legalName = legalName;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
     public ArrayList<String> getErrors()
     {
        ArrayList<String> errors = new ArrayList<>();
        if( legalName == null )
            errors.add("Legal name is null");
        if( legalName.isBlank() )
            errors.add("Legal name is blank");
        if( address == null )
            errors.add("Address is null");
        if( address.isBlank() )
            errors.add("Address is blank");
        if( city == null )
            errors.add("City is null");
        if( city.isBlank() )
            errors.add("City is blank");
        if( country == null )
            errors.add("Country is null");
        if( country.isBlank() )
            errors.add("Country is blank");

        return errors;
     }
}
