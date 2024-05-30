package com.generation.controller.lambda;

import com.generation.model.Product;

public interface CondizioneDiFiltro 
{
    /**
     * Restituisce TRUE se il prodotto rispetta la condizione
     * False altrimenti
     */
    boolean verificaCondizione(Product p);
}
