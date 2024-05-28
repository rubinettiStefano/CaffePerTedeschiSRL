package com.generation.controller;

public interface Es2 
{
    //UTILIZZARE SEMPRE i selectALL del repositoriesService

    /**
     * Stampare la somma di tutti i pezzi prodotti nei batch
     * RIDUZIONE
     */
    void printTotalPiecesProduced();

      /**
     * Stampare la media della quantity dei contratti
     * RIDUZIONE
     */
    void printAverageQuantity();

    /**
     * Chiede un determinato id all'utente e stampare il prodotto con quell'id
     * NON USARE QUERY CONDIZIONALE, leggere tutti i prodotti e fare la ricerca in JAVA
     * se il prodotto non esiste, stampare PRODOTTO NON TROVATO
     * RICERCA
     */
    void printProductWithId();


     /**
     * Stampare l'impiegato con più lettere tra nome e cognome (con il nominativo piu lungo)
     * RICERCA DEL MASSIMO
     */
    void printMostVerbosoEmployee();


    /**
     * Stampare i batch con status ->  "Failed"
     * FILTRO
     */
    void printFailedBatches();

     /**
     * Stampare i batch con status ->  "Failed" e productiondate negli ultimi 30 giorni
     * FILTRO
     */
    void printRecentFailedBatches();

    /**
     * Stampare i prodotti con category -> "Beverages" e almeno una review sopra 4.7
     * FILTRO
     */
    void printSuccessfullBeverages();

    /**
     * Ottenere una lista di double con il wastedIncome di ogni batch
     * e stampare
     * MAPPATURA
     */
    void printWastedIncomesBatches();

      /**
     * Ottenere una lista di String con l'indirizzo completo di ogni cliente (es: Italy, Turin, via Garibaldi 10)
     * e stampare
     * MAPPATURA
     */
    void printFullAddresses();

}
