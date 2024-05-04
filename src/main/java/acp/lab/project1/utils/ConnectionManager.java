/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acp.lab.project1.utils;
import java.sql.*;
/**
 *
 * @author addan
 */
public class ConnectionManager {
    private static Connection connection;

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ACP_Project;integratedSecurity=true;trustServerCertificate=true";

    private ConnectionManager() throws SQLException {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }
}
