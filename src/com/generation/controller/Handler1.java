package com.generation.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.generation.model.Category;
import com.generation.model.Client;
import com.generation.model.Product;
import com.generation.model.Review;

public class Handler1 extends BaseHandler implements Es1
{

    @Override
    public void printAllClient()
    {
        List<Client> all = db.selectAllClients();

        System.out.println(getView("client").renderAll((List<Object>)(List<?>)all));
    }

    @Override
    public void printContractByInterval() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printContractByInterval'");
    }

    @Override
    public void printRevenueByInterval() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printRevenueByInterval'");
    }

    @Override
    public void printContractByNation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printContractByNation'");
    }

    public void stampareRecensioniDiProdotto1e2()
    {
        Product espresso = db.selectProduct(1);
        Product latte = db.selectProduct(2);

        List<Review> reviews = new ArrayList<>();

        reviews.addAll(espresso.getReviews());
        reviews.addAll(latte.getReviews());

        System.out.println(getView("review").renderAll((List<Object>)(List<?>)reviews));
    }

    public void stampareProdottiDiCatBevEClienteItaliano()
    {
       

        Category bev = db.selectCategory(1);//categoria beverages
        Client ita = db.selectClient(1);

        Set<Product> res = new HashSet<>(bev.getProducts());

        res.retainAll(ita.getProducts());

        List<Product> toPrint = new ArrayList<>(res);
        System.out.println(getView("product").renderAll((List<Object>)(List<?>)toPrint));
    }
}
