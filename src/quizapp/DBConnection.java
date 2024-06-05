/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/newalphadb";
    String username = "root";
    String password = "";
    Connection connection=null;
    try
    {
    connection = DriverManager.getConnection(url, username, password);
    }
    catch(Exception e){
        System.out.println(e);
    }
    return connection;
    }
}
