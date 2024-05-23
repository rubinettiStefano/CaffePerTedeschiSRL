package com.generation.esempiointerfaccia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main 
{
    static SalvataggioSuFile sf = SalvataggioSuFileFactory.getInstance();
    public static void main(String[] args) 
    {
        //il suo obiettivo è quello di salvare un file con 5 numeri inseriti dall'utente
        Scanner term = new Scanner(System.in);
        List<Integer> interi = new ArrayList<>();

        
        for(int i=0;i<2;i++)
        {
            System.out.println("Inserisci numero");
            interi.add(Integer.parseInt(term.nextLine()));   
        }

        sf.salva(interi);
        System.out.println("Programma terminato, file creato");
    }
}






















































