package com.generation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Batch;

public class BatchRepositoryImpl extends BaseRepository<Batch> implements IRepository<Batch>
{
    public BatchRepositoryImpl(String tableName) 
    {
        super(tableName);
    }

    @Override
    public List<Batch> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Batch> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Batch t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (productiondate,produced,discarded,status,contractid) VALUES (?,?,?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getProductionDate()+"");
        pStatement.setInt(2, t.getUnitsProduced());
        pStatement.setInt(3, t.getUnityDiscarded());
        pStatement.setString(4, t.getStatus());
        pStatement.setInt(5, t.getContractId());



        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Batch t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET productiondate=?,produced=?,discarded=?,status=?,contractid=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setObject(1, t.getProductionDate());
        pStatement.setInt(2, t.getUnitsProduced());
        pStatement.setInt(3, t.getUnityDiscarded());
        pStatement.setString(4, t.getStatus());
        pStatement.setInt(5, t.getContractId());


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


    protected Batch convertToEntity(ResultSet rs) throws SQLException 
    {
        Batch e = new Batch();
        e.setId(rs.getInt("id"));
        e.setProductionDate(LocalDate.parse(rs.getString("productiondate")));
        e.setUnitsProduced(rs.getInt("produced"));
        e.setUnityDiscarded(rs.getInt("discarded"));
        e.setStatus(rs.getString("status"));
        e.setContract_id(rs.getInt("contractid"));
        return e;
    }
}
