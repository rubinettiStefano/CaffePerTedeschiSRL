package com.generation.controller.idimenticati;

import java.util.ArrayList;
import java.util.List;

public class BreakInsufficienze 
{
    public static void main(String[] args) 
    {
        Student s1 = new Student();
        s1.name = "Mario";
        s1.grades.add(10);
        s1.grades.add(6);
        s1.grades.add(2);
        s1.grades.add(10);


        Student s2 = new Student();
        s2.name = "Paolo";
        s2.grades.add(9);
        s2.grades.add(4);
        s2.grades.add(10);
        s2.grades.add(10);

        Student s3 = new Student();
        s3.name = "Pluto";
        s3.grades.add(3);
        s3.grades.add(10);
        s3.grades.add(6);
        s3.grades.add(10);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);


        //voglio che stampi "[NOME] INSUFFICIENTE" alla prima insufficienza trovata

        int numeroCicli = 0;

        //Mario ha i voti  10  6  10  10       //4   STAMPA 10
        //Paolo ha i voti  9   4  10  10       //2
        //Pluto ha i voti  9   10  6  10       //4

        //Mario ha i voti  10  6  2  10       //3
        //Paolo ha i voti  9   4  10  10      //2
        //Pluto ha i voti  3   10  6  10      //1

        esterno: for(int i=0;i<students.size();i++) //sto chiamando questo ciclo esterno
        {
            Student inEsame = students.get(i);
            
            for(int y=0;y<inEsame.grades.size();y++)
            {
                numeroCicli++;

                if(inEsame.grades.get(y)<6)
                {
                    System.out.println(inEsame.name + " INSUFFICIENTE");
                    break esterno;
                }
            }
        }
        //RISPOSTA 1 -> 9
        //RISPOSTA 2 -> 4
        //RISPOSTA 3 -> 2
        //RISPOSTA 4 -> 5


        for(int i=0;i<students.size();i++)
        {
            Student inEsame = students.get(i);
            
            for(int y=0;y<inEsame.grades.size();y++)
            {
                numeroCicli++;

                if(inEsame.grades.get(y)<6)
                {
                    System.out.println(inEsame.name + " INSUFFICIENTE");
                    continue;
                }
            }
        }

        for(Student s:students)
            if(s.sonoInsufficiente())
            {
                System.out.println(s.name + " INSUFFICIENTE");
                break;
            }

        System.out.println("Ho eseguito "+numeroCicli+" cicli");

    }
}
