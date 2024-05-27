package com.generation.controller;

public interface Es1 
{
    /**
     * Deve stampare tutti i clienti SENZA i loro prodotti
     */
    void printAllClient();

    /**
     * Deve chiedere all'utente 2 date e stampare solo i contratti compresi tra quelle date
     */
    void  printContractByInterval();

     /**
     * Deve chiedere all'utente 2 date e stampare quanto abbiamo guadagnato per i contratti compresi tra quelle date
     */
    void  printRevenueByInterval();

     /**
     * Deve chiedere all'utente una nazione, stampare tutti i contratti fatti ad un cliente di quella nazione
     */
    void  printContractByNation();
}
