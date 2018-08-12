package com.web.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyjdbcUtil {

    private static MyConnectionPool pool = new MyConnectionPool() ;

    public static Connection getconnection() throws SQLException {
        return pool.getConnection() ;
    }

    public static void close(ResultSet rs, Statement st, Connection conn) throws SQLException{
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
