package com.itheima;


import java.sql.Connection;

public class TestMyPool {
    public static void main(String[] args) {
        MyPool pool = new MyPool();

        Connection conn1 = pool.getConnection();
        Connection conn2 = pool.getConnection();
        Connection conn3 = pool.getConnection();
        Connection conn4 = pool.getConnection();
        Connection conn5 = pool.getConnection();
        //System.out.println(pool.getcount());
        Connection conn6 = pool.getConnection();
        pool.close(conn1);
        pool.close(conn5);
        System.out.println(pool.getcount());


    }
}
