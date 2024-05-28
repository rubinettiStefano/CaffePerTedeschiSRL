package esempi.insiemistica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main 
{
    public static void main(String[] args) 
    {
        Set<String>  a = new HashSet<>();
        Set<String>  b = new HashSet<>();
        //discriminante -> iniziare con c
        a.add("cane");
        a.add("casa");
        a.add("cornacchie");
        a.add("cavolfiore");
        a.add("calotta");

        //discriminante -> finire con e
        b.add("cane");
        b.add("cornacchie");
        b.add("cavolfiore");
        b.add("lente");
        b.add("bottiglie");
        b.add("torte");

        //in java l'unione viene fatta tramite il metodo addAll
        //e, mannaggina a chi ha scritto il metodo, non produce un nuovo insieme
        //ma modifica quello su cui lo chiamiamo

        // Set<String>  c = new HashSet<>(a);//clono a
        // c.addAll(b);
        //c è l'insieme unione di a e b


        Set<String>  c = new HashSet<>(a);//clono a
        c.retainAll(b);
        //c è l'insieme intersezione di a con b
        //mantiene dentro c (clone di a) solo gli elementi presenti in b

        List<String> l = new ArrayList<>();
        l = new ArrayList<>(new HashSet<>(l));//versione vergognosa per eliminare i duplicati

        System.out.println(a);
        System.out.println("-------------");
        System.out.println(b);
        System.out.println("-------------");
        System.out.println(c);
    }
}
