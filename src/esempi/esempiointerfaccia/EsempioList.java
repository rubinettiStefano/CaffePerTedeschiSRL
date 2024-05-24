package esempi.esempiointerfaccia;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EsempioList 
{
    public static void main(String[] args) 
    {
        List<String> parole = new LinkedList<>();
        
        parole.add("ciao");
        parole.add("arrivederci");
        parole.add("bye");
        parole.add("ciao");
        parole.add("ciao");
        parole.add("ciao");
        parole.add("ciao");
        parole.add("ciao");
        parole.add("ciao");

        System.out.println(parole);
    }
}
