package com.generation.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.model.Employee;
import com.generation.repository.abstraction.BaseRepository;
import com.generation.repository.abstraction.IRepository;

public class EmployeeRepositoryImpl extends BaseRepository<Employee> implements IRepository<Employee>
{
    
    public EmployeeRepositoryImpl(String tablename)
    {
        super(tablename);
    }

    @Override
    public List<Employee> select(String condition) throws SQLException 
    {
        PreparedStatement ps = con.prepareStatement(replaceTableName("SELECT * FROM [table] WHERE ")+condition);

        List<Employee> res = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            res.add(convertToEntity(rs));

        ps.close();
        return res;
    }


    @Override
    public void insert(Employee t) throws SQLException 
    {
        String query = replaceTableName("INSERT INTO [table] (name,surname) VALUES ( ?,  ?);");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setString(2, t.getSurname());


        pStatement.execute();
        pStatement.close();
    }


    @Override
    public void update(Employee t) throws SQLException 
    {
        String query = replaceTableName("UPDATE [table] SET name=?,surname=? WHERE id=?");
        
        PreparedStatement pStatement = con.prepareStatement(query);
        pStatement.setString(1, t.getName());
        pStatement.setString(2, t.getSurname());
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
    protected Employee convertToEntity(ResultSet rs) throws SQLException 
    {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setSurname(rs.getString("surname"));

        return e;
    }
}
