package com.itheima;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;


public class MyPool implements DataSource{
    //创建一个集合用来存储多个连接对象
    private static LinkedList<Connection> list = new LinkedList<>();
    private static String password = "123";
    private static String user = "root";
    private static String url = "jdbc:mysql:///day17";
    static{
    //注册驱动,因为要实现一次，所以需要静态修饰,并完成初始化五个连接对象
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 5; i++) {
            Connection conn = DriverManager.getConnection(url, user, password);
            MyConnection my = new MyConnection(conn,list);
                list.addLast(my);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //申明一个获取连接的方法
    public Connection getConnection(){
        if(list.size()>0){
          return list.remove();
        }else{
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    }
    public int getcount(){
        return list.size();
    }
    //申明一个关闭连接的方法,判断是自定义中的连接对象还是新创建的连接对象
    public void close(Connection conn){
        try {
            if(conn instanceof MyConnection){
               conn.close();
            }else{
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
