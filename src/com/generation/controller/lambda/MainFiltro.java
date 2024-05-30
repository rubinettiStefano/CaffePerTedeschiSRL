package com.generation.controller.lambda;

import java.util.ArrayList;
import java.util.List;

import com.generation.model.Product;
import com.generation.repository.RepositoriesService;

public class MainFiltro
{
    static protected RepositoriesService db = RepositoriesService.getSingleton();
    public static void main(String[] args) 
    {
        List<Product> all = db.selectAllProducts();

        //voglio avere i prodotti che pesano piu di 250 kg
        List<Product> pesanti = filtraProdottiUniversale(all, p -> p.getWeight()>250);
        // boolean verificaCondizione(Product p)
        //{
            // return p.getWeight()>250;
        //}
        //voglio avere i prodotti che abbiano meno di 20 kg di imballaggio
        List<Product> pocaPlastica = filtraProdottiUniversale(all,p -> {
                                                                            return p.getPackagingWeight()<20;
                                                                       });
    }

    //CALLBACK -> passare un metodo come parametro ad un altro metodo, che poi lo utilizza al suo interno
    public static List<Product> filtraProdottiUniversale(List<Product> all,CondizioneDiFiltro c)
    {
        List<Product> res =  new ArrayList<>();

        for(Product p: all)
            if(c.verificaCondizione(p))           
                res.add(p);

        return  res;
    }


    public static List<Product> filtraProdotti(int peso,List<Product> all,String caso)
    {
        List<Product> res =  new ArrayList<>();

        for(Product p: all)
        {
            switch (caso) {
                case "pesomin":
                    if(p.getWeight()>peso)
                        res.add(p);
                break;
                case "pesomax":
                    if(p.getWeight()<peso)
                        res.add(p);
                break;
                case "pesomintara":
                    if(p.getPackagingWeight()>peso)
                        res.add(p);
                break;
                case "pesomaxtara":
                if(p.getPackagingWeight()<peso)
                    res.add(p);
                break;
            }
        }        
        return  res;
    }

    public static List<Product> filtraPerPeso(int peso,List<Product> all)
    {
        List<Product> res =  new ArrayList<>();

        for(Product p: all)
            if(p.getWeight()>peso)
                res.add(p);

        return  res;
    }

    public static List<Product> filtraPerTara(int pesoTara,List<Product> all)
    {
        List<Product> res =  new ArrayList<>();

        for(Product p: all)
            if(p.getWeight()<pesoTara)
                res.add(p);

        return  res;
    }
}
