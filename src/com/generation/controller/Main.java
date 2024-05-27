package com.generation.controller;

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner term = new Scanner(System.in);
        String cmd="";
        Handler1 h = new Handler1();

        do 
        {
            System.out.println("Inserisci comando");
            cmd = term.nextLine().toLowerCase();

            switch (cmd) 
            {
                case "changelang":
                    h.changeLanguage();
                break;
                case "printclients":
                    h.printAllClient();
                break;
                case "insieme":
                    h.stampareProdottiDiCatBevEClienteItaliano();
                break;
                case "quit":
                    System.out.println("Ciao ciao");
                break;
                default:
                    System.out.println("Comando non riconosciuto");
                break;
            }
        

        } 
        while (!cmd.equals("quit"));
    }
}
