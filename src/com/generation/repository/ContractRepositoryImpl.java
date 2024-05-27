package com.generation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Contract;

public class ContractRepositoryImpl extends BaseRepository<Contract> implements IRepository<Contract>
{


    public ContractRepositoryImpl(String tableName) {
        super(tableName);
    }


    @Override
    public List<Contract> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Contract> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Contract t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (acceptedon,unitprice,quantity,productid) VALUES (?,?,?,?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setObject(1, t.getAcceptedOn());
        pStatement.setDouble(2, t.getUnitPrice());
        pStatement.setInt(3, t.getQuantity());
        pStatement.setInt(4, t.getProduct_id());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Contract t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET acceptedon=?,unitprice=?,quantity=?,productid WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setObject(1, t.getAcceptedOn());
        pStatement.setDouble(2, t.getUnitPrice());
        pStatement.setInt(3, t.getQuantity());
        pStatement.setInt(4, t.getProduct_id());
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


    @Override
    protected Contract convertToEntity(ResultSet rs) throws SQLException 
    {
        Contract e = new Contract();
        e.setId(rs.getInt("id"));
        e.setAcceptedOn(LocalDate.parse(rs.getString("acceptedon")));
        e.setUnitPrice(rs.getDouble("unitprice"));
        e.setQuantity(rs.getInt("quantity"));
        e.setProduct_id(rs.getInt("productid"));

        return e;
    }
}
