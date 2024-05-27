package com.generation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.generation.repository.RepositoriesService;
import com.generation.view.IView;
import com.generation.view.batch.BatchViewEngImpl;
import com.generation.view.batch.BatchViewItaImpl;
import com.generation.view.category.CategoryViewEngImpl;
import com.generation.view.category.CategoryViewItaImpl;
import com.generation.view.client.ClientViewEngImpl;
import com.generation.view.client.ClientViewItaImpl;
import com.generation.view.contract.ContractViewEngImpl;
import com.generation.view.contract.ContractViewItaImpl;
import com.generation.view.employee.EmployeeViewEngImpl;
import com.generation.view.employee.EmployeeViewItaImpl;
import com.generation.view.product.ProductViewEngImpl;
import com.generation.view.product.ProductViewItaImpl;
import com.generation.view.review.ReviewViewEngImpl;
import com.generation.view.review.ReviewViewItaImpl;

public abstract class BaseHandler 
{
    protected Scanner term = new Scanner(System.in);
    protected RepositoriesService db = RepositoriesService.getSingleton();
    protected String language = "ita";
    protected Map<String,IView> views = new HashMap<>();

    public BaseHandler()
    {
        views.put("itabatch",       BatchViewItaImpl.getSingleton()     );
        views.put("engbatch",       BatchViewEngImpl.getSingleton()     );
        views.put("itacategory",    CategoryViewItaImpl.getSingleton()  );
        views.put("engcategory",    CategoryViewEngImpl.getSingleton()  );
        views.put("itaclient",      ClientViewItaImpl.getSingleton()    );
        views.put("engclient",      ClientViewEngImpl.getSingleton()    );
        views.put("itacontract",    ContractViewItaImpl.getSingleton()  );
        views.put("engcontract",    ContractViewEngImpl.getSingleton()  );
        views.put("itaemployee",    EmployeeViewItaImpl.getSingleton()  );
        views.put("engemployee",    EmployeeViewEngImpl.getSingleton()  );
        views.put("itaproduct",     ProductViewItaImpl.getSingleton()   );
        views.put("engproduct",     ProductViewEngImpl.getSingleton()   );
        views.put("itareview",      ReviewViewItaImpl.getSingleton()    );
        views.put("engreview",      ReviewViewEngImpl.getSingleton()    );
    }
    
    public IView getView(String en)
    {
        return views.get(language+en);
    } 

    public void changeLanguage()
    {
        System.out.println("Insert lang (ita/eng)");
        String lang = term.nextLine().toLowerCase();
       
        if(lang.equals("ita"))
            language = "ita";
        else
            language = "eng";
    }
}
