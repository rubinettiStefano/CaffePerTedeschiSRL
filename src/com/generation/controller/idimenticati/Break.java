package com.generation.controller.idimenticati;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Break 
{
    public static void main(String[] args) 
    {
        Scanner term  = new Scanner(System.in);
        //il break interrompe uno switch o un CLICLO
        int counter = 0;
        while(true)//ciclo infinito
        {
            counter++;
            System.out.println("ciao"+counter);

            System.out.println("Premi q per uscire, enter per continuare");
            if(term.nextLine().equals("q"))
                break;

            System.out.println("andiamo avanti");
        }

        //voglio trovare i primi 3 numeri maggiori di  50
        int[] v = new int[]{5,100,5,22,150,14,35,53,1,22,33,412,44};

        List<Integer> res = new ArrayList<>();

        for(int n : v)
            if(n>50)
            {
                res.add(n);
                if(res.size()==3)
                    break;
            }

        for(int i=0;i<v.length && res.size()<3;i++)
            if(v[i]>50)
                res.add(v[i]);
                
        System.out.println("programma terminato");
    }
}
