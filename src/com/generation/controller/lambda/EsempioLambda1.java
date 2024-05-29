package com.generation.controller.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.generation.controller.BaseHandler;
import com.generation.model.Category;
import com.generation.model.Product;
import com.generation.model.Review;

public class EsempioLambda1 extends BaseHandler
{

    List<String> parole = new ArrayList<>();

    public EsempioLambda1()
    {
          // Aggiungi 20 parole alla lista
        parole.add("apple");
        parole.add("banana");
        parole.add("cherry");
        parole.add("date");
        parole.add("elderberry");
        parole.add("fig");
        parole.add("grape");
        parole.add("honeydew");
        parole.add("kiwi");
        parole.add("lemon");
        parole.add("mango");
        parole.add("nectarine");
        parole.add("orange");
        parole.add("papaya");
        parole.add("quince");
        parole.add("raspberry");
        parole.add("strawberry");
        parole.add("tangerine");
        parole.add("ugli");
        parole.add("watermelon");
    }
    
    public void es1()
    {
        //giochiamo con le String
      

        // List<String> res = new ArrayList<>();

        // for(String s : parole)
        //     if(s.endsWith("e"))
        //         res.add(s);

        //I programmatori adorano usare strutture dati diverse



        List<String> res =   parole
                            .stream() //trasformato la List<String> in uno Stream<String>
                            //s -> s.endsWith("e")     chiama ogni elemento dello Stream     s
                            //su ognuno esegui il metodo endsWith
                            //se quel metodo applicato sull'elemento restituisce TRUE allora lo tieni
                            //se resistituisce false lo scarti
                            .filter(s -> s.endsWith("e")) //Andreamo a creare un PREDICATE tramite lambda
                            //Finiamo con il COLLEZIONARE lo stream
                            //lo trasformiamo di nuovo in una lista,set, vettore,mappa, quello che vogliamo
                            .collect(Collectors.toList());

        System.out.println(res);

    }

    public void es2()
    {
        
        // List<String> res = new ArrayList<>();

        // for(String s : parole)
        // {
        //     String vMaiusc = s.toUpperCase();
        //     res.add(vMaiusc);
        // }

        List<String> res =  parole
                            .stream()
                            .map(s -> s.substring(0,1).toUpperCase()+s.substring(1))//trasformazione applicata a tutti gli elementi di un insieme
                            .collect(Collectors.toList());

        System.out.println(res);
    }

    public void es3()
    {
        // List<String> res = new ArrayList<>();
        
        // for(String s : parole)
        //     if(s.endsWith("e"))
        //     {
        //         String t = s.substring(0,1).toUpperCase()+s.substring(1);
        //         res.add(t);
        //     }

        // for(String s : parole)
        //     if(s.endsWith("e"))
        //         temp.add(s);

        // for(String s : temp)
        // {
        //     String t = s.substring(0,1).toUpperCase()+s.substring(1);
        //     res.add(t);
        // }

        List<String> res =  parole
                            .stream()
                            .filter(s -> s.endsWith("e"))
                            .map(s -> s.substring(0,1).toUpperCase()+s.substring(1))
                            .collect(Collectors.toList());

        System.out.println(res);
    }

    public void es4()
    {

        // Double res =        parole
        //                     .stream()
        //                     .map(s -> s.length())
        //                     .collect(Collectors.averagingInt(Integer::intValue)); //AVG(s.length())


        String csv =        parole
                            .stream()
                            .collect(Collectors.joining(",")); 
                   
        System.out.println(csv);
    }

    private void esempio()
    {
        List<Category> cAll = db.selectAllCategories();

        List<Product> allProds = new ArrayList<>();

        List<List<Product>> b;    //qui lo scaffale contiene prodotti divisi in scatoloni
        
        //flattening      ->  linearizzazione
        List<Product>       a;    //qui li metto tutti in uno scaffalone


        List<List<Product>> listaDiListe = new ArrayList<>();
        for(Category c: cAll)

            for(Product p : c.getProducts())
                {
                    //lavoro su singolo prodotto
                }


        for(Category c : cAll)
        {
            List<Product> prods = c.getProducts();

            //listaDiListe.add(prods);
            allProds.addAll(prods);

        }
    }


    public void es5()
    {
        List<Category> cAll = db.selectAllCategories();
        //parto dalle category perchè solo cosi le posso avere
        //filtro per tenere solo le category con nome Beverages
        //mappatura e flattenizazione
        //filtro per tenere i product con almeno una recensione positiva
        //colleziono tutto ad una lista di prodotti

        List<Product> res =     cAll                                                //List<Category>
                                .stream()                                           //Stream<Category>
                                .filter(c -> c.getName().equals("Beverages"))
                                .flatMap(c -> c.getProducts().stream()) // c -> c.getProducts().stream() -> Stream<Stream<Product>>  ->  Stream<Product>
                                .filter(
                                    p -> 
                                    {
                                        for(Review r : p.getReviews())
                                            if(r.getScore()>4)
                                                return true;
                                        
                                        return false;
                                    }
                                )
                                .collect(Collectors.toList());
                                
        System.out.println(getView("product").renderAll((List<Object>)(List<?>)res));
                                //Stream<Product>                      
    }


    public void printSuccessfullBeverages() 
    {

        List<Review> all = db.selectAllReviews();
        List<Category> cAll = db.selectAllCategories();

        String acc = "Beverages";
        for (Category c : cAll)
            if (c.getName().equals(acc))
                for (Product p : c.getProducts())
                    for (Review r : all)
                        if (r.getProduct_id() == p.getId() && r.getScore() > 4.7)
                            System.out.println(getView("product").renderOne((p)));
    }

}
