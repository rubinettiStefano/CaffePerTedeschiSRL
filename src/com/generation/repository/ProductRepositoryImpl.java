package com.generation.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Product;

public class ProductRepositoryImpl implements IRepository<Product>
{
    private Connection con = ConnectionFactory.getConnection();
    private String tableName = "product";


    @Override
    public List<Product> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Product> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToProduct(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Product t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (name,description,categoryid,clientid,weight,grossweight,employeeid) VALUES (?,?,?,?,?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setInt(2, t.getCategory_id());
        pStatement.setInt(3, t.getClient_id());
        pStatement.setInt(4, t.getWeight());
        pStatement.setInt(5, t.getGrossWeight());
        pStatement.setInt(6, t.getEmployee_id());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Product t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET name=?,description=?,categoryid=?,clientid=?,weight=?,grossweight=?,employeeid=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setInt(2, t.getCategory_id());
        pStatement.setInt(3, t.getClient_id());
        pStatement.setInt(4, t.getWeight());
        pStatement.setInt(5, t.getGrossWeight());
        pStatement.setInt(6, t.getEmployee_id());
        pStatement.setInt(7, t.getId());



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

    private Product convertToProduct(ResultSet rs) throws SQLException 
    {
        Product e = new Product();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setDescription(rs.getString("description"));
        e.setCategory_id(rs.getInt("categoryid"));
        e.setClient_id(rs.getInt("clientid"));
        e.setWeight(rs.getInt("weight"));
        e.setGrossWeight(rs.getInt("grossweight"));
        e.setEmployee_id(rs.getInt("employeeid"));

        return e;
    }
}
