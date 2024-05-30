package com.generation.controller.lambda;

public class Student 
{
    public String name,surname;
    public String section;
    public double mathGrade;
    public int year;
    public Student(String name, String surname, String section, double mathGrade,int year) {
        this.name = name;
        this.surname = surname;
        this.section = section;
        this.mathGrade = mathGrade;
        this.year = year;
    }
    @Override
    public String toString() {
        return "{name=" + name + "}";
    }

    
}
