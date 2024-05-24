package esempi.esempiointerfaccia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.generation.model.Employee;

public class MainListeSortate 
{
    public static void main(String[] args) 
    {
        List<Employee> nomi =  new ArrayList<>();
        
        Employee e1 = new Employee("Stefano", "Rubinetti");
        Employee e2 = new Employee("Andrea", "Poretta");
        Employee e3 = new Employee("Viktoriya", "Shnurovska");
        Employee e4 = new Employee("Gianluca", "Coccimiglio");
        Employee e5 = new Employee("Alessio", "Nordio");

        nomi.add(e1);
        nomi.add(e2);
        nomi.add(e3);
        nomi.add(e4);
        nomi.add(e5);
       

        Collections.sort(nomi);

        System.out.println(nomi);
    }
}
