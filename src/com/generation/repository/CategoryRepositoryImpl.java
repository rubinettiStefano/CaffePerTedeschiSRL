package com.generation.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Category;

public class CategoryRepositoryImpl implements IRepository<Category>
{
     private Connection con = ConnectionFactory.getConnection();
    private String tableName = "category";


    @Override
    public List<Category> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Category> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToCategory(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Category t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (name) VALUES (?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Category t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET name=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setInt(3, t.getId());


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

    private String replaceTableName(String query)
    {
        return query.replace("[table]", tableName);
    }

    private Category convertToCategory(ResultSet rs) throws SQLException 
    {
        Category e = new Category();
        e.setId(rs.getInt("id"));

        return e;
    }
}
