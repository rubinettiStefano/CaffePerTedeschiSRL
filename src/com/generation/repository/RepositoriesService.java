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
import com.generation.repository.abstraction.IRepository;
import com.generation.repository.implementation.BatchRepositoryImpl;
import com.generation.repository.implementation.CategoryRepositoryImpl;
import com.generation.repository.implementation.ClientRepositoryImpl;
import com.generation.repository.implementation.ContractRepositoryImpl;
import com.generation.repository.implementation.EmployeeRepositoryImpl;
import com.generation.repository.implementation.ProductRepositoryImpl;
import com.generation.repository.implementation.ReviewRepositoryImpl;

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
//INIZIO CLIENT
    public Client selectClient(int id)
    {
        try 
        {
            Client c = clRepo.select(id);
            c.setProducts(groupProducts().get("client").get(c.getId()));
            return c;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Client> selectAllClients()
    {
        try 
        {
            List<Client> res = clRepo.selectAll();

            for(Client c : res)
            {
                c.setProducts(groupProducts().get("client").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Client> selectClients(String condition)
    {
        try 
        {
            List<Client> res = clRepo.select(condition);

            for(Client c : res)
            {
                c.setProducts(groupProducts().get("client").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
//FINE CLIENT
//INIZIO EMPLOYEE
    public Employee selectEmployee(int id)
    {
        try 
        {
            Employee c = eRepo.select(id);
            //Insieme di Coppie Key-Value
            //utilizziamo una key per accedere al value corrispondente
            //al metodo get passiamo un oggetto con il tipo della key
            //e otteniamo un oggetto con il tipo del value
            c.setProducts(groupProducts().get("employee").get(c.getId()));
            return c;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> selectAllEmployees()
    {
        try 
        {
            List<Employee> res = eRepo.selectAll();

            for(Employee c : res)
            {
                c.setProducts(groupProducts().get("employee").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> selectEmployees(String condition)
    {
        try 
        {
            List<Employee> res = eRepo.select(condition);

            for(Employee c : res)
            {
                c.setProducts(groupProducts().get("employee").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
//FINE EMPLOYEE
//INIZIO CATEGORY
    public Category selectCategory(int id)
    {
        try 
        {
            Category c = catRepo.select(id);
            c.setProducts(groupProducts().get("category").get(c.getId()));
            return c;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> selectAllCategories()
    {
        try 
        {
            List<Category> res = catRepo.selectAll();

            for(Category c : res)
            {
                c.setProducts(groupProducts().get("category").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> selectCategories(String condition)
    {
        try 
        {
            List<Category> res = catRepo.select(condition);

            for(Category c : res)
            {
                c.setProducts(groupProducts().get("category").get(c.getId()));
            }
            return res;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
//FINE CATEGORY

//INSERT,UPDATE,DELETE
    public void insertBatch(Batch b)
    {
        try 
        {
            bRepo.insert(b);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void insertBatches(List<Batch> l)
    {
        try 
        {
            bRepo.insert(l);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void updateBatch(Batch b)
    {
        try 
        {
            bRepo.update(b);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void updateBatches(List<Batch> l)
    {
        try 
        {
            bRepo.update(l);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void deleteBatch(Batch b)
    {
        try 
        {
            bRepo.delete(b);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void deleteBatch(int id)
    {
        try 
        {
            bRepo.delete(id);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void deleteBatches(List<Batch> l)
    {
        try 
        {
            bRepo.delete(l);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void deleteBatches(int[] ids)
    {
        try 
        {
            bRepo.delete(ids);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void insertEmployee(Employee e)
    {
        try 
        {
            eRepo.insert(e);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertEmployees(List<Employee> l)
    {
        try 
        {
            eRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Employee e)
    {
        try 
        {
            eRepo.update(e);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateEmployees(List<Employee> l)
    {
        try 
        {
            eRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(Employee e)
    {
        try 
        {
            eRepo.delete(e);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id)
    {
        try 
        {
            eRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteEmployees(List<Employee> l)
    {
        try 
        {
            eRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteEmployees(int[] ids)
    {
        try 
        {
            eRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    // Metodi per Product
    public void insertProduct(Product p)
    {
        try 
        {
            pRepo.insert(p);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertProducts(List<Product> l)
    {
        try 
        {
            pRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateProduct(Product p)
    {
        try 
        {
            pRepo.update(p);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateProducts(List<Product> l)
    {
        try 
        {
            pRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteProduct(Product p)
    {
        try 
        {
            pRepo.delete(p);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteProduct(int id)
    {
        try 
        {
            pRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteProducts(List<Product> l)
    {
        try 
        {
            pRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteProducts(int[] ids)
    {
        try 
        {
            pRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    // Metodi per Client
    public void insertClient(Client c)
    {
        try 
        {
            clRepo.insert(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertClients(List<Client> l)
    {
        try 
        {
            clRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateClient(Client c)
    {
        try 
        {
            clRepo.update(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateClients(List<Client> l)
    {
        try 
        {
            clRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteClient(Client c)
    {
        try 
        {
            clRepo.delete(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteClient(int id)
    {
        try 
        {
            clRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteClients(List<Client> l)
    {
        try 
        {
            clRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteClients(int[] ids)
    {
        try 
        {
            clRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    // Metodi per Contract
    public void insertContract(Contract c)
    {
        try 
        {
            conRepo.insert(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertContracts(List<Contract> l)
    {
        try 
        {
            conRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateContract(Contract c)
    {
        try 
        {
            conRepo.update(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateContracts(List<Contract> l)
    {
        try 
        {
            conRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteContract(Contract c)
    {
        try 
        {
            conRepo.delete(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteContract(int id)
    {
        try 
        {
            conRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteContracts(List<Contract> l)
    {
        try 
        {
            conRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteContracts(int[] ids)
    {
        try 
        {
            conRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    // Metodi per Review
    public void insertReview(Review r)
    {
        try 
        {
            rRepo.insert(r);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertReviews(List<Review> l)
    {
        try 
        {
            rRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateReview(Review r)
    {
        try 
        {
            rRepo.update(r);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateReviews(List<Review> l)
    {
        try 
        {
            rRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteReview(Review r)
    {
        try 
        {
            rRepo.delete(r);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteReview(int id)
    {
        try 
        {
            rRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteReviews(List<Review> l)
    {
        try 
        {
            rRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteReviews(int[] ids)
    {
        try 
        {
            rRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    // Metodi per Category
    public void insertCategory(Category c)
    {
        try 
        {
            catRepo.insert(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void insertCategories(List<Category> l)
    {
        try 
        {
            catRepo.insert(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateCategory(Category c)
    {
        try 
        {
            catRepo.update(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void updateCategories(List<Category> l)
    {
        try 
        {
            catRepo.update(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteCategory(Category c)
    {
        try 
        {
            catRepo.delete(c);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteCategory(int id)
    {
        try 
        {
            catRepo.delete(id);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteCategories(List<Category> l)
    {
        try 
        {
            catRepo.delete(l);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void deleteCategories(int[] ids)
    {
        try 
        {
            catRepo.delete(ids);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }


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
    private Map<
                 String,  //key
                 Map< Integer, List<Product> >  //value
                > groupProducts()
    {
        List<Product> all = selectAllProducts();
      

        Map<Integer,List<Product>> mapEmp = new HashMap<>();
        Map<Integer,List<Product>> mapCat = new HashMap<>();
        Map<Integer,List<Product>> mapCl = new HashMap<>();

        //char c =res.get("aaa").get(15).get(0).getName().charAt(12);

        for(Product p : all)
        {
            int fkEmp = p.getEmployee_id();
            int fkCat = p.getCategory_id();
            int fkCl = p.getClient_id();

            if(!mapEmp.containsKey(fkEmp))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(p);
                mapEmp.put(fkEmp, temp);
            }
            else
                mapEmp.get(fkEmp).add(p);

            if(!mapCat.containsKey(fkCat))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(p);
                mapCat.put(fkCat, temp);
            }
            else
                mapCat.get(fkCat).add(p);

            
            if(!mapCl.containsKey(fkCl))
            {
                List<Product> temp = new ArrayList<>();
                temp.add(p);
                mapCl.put(fkCl, temp);
            }
            else
                mapCl.get(fkCl).add(p);
        }
        Map<String,Map<Integer,List<Product>>> res = new HashMap<>();
        res.put("employee", mapEmp);
        res.put("client", mapCl);
        res.put("category", mapCat);
        return res;
    }

}
