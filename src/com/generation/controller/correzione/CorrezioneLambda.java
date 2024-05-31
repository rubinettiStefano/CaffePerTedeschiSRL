package com.generation.controller.correzione;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.generation.controller.BaseHandler;
import com.generation.model.Category;
import com.generation.model.Client;
import com.generation.model.Product;

public class CorrezioneLambda extends BaseHandler
{
    public void es1()
    {
        //proiezione -> mappatura
        //Partiamo da una lista di prodotti      ->      arriviamo ad una Lista di Stringhe contententi alcuni dati
        List<Product> products = db.selectAllProducts();

        List<String> res =  products //Lista prodotti
                            .stream()//passaggio a Stream
                            .map(p -> p.getName() + " "+ (p.getGrossWeight() - p.getWeight()) +  " kg")//mappatura, la trasformazione
                            .collect(Collectors.toList());//Lista String

        System.out.println(res);
    }

    public void es2()
    {
        //filtro -> WHERE
        //Partiamo da una lista di clienti      ->      arriviamo ad una Lista di Clienti, ma con meno
        //Vogliamo solo nome e città -> map, quindi otterremo una Lista di String
        List<Client> clients = db.selectAllClients();

        List<String> res =  clients //Lista clienti
                            .stream()//passaggio a Stream
                            .filter(c -> c.getCountry().equals("Italy"))//filtro solo clienti italiani
                            .map(c -> c.getLegalName() + " " + c.getCity())//mappo a string Stream<String>
                            .collect(Collectors.toList());//Lista String

        System.out.println(res);
    }

    public void es3()
    {
        //Come mai partiamo da Category e non da Product
        List<Category> categories  = db.selectAllCategories();

        //Quando raggruppiamo cosa otteniamo? Mappa con key ciò per cui abbiamo raggruppato, value la lista di entita dell'insieme

        //Dopo aver raggruppato posso scegliere di fare una RIDUZIONE, vale a dire trasformare la lista di entità in un unico valore

        Map<String,Long> numProdCat =    categories         //devo passare da una Lista di Categories ad uno Stream<Product>
                                            .stream()
                                            .flatMap(c -> c.getProducts().stream())
                                            .collect(Collectors.groupingBy(p -> p.getCategory().getName(),Collectors.counting()));

        
    }
}
