package com.generation.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Review;
import com.generation.repository.abstraction.BaseRepository;
import com.generation.repository.abstraction.IRepository;

public class ReviewRepositoryImpl extends BaseRepository<Review> implements IRepository<Review>
{
    public ReviewRepositoryImpl(String tableName) 
    {
        super(tableName);
    }




    @Override
    public List<Review> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Review> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

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



    @Override
    protected Review convertToEntity(ResultSet rs) throws SQLException 
    {
        Review e = new Review();
        e.setId(rs.getInt("id"));
        e.setCotent(rs.getString("content"));
        e.setScore(rs.getDouble("score"));
        e.setProduct_id(rs.getInt("productid"));

        return e;
    }
}
