package com.generation.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.model.Entity;

public interface IRepository <T extends Entity>
{
    List<T> select(String condition) throws SQLException ;
    void insert(T t) throws SQLException ;
    void update(T t) throws SQLException ;
    void delete(int id) throws SQLException ;
    

    default List<T> selectAll() throws SQLException 
    {
        return select("1=1");
    }

    default T select(int id) throws SQLException 
    {
        return select("id="+id).get(0);
    }
    
    default void insert(List<T> t) throws SQLException 
    {
        for(T e : t)
            insert(e);
    }
    
    default void update(List<T> t) throws SQLException 
    {
        for(T e : t)
            update(e);
    }
    
    default void delete(T t) throws SQLException 
    {
        delete(t.getId());
    }

    default void delete(int[] ids) throws SQLException 
    {
        for(int i:ids)
            delete(i);
    }
    
    default void delete(List<T> t) throws SQLException 
    {
        for(T e : t)
            delete(e);
    }
    
}
