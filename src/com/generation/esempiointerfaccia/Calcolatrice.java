package com.generation.esempiointerfaccia;

public interface Calcolatrice<T> 
{
    T somma(T i1,T i2);
    T moltiplicazione(T i1,T i2);
    T divisione(T i1,T i2);
    T sottrazione(T i1,T i2);
}
