package esempi.esempiointerfaccia;

public class CalcolatriceIntera implements Calcolatrice<Integer>
{

    @Override
    public Integer somma(Integer i1, Integer i2) 
    {
        return i1+i2;
    }

    @Override
    public Integer moltiplicazione(Integer i1, Integer i2) 
    {
        return i1*i2;
    }

    @Override
    public Integer divisione(Integer i1, Integer i2)
    {
        return i1/i2;
    }

    @Override
    public Integer sottrazione(Integer i1, Integer i2)
    {
        return i1+i2;
    }

}
