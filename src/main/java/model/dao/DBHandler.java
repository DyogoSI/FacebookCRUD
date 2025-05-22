
package model.dao;

import java.sql.*;

public class DBHandler {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DBHandler() throws SQLException {
        this.connection = getConnection();
    }

    // Método para estabelecer a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/facebook";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

    // Preparar uma instrução SQL
    public void prepareStatement(String sql) throws SQLException {
        this.preparedStatement = connection.prepareStatement(sql);
    }

    // Definir um parâmetro do tipo String na instrução preparada
    public void setString(int parameterIndex, String value) throws SQLException {
        preparedStatement.setString(parameterIndex, value);
    }

    // Definir um parâmetro do tipo Integer na instrução preparada
    public void setInt(int parameterIndex, int value) throws SQLException {
        preparedStatement.setInt(parameterIndex, value);
    }

    // Executar uma atualização (INSERT, UPDATE, DELETE)
    public int executeUpdate() throws SQLException {
        return preparedStatement.executeUpdate();
    }

    // Executar uma consulta (SELECT)
    public void executeQuery() throws SQLException {
        this.resultSet = preparedStatement.executeQuery();
    }

    // Verificar se há mais resultados no ResultSet
    public boolean next() throws SQLException {
        return resultSet.next();
    }

    // Obter um valor String do ResultSet
    public String getString(String columnLabel) throws SQLException {
        return resultSet.getString(columnLabel);
    }

    // Obter um valor Integer do ResultSet
    public int getInt(String columnLabel) throws SQLException {
        return resultSet.getInt(columnLabel);
    }
}
