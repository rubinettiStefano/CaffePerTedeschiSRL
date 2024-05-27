package com.generation.controller;

import java.util.List;

import com.generation.model.Client;

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
}
