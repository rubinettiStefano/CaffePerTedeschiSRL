package com.generation.esempiointerfaccia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalvaSuFileACapo implements SalvataggioSuFile
{

    @Override
    public void salva(ArrayList<Integer> interi) 
    {
        try 
        {
            String filename = "fileNum_"+(int)(Math.random()*10000)+".txt";
            FileWriter fw = new FileWriter(filename);

            StringBuilder content = new StringBuilder();

            content.append("I tuoi numeri sono:\n");

            for(int i:interi)
                content.append(i).append("\n");

            content.append("File Finito");
            fw.write(content.toString());
            fw.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }

}
