package com.generation.view;

import java.util.Scanner;

import com.generation.model.Employee;

public class Prova 
{
    static EmployeeView view ;
    public static void main(String[] args) 
    {
        init();

        Employee e = new Employee(2,"Stefano", "Rubinetti");
        

        String output = view.render(e);

        System.out.println(output);
    }

    public static void init()
    {
        Scanner term = new Scanner(System.in);
        System.out.println("Scrivi ITA per italiano, DE per tedesco, qualsiasi altra cosa per inglese");
        String lang  = term.nextLine();

        if(lang.equals("ITA"))
        {
            view = new EmployeeViewIta();
            return;
        }

        if(lang.equals("DE"))
        {
            view = new EmployeeViewDe();
            return;
        }

        view = new EmployeeViewEng();

    }
}
