package com.generation.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class BaseRepository <T>
{
    protected Connection con = ConnectionFactory.getConnection();
    protected String tableName;

    public BaseRepository (String tableName)
    {
        this.tableName = tableName;
    }

    protected String replaceTableName(String query)
    {
        return query.replace("[table]", tableName);
    }

    protected abstract T convertToEntity(ResultSet rs) throws SQLException;
}
