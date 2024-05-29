package com.generation.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Product;
import com.generation.repository.abstraction.BaseRepository;
import com.generation.repository.abstraction.IRepository;

public class ProductRepositoryImpl extends BaseRepository<Product> implements IRepository<Product>
{

    public ProductRepositoryImpl(String tableName) 
    {
        super(tableName);
    }

    @Override
    public List<Product> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Product> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

        ps.close();
        return res;
    }

    @Override
    public void insert(Product t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (name,description,weight,grossweight,employeeid,clientid,categoryid) VALUES (?,?,?,?,?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setString(2, t.getDescription());
        pStatement.setInt(3, t.getWeight());
        pStatement.setInt(4, t.getGrossWeight());
        pStatement.setInt(5, t.getEmployee_id());
        pStatement.setInt(6, t.getClient_id());
        pStatement.setInt(7, t.getCategory_id());


        pStatement.execute();
        pStatement.close();
    }

    @Override
    public void update(Product t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET name=?,description=?,weight=?,grossweight=?,employeeid=?,clientid=?,categoryid=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setString(2, t.getDescription());
        pStatement.setInt(3, t.getWeight());
        pStatement.setInt(4, t.getGrossWeight());
        pStatement.setInt(3, t.getWeight());
        pStatement.setInt(5, t.getEmployee_id());
        pStatement.setInt(6, t.getClient_id());
        pStatement.setInt(7, t.getCategory_id());
        pStatement.setInt(8, t.getId());


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
    protected Product convertToEntity(ResultSet rs) throws SQLException 
    {
        Product e = new Product();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setDescription(rs.getString("description"));
        e.setWeight(rs.getInt("weight"));
        e.setGrossWeight(rs.getInt("grossweight"));
        e.setEmployee_id(rs.getInt("employeeid"));
        e.setClient_id(rs.getInt("clientid"));
        e.setCategory_id(rs.getInt("categoryid"));

        return e;
    }

}
