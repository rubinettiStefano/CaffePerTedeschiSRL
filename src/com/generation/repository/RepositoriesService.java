package com.generation.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.model.Employee;
import com.generation.model.Product;

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

    private IRepository<Employee> eRepo = new EmployeeRepositoryImpl("employee");
    private IRepository<Product> pRepo = new ProductRepositoryImpl("product");

    public List<Employee> selectAllEmployee() 
    {
        try 
        {
            return eRepo.selectAll();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
