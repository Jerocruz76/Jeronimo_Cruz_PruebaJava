package riwi.filtro.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/riwiacademydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Rlwl2023.";
    static Connection connection = null;
    public Connection getConnection(){
        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect(){
        try{
            if (connection != null) connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
