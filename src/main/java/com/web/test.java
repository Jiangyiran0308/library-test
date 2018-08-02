package com.web;

import com.web.Dao.AccountDao;
import com.web.Dao.DBCPUtil;
import com.web.Model.Account;

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
       /* try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        try {
            Properties pro = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/properties/jdbc.properties");
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
        }*/

        String name = "admin";
        String pass = "123456" ;

        Account account = AccountDao.selectAccount(name, pass);
        if(account != null){
            System.out.println(account.getId());
            System.out.println(account.getName());
            System.out.println(account.getPassword());
        }
        else
            System.out.println("用户名或密码错误！");

    }

}
