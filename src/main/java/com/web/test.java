package com.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.math.*;
import java.util.Properties;

/**
 * @Author: Jiang
 * @Date: Created in 11:46  2018\7\31 0031
 * @Description:
 * @Modified By:
 */
public class test {
    public static void main (String []arg ){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        try {
            Properties pro = new Properties();
            FileInputStream input = new FileInputStream("F:\\My Document\\Java_study\\librarytest\\src\\main\\resources\\properties\\jdbc.properties");
            pro.load(input);
            input.close();

            String url = pro.getProperty("url");
            String user = pro.getProperty("username");
            String pass = pro.getProperty("password");

            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(url, user, pass);

            System.out.println("Creating statement...");
            Statement statement = connection.createStatement() ;
            String sql = "select t.name from batt_site t" ;

            ResultSet resultSet = statement.executeQuery(sql) ;

            while (resultSet.next()){
                String name = resultSet.getString("name") ;
                System.out.println(name);
            }


            resultSet.close();
            statement.close();
            connection.close();

        }catch(SQLException | IOException  ignored){
            System.out.println(ignored);
            System.exit(1);
        }

    }

}
