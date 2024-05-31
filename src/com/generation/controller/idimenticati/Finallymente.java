package com.generation.controller.idimenticati;

import java.util.Scanner;

public class Finallymente 
{
    public static void main(String[] args) 
    {
        String input = new Scanner(System.in).nextLine();



       boolean riuscito = stampaCavolate(input);
        
    }

    public static boolean stampaCavolate(String input)
    {

        try 
        {
            int a = Integer.parseInt(input);
            System.out.println("Hai inserito "+a);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Non è un numero");
            return false;
        }
        finally
        {
            //lui è inevitabile, ineluttabile, niente lo può fermare
            //se avviene eccezione fa il close della risorsa
            System.out.println("Io mi stampo comunque");
        }
    }

}
