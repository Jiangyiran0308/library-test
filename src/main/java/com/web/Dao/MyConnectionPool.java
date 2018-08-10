package com.web.Dao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Author: Jiang
 * @Date: Created in 8:56  2018\8\10 0010
 * @Description:
 * @Modified By:
 */
public class MyConnectionPool implements DataSource{

    private static final String configFile = "src/main/resources/properties/dbcp.properties" ;

    private static String driver ;
    private static String url ;
    private static String username ;
    private static String password ;
    private static int InitSize ;
    private static int maxActive ;

    private static int max = 0 ;
    private static List<Connection> allConnextion = new LinkedList<Connection>();


    static {
        Properties pro = new Properties() ;
        try {
            //FileInputStream config = new FileInputStream(configFile);
            InputStream config=MyConnectionPool.class.getResourceAsStream("/dbcp.properties");
            if (config != null) {
                pro.load(config);
                System.out.println(pro);
                driver = pro.getProperty("driverClassName") ;
                url = pro.getProperty("url") ;
                username = pro.getProperty("username") ;
                password = pro.getProperty("password") ;
                InitSize = Integer.parseInt(pro.getProperty("initialSize")) ;
                maxActive = Integer.parseInt(pro.getProperty("maxActive")) ;

                Class.forName(driver) ;

                for(int i = 0 ; i < InitSize ; i++ ){
                    Connection conn = DriverManager.getConnection(url,username,password) ;
                    allConnextion.add(conn) ;
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(allConnextion.size() == 0 && max < ((maxActive-InitSize)/5)) {
            for (int i = 0; i < 5; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                allConnextion.add(conn);
            }
            max++ ;
        }
        if(allConnextion.size() > 0 ){
            Connection conn = allConnextion.get(0) ;
            allConnextion.remove(0) ;
        }
        return null ;
    }

    /**
     * 归还连接对象到连接池中去
     */
    public void backConnection(Connection conn){

    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
