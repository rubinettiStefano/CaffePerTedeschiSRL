package com.generation.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory 
{
    private static Connection con;

    public static Connection getConnection()
    {
        if(con!=null)
            return con;

        String dbUrl = "jdbc:mysql://localhost:3306/caffepertedeschi";
        try 
        {
            //ne avremo una sola in tutto il programma
            con = DriverManager.getConnection(dbUrl, "jaita", "jaita121");
            return con;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }
}
