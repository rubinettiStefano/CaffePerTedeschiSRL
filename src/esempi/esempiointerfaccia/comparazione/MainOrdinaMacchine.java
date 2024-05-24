package esempi.esempiointerfaccia.comparazione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainOrdinaMacchine 
{
    public static void main(String[] args) 
    {
        Car c2 = new Car();
        c2.model = "Virgola";
        c2.price = 15000;

        Car c3 = new Car();
        c3.model = "Alfa";
        c3.price = 20000;

        Car c1 = new Car();
        c1.model = "Punto";
        c1.price = 10000;
        
        
        

        List<Car> cars = new ArrayList<>();

        cars.add(c2);
        cars.add(c3);
        cars.add(c1);
        ComparaMacchineAlfabetico comp = new ComparaMacchineAlfabetico();

        Collections.sort(cars);

        // List<Comparable> oggettiDaComparare = new ArrayList<>();
        // oggettiDaComparare.add(c2);
        // oggettiDaComparare.add(c3);
        // oggettiDaComparare.add(c1);


        // Collections.sort(oggettiDaComparare);
        System.out.println(cars);
    }
}
