package com.generation.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Category;
import com.generation.repository.abstraction.BaseRepository;
import com.generation.repository.abstraction.IRepository;

public class CategoryRepositoryImpl extends BaseRepository<Category> implements IRepository<Category>
{


    public CategoryRepositoryImpl(String tableName) {
        super(tableName);
    }


    @Override
    public List<Category> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Category> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

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



    @Override
    protected Category convertToEntity(ResultSet rs) throws SQLException {
        Category e = new Category();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        return e;
    }
}
