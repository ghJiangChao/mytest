package com.itheima;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*自定义C3P0工具类，要求如下：
 1.创建私有静态数据源(连接池对象)成员变量
 2.创建公有的得到数据源(连接池对象)的方法
 3.创建共有的得到连接对象的方法
* */
public class C3P0Untils {
    private static DataSource ds;
    static {
        ds = new ComboPooledDataSource();
    }
    public static DataSource getDataSource(){
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
