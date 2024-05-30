package com.generation.controller.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BugiardinoStream 
{
    public static void main(String[] args) 
    {
        List<String> lista =Arrays.asList("apple,banana,cherry,date,elderberry,fig,grape,honeydew,kiwi,lemon,mango,nectarine,orange,papaya,quince,raspberry,strawberry,tangerine,ugli,watermelon".split(","));
        
        // filter       -> non cambia il tipo, vuole una lambda con un solo parametro che restituisca un boolean
        //  par -> espressioneBoolean
        lista
        .stream()//qui il tipo è Stream<String>
        .filter(par -> par.length()>5);//qui il tipo è Stream<String>, ma con meno elementi

        //map -> può (ma non è obbligato) cambiare il tipo del singolo elemento, applica la stessa trasformazione su ogni elemento
        // par -> qualsiasiCosa, che poi diventa il tipo del singolo elemento
        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.toUpperCase());//qui il tipo è Stream<String>

        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.length());//qui il tipo è Stream<Integer>

        lista
        .stream()//qui il tipo è Stream<String>
        .map(par -> par.split(""));//qui il tipo è Stream<String[]> [a,p,p,l,e], [b,a,n,a,n,a]


        //con il flatMap linearizziamo
        //vuole che la lamda sia   par -> Stream
        lista
        .stream()//qui il tipo è Stream<String>
        .flatMap(par -> Arrays.stream(par.split("")));//qui il tipo è Stream<String>  [a,p,p,l,e,b,a,n,a,n,a]


        //ricerca
        //si fa con un filtro e si prende solo il primo elemento
        String s =lista
        .stream()//qui il tipo è Stream<String>
        .filter(par -> par.length()>1000)
        .collect(Collectors.toList()).get(0);
    } 
}
