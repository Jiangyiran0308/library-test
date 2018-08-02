package com.web.Dao;

import com.web.Dao.Interfaces.AccountDao;
import com.web.Model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SimpleAccountDao implements AccountDao{

    public static Connection connection = null ;

    public void config(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
        }
        try {
            Properties pro = new Properties();
            FileInputStream input = new FileInputStream("F:\\My document\\JavaStudy\\library-test\\src\\main\\resources\\properties\\jdbc.properties");
            pro.load(input);
            input.close();
            String url = pro.getProperty("url");
            String user = pro.getProperty("username");
            String pass = pro.getProperty("password");

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(url, user, pass);


        }catch(SQLException | IOException ignored){
            System.out.println(ignored);
        }
    }

    public Account getAccountByName(String accountName,String accountPass){
        config();
        String sql = "SELECT t.id,t.name,t.password FROM account_info t WHERE t.NAME = ? AND t.PASSWORD = ?" ;
        try {
            PreparedStatement pre = connection.prepareStatement(sql) ;
            pre.setString(1,accountName);
            pre.setString(2,accountPass);

            ResultSet resultSet = pre.executeQuery() ;
            while (resultSet.next()){
                Account a = new Account() ;
                a.setId(resultSet.getString("id")) ;
                a.setName(resultSet.getString("name")) ;
                a.setPassword(resultSet.getString("password")) ;
                resultSet.close();
                pre.close();
                return a ;
            }

        }catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        return null ;
    }

}
