package com.generation.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Review;

public class ReviewRepositoryImpl implements IRepository<Review>
{
    private Connection con = ConnectionFactory.getConnection();
    private String tableName = "review";


    @Override
    public List<Review> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Review> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToReview(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Review t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (content,score,productid) VALUES (?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getContent());
        pStatement.setDouble(2, t.getScore());
        pStatement.setInt(3, t.getProduct_id());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Review t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET content=?,score=?,productid=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getContent());
        pStatement.setDouble(2, t.getScore());
        pStatement.setInt(3, t.getProduct_id());
        pStatement.setInt(4, t.getId());


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

    private Review convertToReview(ResultSet rs) throws SQLException 
    {
        Review e = new Review();
        e.setId(rs.getInt("id"));
        e.setCotent(rs.getString("content"));
        e.setScore(rs.getDouble("score"));
        e.setProduct_id(rs.getInt("productid"));

        return e;
    }
}
