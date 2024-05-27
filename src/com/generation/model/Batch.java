package com.generation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Batch extends Entity
{
    private Contract contract;
    private Integer contract_id;

    private LocalDate productionDate;
    private Integer unitsProduced;
    private Integer unityDiscarded;
    private String status;

    public Batch(){}

    public Batch(Integer id, String productionDate, Integer unitsProduced, Integer unityDiscarded, String status)
    {
        super(id);
        this.productionDate = LocalDate.parse(productionDate);
        this.unitsProduced =unitsProduced;
        this.unityDiscarded = unityDiscarded;
        this.status = status;
    }

    public Integer getContractId()
    {
        return contract_id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
        if(contract == null)
            this.contract_id = null;
        else
            this.contract_id = contract.getId();
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getUnitsProduced() {
        return unitsProduced;
    }

    public void setUnitsProduced(Integer unitsProduced) {
        this.unitsProduced = unitsProduced;
    }

    public Integer getUnityDiscarded() {
        return unityDiscarded;
    }

    public void setUnityDiscarded(Integer unityDiscarded) {
        this.unityDiscarded = unityDiscarded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Batch))
            return false;
    
        return o.hashCode() == this.hashCode();
    }

    

    @Override
    public List<String> getErrors()
    {
        ArrayList<String> errors = new ArrayList<>();
        if(contract == null)
            errors.add("Contract in null");
        if(productionDate == null)
            errors.add("ProductionDate in null");
        if(productionDate.isBefore(contract.getAcceptedOn()))
            errors.add("ProductionDate is invalid");
        if(unitsProduced == null)
            errors.add("UnityProduced is null");
        if(unitsProduced < 0)
            errors.add("UnityProduced is invalid");
        if(unityDiscarded == null)
            errors.add("UnityDiscarded id null");
        if(unityDiscarded < 0)
            errors.add("UnityDiscarded is invalid");
        if(status == null)
            errors.add("Status is null");
        if(status.isBlank())
            errors.add("Status is blank");

        return errors;
    }    

    public Double getWastedIncome()
    {
        return (double)unityDiscarded*contract.getUnitPrice();
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }
}
