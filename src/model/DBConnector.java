package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnector {

    private final String ip = "173.249.29.54";
    private final String db = "javapara_accounts";
    private final String user = "javapara_Fausto";
    private final String pass = "kirascorp9";
    private String url;
    private Connection connection;
    
    public DBConnector() throws SQLException{
        this.url = "jdbc:mysql://"+ip+"/"+db;
        connection = DriverManager.getConnection(url,user, pass);
        
    }
    
    public ResultSet select(String instructSQL) throws SQLException{
        ResultSet resultSet = null;
        Statement statement = connection.createStatement();
        
        resultSet = statement.executeQuery(instructSQL);
        
        return resultSet;
    }
    
    public boolean insert(String instructSQL) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement statement = connection.prepareStatement(instructSQL);
        
        if (statement.execute()) {
            resultSet = statement.getGeneratedKeys();
            return true;
        }else{
            throw new SQLException();
        }
    }
    
    public boolean update(String instructSQL) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement statement = connection.prepareStatement(instructSQL);
        
        if (statement.execute()) {
            resultSet = statement.getGeneratedKeys();
            return true;
        }else{
            throw new SQLException();
        }
    }
    
    public boolean delete(String instructSQL) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement statement = connection.prepareStatement(instructSQL);
        
        if (statement.execute()) {
            resultSet = statement.getGeneratedKeys();
            return true;
        }else{
            throw new SQLException();
        }
    }
    
    public void connectionClose() throws SQLException{
        connection.close();
    }
}
