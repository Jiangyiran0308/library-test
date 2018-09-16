package com.web.Dao;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
    private static int increase ;
    private static long maxWait ;
    private static int waitTime ;

    private static int poolSize = 0 ;
    private static LinkedList<Connection> allConnextion = new LinkedList<Connection>();


    static {
        Properties pro = new Properties() ;
        try {
            FileInputStream config = new FileInputStream(configFile);
            //InputStream config=MyConnectionPool.class.getResourceAsStream("/dbcp.properties");
            if (config != null) {
                pro.load(config);
                //System.out.println(pro);
                driver = pro.getProperty("driverClassName") ;
                url = pro.getProperty("url") ;
                username = pro.getProperty("username") ;
                password = pro.getProperty("password") ;
                InitSize = Integer.parseInt(pro.getProperty("initialSize")) ;

                poolSize = InitSize ;

                maxActive = Integer.parseInt(pro.getProperty("maxActive")) ;
                maxWait = Long.parseLong(pro.getProperty("maxWait")) ;
                waitTime = Integer.parseInt(pro.getProperty("waitTime"));
                increase = Integer.parseInt(pro.getProperty("increase")) ;

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
        if(allConnextion.size() == 0 && poolSize < maxActive) {
            synchronized(this) {
                if(allConnextion.size() == 0 && poolSize < maxActive) {
                    if (maxActive - poolSize < increase)
                        increase = maxActive - poolSize;
                    for (int i = 0; i < increase; i++) {
                        Connection conn = DriverManager.getConnection(url, username, password);
                        allConnextion.add(conn);
                    }
                    poolSize += increase;
                    System.out.println("********连接池的大小为：" + poolSize + "******最大为：" + maxActive);
                }
            }
        }

        synchronized (this){

            long time = 0 ;
            if(allConnextion.size() == 0) {
                while (allConnextion.size() == 0 && time < maxWait) {
                    try {
                        long wait = 1;
                        time += wait;
                        if (time > maxWait) {
                            wait = maxWait - time;
                            time = maxWait;

                        }
                        this.wait(wait);

                        if(allConnextion.size() != 0){
                            System.out.println("********等待了 "+time+" ms********");
                            return returnConnection() ;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            else{
                System.out.println("********没有等待********");
                return returnConnection() ;
            }


        }

//        if (allConnextion.size() > 0) {
//        synchronized (this) {
//
//            if (allConnextion.size() > 0) {
//
//                final Connection conn = allConnextion.get(0);
//                allConnextion.remove(0);
//
//                return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
//                        new Class[]{Connection.class}, new InvocationHandler() {
//                            @Override
//                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                                if (!method.getName().equals("close")) {
//                                    return method.invoke(conn, args);
//                                } else {
//                                    //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
//                                    allConnextion.add(conn);
//                                    System.out.println("Connections数据库连接池大小为" + allConnextion.size());
//                                    return null;
//                                }
//                            }
//                        });
//            }
//        }
//        }
        System.out.println("********连接超时*********");
        return null ;
    }

    private Connection returnConnection(){
        synchronized (this) {
            if (getAllConnextion().size() > 0) {
                if (getAllConnextion().size() > 0) {
                    final Connection conn = allConnextion.removeFirst();
//                    allConnextion.removeFirst();
//                    allConnextion.remove(0);

                    return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                            new Class[]{Connection.class}, new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    if (!method.getName().equals("close")) {
                                        return method.invoke(conn, args);
                                    } else {
                                        //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
                                        allConnextion.add(conn);
                                        System.out.println("Connections数据库连接池大小为" + allConnextion.size());
                                        return null;
                                    }
                                }
                            });
                }
            }
            return null ;
        }
    }

    private synchronized List<Connection> getAllConnextion(){
        return allConnextion ;
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
