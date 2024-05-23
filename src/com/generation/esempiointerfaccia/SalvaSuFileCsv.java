package com.generation.esempiointerfaccia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalvaSuFileCsv implements SalvataggioSuFile
{

    @Override
    public void salva(ArrayList<Integer> interi) 
    {
         try 
        {
            String filename = "fileNum_"+(int)(Math.random()*10000)+".csv";
            FileWriter fw = new FileWriter(filename);

            StringBuilder content = new StringBuilder();

            content.append("numero\n");

            for(int i:interi)
                content.append(i).append(",");

            content.deleteCharAt(content.length()-1);
            fw.write(content.toString());
            fw.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

}
