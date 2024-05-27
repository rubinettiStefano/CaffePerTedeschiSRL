package com.generation.model;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity 
{
    private String name,description;
    private Integer weight,grossWeight;

    private Integer employee_id,client_id,category_id;
    private Employee employee;
    private Client client;
    private Category category;

    private List<Review> reviews = new ArrayList<>();
    private List<Contract> contracts = new ArrayList<>();

    public Product(){}

    public Product(String name, String description, Integer weight, Integer grossWeight) 
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.grossWeight = grossWeight;
    }

    public Product(Integer id, String name, String description, Integer weight, Integer grossWeight) 
    {
        super(id);
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.grossWeight = grossWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Integer grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) 
    {
        this.employee = employee;
        if(employee==null)
            this.employee_id = null;
        else
            this.employee_id = employee.getId();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) 
    {
        this.client = client;
        if(client==null)
            this.client_id = null;
        else
            this.client_id = client.getId();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) 
    {
        this.category = category;
        if(category==null)
            this.category_id = null;
        else
            this.category_id = category.getId();
    }

    @Override
    public List<String> getErrors() 
    {
        ArrayList<String> res = new ArrayList<>();
        if(name==null || name.isBlank())
            res.add("Name non valido");

        if(description==null || description.isBlank())
            res.add("Description non valida");

        if(weight==null || weight<=0)
            res.add("weight non valido");

        if(grossWeight==null || grossWeight<weight)
            res.add("grossWeight non valido");

        return res;
    }

    /**
     * Restituisce il peso del solo imballaggio (in kg)
     */
    public int getPackagingWeight()
    {
        return grossWeight-weight;
    }

    public double getPackagingWeightPercentage()
    {
        return ((double)getPackagingWeight()/grossWeight)*100;
    }


    @Override
    public boolean equals(Object o)
    {
        return super.equals(o) && o instanceof Product;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public List<Contract> getContracts()
    {
        return contracts;
    }

    public void setReviews(List<Review> reviews)
    {
        for(Review r: reviews)
            addReview(r);
    }

    public void setContracts(List<Contract> contracts)
    {
        for(Contract c: contracts)
            addContract(c);
    }

    public void addReview(Review r)
    {
        reviews.add(r);
        r.setProduct(this);
    }

    public void addContract(Contract c)
    {
        contracts.add(c);
        c.setProduct(this);
    }

    public void removeReview(Review r)
    {
        reviews.remove(r);
        r.setProduct(null);
    }

    public void removeContract(Contract c)
    {
        contracts.remove(c);
        c.setProduct(null);
    }

    

}
