package com.generation.model;

public class Car implements Comparable<Car>
{
    private static final String[] VALIDMODELS = new String[]{"punto","stilo","peugeot207"};
    
    public String model;
    public int price;

    @Override
    public int compareTo(Car o) 
    {
        // if(this.price>o.price)
        //     return 1;

        // if(this.price==o.price)
        //     return 0;

        // return -1;
        return this.price-o.price;
    }

    @Override
    public String toString() {
        return "{model=" + model + ", price=" + price + "}";
    }

    
}
