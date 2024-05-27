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
//INIZIO REVIEW
public Review selectReview(int id)
{
    try 
    {
        Review c = rRepo.select(id);
        return c;
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return null;
    }
}

public List<Review> selectAllReviews()
{
    try 
    {
        List<Review> res = rRepo.selectAll();

        return res;
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return null;
    }
}

public List<Review> selectReviews(String condition)
{
    try 
    {
        List<Review> res = rRepo.select(condition);

        return res;
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return null;
    }
}
//FINE REVIEW
//INIZIO PRODUCT
public Product selectProduct(int id)
{
    try 
    {
        Product c = pRepo.select(id);
        c.setContracts(groupContracts().get(c.getId()));
        c.setReviews(groupReviews().get(c.getId()));
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

public List<Product> selectAllProducts()
{
    try 
    {
        List<Product> res = pRepo.selectAll();

        for(Product c : res)
        {
            c.setContracts(groupContracts().get(c.getId()));
            c.setReviews(groupReviews().get(c.getId()));
        }
        return res;
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return null;
    }
}

public List<Product> selectPoducts(String condition)
{
    try 
    {
        List<Product> res = pRepo.select(condition);

        for(Product c : res)
        {
            c.setContracts(groupContracts().get(c.getId()));
            c.setReviews(groupReviews().get(c.getId()));
        }
        return res;
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return null;
    }
}
//FINE PRODUCT

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

    private Map<Integer,List<Review>> groupReviews()
    {
        List<Review> all = selectAllReviews();
        Map<Integer,List<Review>> res = new HashMap<>();

        for(Review b : all)
        {
            int fk = b.getProduct_id();

            if(!res.containsKey(fk))
            {
                List<Review> temp = new ArrayList<>();
                temp.add(b);
                res.put(fk, temp);
            }
            else
                res.get(fk).add(b);
        }

        return res;
    }

    //avremo 3 metodi in 1
    //la chiave String sarà il nome dell'entita padre (client,employee,category)
    //il valore associato sarà la mappa con chiavi esterne verso quell'entità e prodotti figli
    private Map<String,Map<Integer,List<Product>>> groupProducts()
    {
        List<Product> all = selectAllProducts();
        Map<String,Map<Integer,List<Product>>> res = new HashMap<>();
        Map<Integer,List<Product>> mapEmp = new HashMap<>();
        Map<Integer,List<Product>> mapCat = new HashMap<>();
        Map<Integer,List<Product>> mapCl = new HashMap<>();

        for(Product b : all)
        {
            int fkEmp = b.getEmployee_id();
            int fkCat = b.getCategory_id();
            int fkCl = b.getClient_id();

            if(!mapEmp.containsKey(fkEmp))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(b);
                mapEmp.put(fkEmp, temp);
            }
            else
                mapEmp.get(fkEmp).add(b);

            if(!mapCat.containsKey(fkCat))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(b);
                mapCat.put(fkCat, temp);
            }
            else
                mapCat.get(fkCat).add(b);

            
            if(!mapCl.containsKey(fkCl))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(b);
                mapCl.put(fkCl, temp);
            }
            else
                mapCl.get(fkCl).add(b);
        }

        res.put("employee", mapEmp);
        res.put("client", mapCl);
        res.put("category", mapCat);
        return res;
    }

}
