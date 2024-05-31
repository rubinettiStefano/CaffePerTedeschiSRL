package com.generation.controller.idimenticati;

public class GarbageCollector 
{
    public static void main(String[] args) 
    {
        Student s = new Student();//STUDENTE1
        s.grades.add(10);
        s.grades.add(10);
        s.grades.add(10);

        boolean forte = metodo1(s);
       
        System.out.println(forte?"Sono fortissimo":"peccato");

        Student vincitore = metodo2(s);

        System.out.println("CHE BATTAGLIA");
    }

    private static Student metodo2(Student s) 
    {
        
        Student g = new Student();//STUDENTE3

        g.grades.add(6);
        g.grades.add(7);
        g.grades.add(8);

        for(int i=0;i<s.grades.size();i++)
        {
            if(g.grades.get(i)>=s.grades.get(i))
                return g;
        }
        return s;
    }


    private static boolean metodo1(Student s) 
    {
        
        Student g = new Student();//STUDENTE2

        g.grades.add(6);
        g.grades.add(7);
        g.grades.add(8);

        for(int i=0;i<s.grades.size();i++)
        {
            if(g.grades.get(i)>=s.grades.get(i))
                return false;
        }
        return true;
    }
}
