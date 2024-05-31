package com.generation.controller.idimenticati;

import java.util.ArrayList;
import java.util.List;

public class Continue 
{
    public static void main(String[] args) 
    {
        Student s1 = new Student();
        s1.name = "Mario";
        s1.grades.add(10);
        s1.grades.add(6);
        s1.grades.add(2);
        s1.grades.add(10);
        s1.immunita = false;


        Student s2 = new Student();
        s2.name = "Paolo";
        s2.grades.add(9);
        s2.grades.add(4);
        s2.grades.add(10);
        s2.grades.add(10);
        s2.immunita  = true;

        Student s3 = new Student();
        s3.name = "Pluto";
        s3.grades.add(3);
        s3.grades.add(10);
        s3.grades.add(6);
        s3.grades.add(10);
        s3.immunita = false;

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        for(Student s:students)
        {
            if(s.immunita)
                continue; //ferma qui il giro CORRENTE e passa subito al prossima

            if(s.sonoInsufficiente())
            {
                System.out.println(s.name + " INSUFFICIENTE");
            }
        }
           
    }
}
