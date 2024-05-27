package com.generation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Contract extends Entity
{
    private Product product;
    private Integer product_id;
    private List<Batch> batches = new ArrayList<>();

    private LocalDate acceptedOn;
    private Double unitPrice;
    private Integer quantity;

    public Contract(){}

    public Contract(Integer id, LocalDate acceptedOn, Double unitPrice, Integer quantity)
    {
        super(id);
        this.acceptedOn = acceptedOn;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if(product==null)
            this.product_id = null;
        else
            this.product_id = product.getId(); 
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) 
    {
        for(Batch b : batches)
            addBatch(b);
    }

    public LocalDate getAcceptedOn() {
        return acceptedOn;
    }

    public void setAcceptedOn(LocalDate acceptedOn) {
        this.acceptedOn = acceptedOn;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Contract))
            return false;
    
        return o.hashCode() == this.hashCode();
    }

    @Override
    public List<String> getErrors()
    {
        ArrayList<String> errors = new ArrayList<>();
        if(acceptedOn == null)
            errors.add("AcceptedOn in null");
        if(unitPrice == null)
            errors.add("UnitPrice in null");
        if(unitPrice < 0)
            errors.add("UnitPrice is invalid");
        if(quantity == null)
            errors.add("Quantity is null");
        if(quantity < 0)
            errors.add("Quantity is invalid");

        return errors;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void addBatch(Batch b)
    {
        batches.add(b);
        b.setContract(this);
    }

    public void removeBatch(Batch b)
    {
        batches.remove(b);
        b.setContract(null);
    }

    public Integer getTotalWeight()
    {
        return product.getGrossWeight()*quantity;
    }

    public Double getTotalPrice()
    {
        return (double)unitPrice*quantity;
    }

    public Integer getTotalUnitProduced()
    {
        Integer unityProduced = 0;
        for (Batch b : batches) 
        {
            unityProduced += b.getUnitsProduced();
        }

        return unityProduced;
    }

    public Double totalWastedIncome()
    {
        Double totalWastedIncome = 0.0;
        for (Batch b : batches) 
            totalWastedIncome += b.getWastedIncome();
        
        return totalWastedIncome;
    }

    public boolean isCompleted()
    {
        return getTotalUnitProduced() >= quantity;
    }
}
