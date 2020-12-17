package com.dimemtl;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import javax.swing.text.TabableView;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConfiguration {

    private final String connectionString;




    public DatabaseConfiguration(String connectionString){
        this.connectionString = connectionString;
    }

    public String getConnectionString(){
        return connectionString;
    }

    public ConnectionSource connectionSource(){
        try{
            return new JdbcConnectionSource(connectionString);
        }catch (SQLException throwables){

            throwables.printStackTrace();
            throw new RuntimeException(String.format("Unable to connect to %s", connectionString));
        }
    }

    public  void createTables() throws SQLException{
        TableUtils.createTableIfNotExists(connectionSource(), Employee.class);
        TableUtils.createTableIfNotExists(connectionSource(), Department.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseConfiguration that = (DatabaseConfiguration) o;
        return Objects.equals(connectionString, that.connectionString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionString);
    }

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
                "connectionString='" + connectionString + '\'' +
                '}';
    }
}
