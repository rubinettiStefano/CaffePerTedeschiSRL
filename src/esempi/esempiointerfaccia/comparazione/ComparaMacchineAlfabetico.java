package esempi.esempiointerfaccia.comparazione;

import java.util.Comparator;

public class ComparaMacchineAlfabetico implements Comparator<Car>
{

    @Override
    public int compare(Car o1, Car o2) 
    {
        return o1.model.compareTo(o2.model);
    }

}
