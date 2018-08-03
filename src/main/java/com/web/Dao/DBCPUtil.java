package com.web.Dao;

import com.web.Model.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Properties;

public class DBCPUtil {

    private static DataSource ds ;
    //private static final String configFile = "src/main/resources/properties/dbcp.properties" ;
    private static final String configFile = "F:\\My document\\Java_study\\librarytest\\src\\main\\resources\\properties\\dbcp.properties" ;

    public DBCPUtil(){
        Properties pro = new Properties() ;
        //InputStream config = DBCPUtil.class.getResourceAsStream(configFile) ;

        try {
            FileInputStream config = new FileInputStream(configFile);
           // InputStream config = this.getClass().getClassLoader().getSystemResourceAsStream("dbcp.properties");
            if (config != null) {
                System.out.println("读取配置文件成功*****");
                pro.load(config);
                System.out.println(pro);
                ds = BasicDataSourceFactory.createDataSource(pro);
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        Connection conn = null ;
        if(ds != null)
        {
            try {
                System.out.println("Create connection ...");
                conn = ds.getConnection();
                conn.setAutoCommit(false); //关闭自动提交事务
            }catch(SQLException e){
                e.printStackTrace();
            }

            return conn ;

        }
        return conn ;
    }

    public void close(ResultSet rs,Statement st,Connection conn) throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(st != null){
            st.close();
        }
        if(conn != null){
            conn.close();
        }
    }


}
