package com.generation.controller.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BugiardinoStream 
{
    public static void main(String[] args) 
    {
        List<String> lista =Arrays.asList("apple,banana,cherry,date,elderberry,fig,grape,honeydew,kiwi,lemon,mango,nectarine,orange,papaya,quince,raspberry,strawberry,tangerine,ugli,watermelon".split(","));
        
        List<Student> students = new ArrayList<>();

        students.add(new Student("John", "Doe", "A", 85.5, 1));
        students.add(new Student("Jane", "Smith", "A", 92.3, 1));
        students.add(new Student("Emily", "Johnson", "A", 74.8, 2));
        students.add(new Student("Michael", "Brown", "A", 88.1, 2));
        students.add(new Student("Chris", "Davis", "A", 79.4, 3));

        students.add(new Student("Linda", "Wilson", "B", 91.2, 1));
        students.add(new Student("James", "Martinez", "B", 67.8, 1));
        students.add(new Student("Patricia", "Anderson", "B", 82.0, 2));
        students.add(new Student("Robert", "Thomas", "B", 89.6, 3));
        students.add(new Student("Mary", "Jackson", "B", 95.4, 3));

        students.add(new Student("David", "White", "C", 73.1, 1));
        students.add(new Student("Barbara", "Harris", "C", 88.9, 2));
        students.add(new Student("Richard", "Clark", "C", 76.5, 2));
        students.add(new Student("Susan", "Lewis", "C", 85.7, 3));
        students.add(new Student("Joseph", "Walker", "C", 80.3, 3));

        students.add(new Student("Sarah", "Hall", "D", 90.1, 1));
        students.add(new Student("Daniel", "Allen", "D", 78.2, 1));
        students.add(new Student("Laura", "Young", "D", 83.5, 2));
        students.add(new Student("Matthew", "King", "D", 77.4, 3));
        students.add(new Student("Nancy", "Wright", "D", 86.9, 3));


        // filter       -> non cambia il tipo, vuole una lambda con un solo parametro che restituisca un boolean
        //  par -> espressioneBoolean
        lista
        .stream()//qui il tipo è Stream<String>
        .filter(par -> par.length()>5);//qui il tipo è Stream<String>, ma con meno elementi

        //map -> può (ma non è obbligato) cambiare il tipo del singolo elemento, applica la stessa trasformazione su ogni elemento
        // par -> qualsiasiCosa, che poi diventa il tipo del singolo elemento
        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.toUpperCase());//qui il tipo è Stream<String>

        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.length())//qui il tipo è Stream<Integer>
        .collect(Collectors.toList());//produciamo una Lista di Integer
        
        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.split(""));//qui il tipo è Stream<String[]> [a,p,p,l,e], [b,a,n,a,n,a]


        //con il flatMap linearizziamo
        //vuole che la lamda sia   par -> Stream
        lista
        .stream()//qui il tipo è Stream<String>
        .flatMap(par -> Arrays.stream(par.split("")));//qui il tipo è Stream<String>  [a,p,p,l,e,b,a,n,a,n,a]


        //ricerca
        //si fa con un filtro e si prende solo il primo elemento
        lista
        .stream()//qui il tipo è Stream<String>
        // .filter(par -> par.length()>9)/par.id==10 nella realtà
        .collect(Collectors.toList()).get(0);

        //RAGGRUPPAMENTO
        Map<String,List<Student>> raggruppatiPerSezione =students
                                                        .stream()
                                                        .collect(Collectors.groupingBy(s -> s.year+s.section));//GROUP BY student.section

         //RAGGRUPPAMENTO e riduzione
         Map<String,Double> mediavotiTerzoAnno =  
                students
                .stream()
                .filter(s -> s.year==3)
                .collect(Collectors.groupingBy(s -> s.year+s.section,Collectors.averagingDouble(s -> s.mathGrade)));
        //Equivalente in sql
        // SELECT   concat(year,section), AVG(mathGrade)
        // FROM STUDENT
        // GROUp BY concat(year,section)


        System.out.println(mediavotiTerzoAnno);
    } 
}
