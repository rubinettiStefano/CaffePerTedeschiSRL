package com.generation.model;

import java.util.ArrayList;

public class Review extends Entity
{
    private Product product;
    private Integer product_id;

    private String content;
    private Double score;
    
    public Review(){}
    
    public Review(Integer id, String content, Double score) 
    {
        super(id);
        this.content = content;
        this.score = score;
    }

    public Product getProduct() {
        return product;
    }

    public String getCotent() {
        return content;
    }

    public void setCotent(String cotent) {
        this.content = cotent;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setProduct(Product product) {
        this.product = product;
        if(product == null)
            this.product_id = null;
        else
            this.product_id = product.getId();
    }

    public Integer getProduct_id() {
        return product_id;
    }

    @Override
    public ArrayList<String> getErrors() 
    {
        ArrayList<String> errors = new ArrayList<>();
        if(product == null )
            errors.add("Product is null");
        if(content == null)
            errors.add("Content is null");
        if(content.isBlank())
            errors.add("Content is blank");
        if(score == null)
            errors.add("Score is null");
        if(score < 0 || score > 5)
            errors.add("Score has invalid value");
        return errors;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Review))
            return false;
    
        return o.hashCode() == this.hashCode();
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}