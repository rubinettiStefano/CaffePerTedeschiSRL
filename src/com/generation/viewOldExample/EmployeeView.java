package com.generation.viewOldExample;

import java.util.ArrayList;

import com.generation.model.Employee;

public interface EmployeeView 
{
    //questo è tutto ciò che mi interessa delle View riguardanti gli Employee
    String render(Employee e); //public abstract
    String render(ArrayList<Employee> l);
}
