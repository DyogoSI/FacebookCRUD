package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.ModelException;

public class MySQLConnectionFactory {

	private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/facebook";
    static final String USER = "root";  
    static final String PASSWORD = "123456"; 

    public static Connection getConnection() throws ModelException {
        try {
            Class.forName(JDBC_DRIVER_NAME);
            return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new ModelException("Driver não encontrado", e);
        } catch (SQLException e) {
            throw new ModelException("Falha na conexão com o BD", e);
        }
    }
}
