package com.itheima;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo04 {
    public static void main(String[] args) throws SQLException {
        //要求：分别使用默认配置和命名配置创建连接池对象。再通过连接池对象获得连接对象
        //使用默认配置创建C3P0连接池
        DataSource ds = new ComboPooledDataSource();
        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        }
        //使用命名配置创建连接池对象
        DataSource ds2 = new ComboPooledDataSource("itheima40");
        Connection conn = ds2.getConnection();
        System.out.println(conn);
    }
}
