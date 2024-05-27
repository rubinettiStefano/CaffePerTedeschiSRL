package com.generation.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.model.Batch;
import com.generation.model.Category;
import com.generation.model.Client;
import com.generation.model.Contract;
import com.generation.model.Employee;
import com.generation.model.Product;
import com.generation.model.Review;

//Di leggere sempre senza le entità collegate
//Leggere solo FIGLI DIRETTI e INDIRETTI, quando leggo un entità NON leggo i suoi padri, non leggo i NIPOTI. Il classico
//una politica CUSTOM, se leggo qualcosa leggo TUTTO ciò che è collegato

public class RepositoriesService 
{
    private static RepositoriesService singleton;

    public static RepositoriesService getSingleton()
    {
        if(singleton==null)
            singleton= new RepositoriesService();

        return singleton;
    }

    private RepositoriesService(){}

    private IRepository<Employee> eRepo     = new EmployeeRepositoryImpl("employee");
    private IRepository<Product> pRepo      = new ProductRepositoryImpl("product");
    private IRepository<Batch> bRepo        = new BatchRepositoryImpl("batch");
    private IRepository<Client> clRepo      = new ClientRepositoryImpl("client");
    private IRepository<Contract> conRepo   = new ContractRepositoryImpl("contract");
    private IRepository<Review> rRepo       = new ReviewRepositoryImpl("review");
    private IRepository<Category> catRepo   = new CategoryRepositoryImpl("category");
//INIZIO BATCH
    public List<Batch> selectAllBatches()
    {
        try 
        {
            return bRepo.selectAll();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Batch> selectBatches(String condition)
    {
        try 
        {
            return bRepo.select(condition);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public Batch selectBatch(int id)
    {
        try 
        {
            return bRepo.select(id);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
//FINE BATCH
//INIZIO CONTRACT
    public Contract selectContract(int id)
    {
        try 
        {
            Contract c = conRepo.select(id);
            c.setBatches(groupBatches().get(c.getId()));
            //imposta i batch figli del contratto
            //prendendo la mappa
            //e prendendo la lista che ha come chiave l'id del contratto
            return c;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contract> selectAllContracts()
    {
        try 
        {
            List<Contract> res = conRepo.selectAll();

            for(Contract c : res)
                c.setBatches(groupBatches().get(c.getId()));
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contract> selectContracts(String condition)
    {
        try 
        {
            List<Contract> res = conRepo.select(condition);

            for(Contract c : res)
                c.setBatches(groupBatches().get(c.getId()));
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
//FINE CONTRACT

//METODI DI UTILITY
    private Map<Integer,List<Batch>> groupBatches()
    {
        List<Batch> all = selectAllBatches();
        Map<Integer,List<Batch>> res = new HashMap<>();

        for(Batch b : all)
        {
            int fk = b.getContractId();

            if(!res.containsKey(fk))
            {
                List<Batch> temp = new ArrayList<>();
                temp.add(b);
                res.put(fk, temp);
            }
            else
                res.get(fk).add(b);
        }

        return res;
    }

    private Map<Integer,List<Contract>> groupContracts()
    {
        List<Contract> all = selectAllContracts();
        //i Contract che prendo sono LEGATI ai batches loro figli
        Map<Integer,List<Contract>> res = new HashMap<>();

        for(Contract b : all)
        {
            int fk = b.getProduct_id();

            if(!res.containsKey(fk))
            {
                List<Contract> temp = new ArrayList<>();
                temp.add(b);
                res.put(fk, temp);
            }
            else
                res.get(fk).add(b);
        }

        return res;
    }
}
