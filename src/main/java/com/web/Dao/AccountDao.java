package com.web.Dao;

import com.web.Model.Account;
import org.apache.ibatis.jdbc.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

    private static DBCPUtil dbcpUtil = new DBCPUtil() ;

    public static Account selectAccount(String name , String pass) {
        System.out.println("开始查询！");
        Account account = null ;
        Connection connection = dbcpUtil.getConn() ;
        if(connection != null) {
            try {

                String sql = "select t.id,t.name,t.password from account_info t where t.name = ? and t.password = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, pass);
                String accountId = null;
                String accountName = null;
                String accountPass = null;
                ResultSet rs = ps.executeQuery();
                System.out.println("查询成功！");
                while (rs.next()) {
                    accountId = rs.getString("id");
                    accountName = rs.getString("name");
                    accountPass = rs.getString("password");
                }
                if (accountId != null && accountName != null && accountPass != null) {
                    account = new Account();
                    account.setId(accountId);
                    account.setName(accountName);
                    account.setPassword(accountPass);
                }
                dbcpUtil.close(rs, ps, connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return account ;
    }


}