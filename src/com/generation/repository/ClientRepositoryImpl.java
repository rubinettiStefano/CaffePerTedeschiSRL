package com.generation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Client;

public class ClientRepositoryImpl extends BaseRepository<Client> implements IRepository<Client> 
{


    public ClientRepositoryImpl(String tableName) {
        super(tableName);
    }


    @Override
    public List<Client> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Client> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToClient(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Client t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (legalname,address,city,country) VALUES (?,?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getLegalName());
        pStatement.setString(2, t.getAddress());
        pStatement.setString(3, t.getCity());
        pStatement.setString(4, t.getCountry());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Client t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET legalname=?,address=?,city=?,country=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getLegalName());
        pStatement.setString(2, t.getAddress());
        pStatement.setString(3, t.getCity());
        pStatement.setString(4, t.getCountry());
        pStatement.setInt(5, t.getId());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void delete(int id) throws SQLException 
    {
        String query = replaceTableName("DELETE FROM [table] WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setInt(1,id);


        pStatement.execute();
        pStatement.close();
    }


    private Client convertToClient(ResultSet rs) throws SQLException 
    {
        Client e = new Client();
        e.setId(rs.getInt("id"));
        e.setLegalName(rs.getString("legalname"));
        e.setAddress(rs.getString("address"));
        e.setCity(rs.getString("city"));
        e.setCountry(rs.getString("country"));

        return e;
    }


    @Override
    protected Client convertToEntity(ResultSet rs) throws SQLException 
    {
        Client e = new Client();
        e.setId(rs.getInt("id"));
        e.setLegalName(rs.getString("legalname"));
        e.setAddress(rs.getString("address"));
        e.setCity(rs.getString("city"));
        e.setCountry(rs.getString("country"));

        return e;
    }
}
