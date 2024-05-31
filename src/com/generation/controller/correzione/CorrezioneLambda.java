package com.generation.controller.correzione;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.generation.controller.BaseHandler;
import com.generation.model.Batch;
import com.generation.model.Category;
import com.generation.model.Client;
import com.generation.model.Contract;
import com.generation.model.Employee;
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

        //con questo calcolo ad esempio il peso totale
        Map<String,Integer> numProdCat =    categories         //devo passare da una Lista di Categories ad uno Stream<Product>
                                            .stream()
                                            .flatMap(c -> c.getProducts().stream())
                                            .collect(
                                                        Collectors.groupingBy(
                                                                                p -> p.getCategory().getName(),
                                                                                Collectors.summingInt(p -> p.getWeight())
                                                                             )
                                                    );

        
        Map<String,Integer> res =   categories
                                    .stream()
                                    .collect(Collectors.toMap(c -> c.getName(), c -> c.getProducts().size()));
    }

    public void es4()
    {
        List<Product> products = db.selectAllProducts();
        List<String> res =  products
                            .stream()
                            .filter(p -> p.getWeight()>350)
                            .map(p -> p.getName() + " "+p.getDescription()+ " "+ (p.getWeight()*1000) +" gr")
                            .collect(Collectors.toList());
    }

    
    public void es5()
    {
        List<Product> products = db.selectAllProducts();

        Map<String,Double> res =    products
                                    .stream()
                                    .flatMap(p -> p.getReviews().stream())
                                    .collect(
                                                Collectors.groupingBy(
                                                                        r -> r.getProduct().getName(),
                                                                        Collectors.averagingDouble(r->r.getScore())
                                                                      )
                                            );

    }

    public void es6()
    {
        List<Contract> contracts = db.selectAllContracts();

        Map<Integer,Long> contrattiPerMese =    contracts
                                                .stream()
                                                .filter(c -> c.getAcceptedOn().getYear()==2024)
                                                .collect(
                                                            Collectors.groupingBy(
                                                                                    c-> c.getAcceptedOn().getMonthValue(),
                                                                                    Collectors.counting()
                                                            )
                                                );
    }

    public void es7()
    {
        List<Employee> employees = db.selectAllEmployees();

        List<String> res =      employees
                                .stream()
                                .filter(p -> p.getProducts().size()>0)
                                .map(p -> p.getName() +" "+p.getSurname() + " ha prodotto "+p.getProducts().size()+" prodotti")
                                .collect(Collectors.toList());
    }

    public void es8()
    {
        List<Product> products = db.selectAllProducts();

        // Map<String,String> res =  products
        //                                 .stream()
        //                                 .flatMap(p -> p.getContracts().stream())
        //                                 .flatMap(c -> c.getBatches().stream())
        //                                 .filter(b -> b.getStatus().equals("completed"))
        //                                 .collect(
        //                                             Collectors.groupingBy(
        //                                                 b -> b.getContract().getProduct().getName(),
        //                                                 Collectors.collectingAndThen(
        //                                                 Collectors.toList(), 
        //                                                 l ->
        //                                                 {
        //                                                     int totaleProdotto = ((Collection<Batch>) l).stream().collect(Collectors.summingInt(b -> b.getUnitsProduced()));
        //                                                     int totaleScartata =  ((Collection<Batch>) l).stream().collect(Collectors.summingInt(b -> b.getUnityDiscarded()));
        //                                                     int totalePercScart =  totaleScartata/(totaleProdotto+totaleScartata) *100;
        //                                                     String fin = "Ho "+totaleProdotto+" pezzi prodotti, "+totaleScartata+" pezzi scartati, con una percentuale di scarto del "+totalePercScart+"%";
        //                                                     return fin;                                                         
        //                                                 }
        //                                             )
        //                                         )
        //                                 );
        Map<String,String> toPrint = new HashMap<>();

        products
        .stream()
        .flatMap(p -> p.getContracts().stream())
        .flatMap(c -> c.getBatches().stream())
        .filter(b -> b.getStatus().equalsIgnoreCase("completed"))
        .collect(Collectors.groupingBy( b -> b.getContract().getProduct().getName()))
        .entrySet()
        .forEach(
                    entry -> 
                    {
                        List<Batch> batches = entry.getValue();
                        int totaleProdotto = batches.stream().collect(Collectors.summingInt(b -> b.getUnitsProduced()));
                        int totaleScartata = batches.stream().collect(Collectors.summingInt(b -> b.getUnityDiscarded()));
                        int totalePercScart =  totaleScartata/(totaleProdotto+totaleScartata) *100;
                        String fin = "Ho "+totaleProdotto+" pezzi prodotti, "+totaleScartata+" pezzi scartati, con una percentuale di scarto del "+totalePercScart+"%";
                        toPrint.put(entry.getKey(), fin);
                    }
                );
       


        

        for(Entry<String,List<Batch>> entry : res.entrySet())
        {
            List<Batch> batches = entry.getValue();
            int totaleProdotto = batches.stream().collect(Collectors.summingInt(b -> b.getUnitsProduced()));
            int totaleScartata = batches.stream().collect(Collectors.summingInt(b -> b.getUnityDiscarded()));
            int totalePercScart =  totaleScartata/(totaleProdotto+totaleScartata) *100;
            String fin = "Ho "+totaleProdotto+" pezzi prodotti, "+totaleScartata+" pezzi scartati, con una percentuale di scarto del "+totalePercScart+"%";
            toPrint.put(entry.getKey(), fin);
        }


        System.out.println(toPrint);

     

        List<Batch> batches2 =  products
                                .stream()
                                .flatMap(p -> p.getContracts().stream())
                                .flatMap(c -> c.getBatches().stream())
                                .filter(b -> b.getStatus().equals("completed"))
                                .collect(Collectors.toList());

           //soluzione senza Stream
        List<Batch> batches =  new ArrayList<>();


        for(Product p : products)
            for(Contract c : p.getContracts())
                for(Batch b: c.getBatches())
                    if(b.getStatus().equalsIgnoreCase("completed"))
                        batches.add(b);
    }

}
