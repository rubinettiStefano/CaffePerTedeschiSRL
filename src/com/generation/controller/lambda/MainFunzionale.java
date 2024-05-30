package com.generation.controller.lambda;

public class MainFunzionale 
{
    public static void main(String[] args) 
    {
        Matematica m1 = new MatematicaImpl();
        
        Matematica m2 =  new Matematica() //in automatico viene istanziato un oggetto della classe anonima
                        {           
                            //tra le graffe ho una CLASSE ANONIMA
                            //una classe usa e getta
                            //è una classe anonima che fa implements dell'interfaccia Matematica
                            @Override
                            public double calcola(double a, double parino) 
                            {
                                return a+parino;
                            }
                        };


                            //parametri
        Matematica m3 =     (pippo,pluto) -> //prima della freccia mettiamo semplicemente i nomi dei parametri
                            {//corpo
                                return pippo+pluto;
                            };

        Matematica m4 =  (pippo,pluto) -> pippo+pluto;


        System.out.println(m2.calcola(10, 20));
    }

    //CALLBACK
    //Un metodo (funzione) che ne riceve un altro come parametro e lo usa al suo interno
    
}
