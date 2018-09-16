package com.web;

import com.web.Dao.AccountDao;
import com.web.Dao.DBCPUtil;
import com.web.Dao.MyjdbcUtil;
import com.web.Model.Account;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.math.*;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Jiang
 * @Date: Created in 11:46  2018\7\31 0031
 * @Description:
 * @Modified By:
 */
public class test {

    private static int threadcount = 1000 ;

    private final static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(threadcount);

    private static int i = 0 ;

    public static void ConnectionTest(){

        try {
            Connection conn = MyjdbcUtil.getconnection() ;
            if(conn != null) {
                Statement st = conn.createStatement();
                String sql = "SELECT * FROM account_info";
                ResultSet rs = st.executeQuery(sql);
//                while (rs.next()) {
//                    System.out.println(rs.getString("name"));
//                    System.out.println(rs.getString("password"));
//                }
                MyjdbcUtil.close(rs, st, conn);
            }else{
                System.out.println("********请求失败*******");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main (String []arg ){


        for(i = 0 ; i < threadcount ; i++ ){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0 ;
                    while (i < 10 ){
                        i ++ ;
                        try{
                            COUNT_DOWN_LATCH.countDown();//每次减一
                            COUNT_DOWN_LATCH.await();
                            ConnectionTest();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
        while (true)
             ;


//        String name = "admin";
//        String pass = "123456" ;
//
//        Account account = AccountDao.selectAccount(name, pass);
//        if(account != null){
//            System.out.println(account.getId());
//            System.out.println(account.getName());
//            System.out.println(account.getPassword());
//        }
//        else
//            System.out.println("用户名或密码错误！");

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



    }

}
