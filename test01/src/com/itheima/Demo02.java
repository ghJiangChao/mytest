package com.itheima;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//事务所
public class Demo02 {
    public static void main(String[] args) {
        //1. 注册驱动
        //2. 获取连接
        DataSource ds = new ComboPooledDataSource();
        Connection conn = null;
        try {
            conn = ds.getConnection();
            //3. 获取到Statement
           //4. 开启事务
            conn.setAutoCommit(false);
            //5. 使用Statement执行SQL
            //张三扣款
            String sql = "update user2 set money = money -500 where name = '张三'";
            //PreparedStatement ps = conn.prepareStatement(sql);
            //ps.executeUpdate();
            System.out.println("异常出现前的值" + conn.createStatement().executeUpdate(sql));

            int a = 10/0;
            //李四收款
            String str = "update user2 set money = money + 500 where name = '李四'";
            //PreparedStatement pst = conn.prepareStatement(str);
            //pst.executeUpdate();
            System.out.println("异常出现后的值" + conn.createStatement().executeUpdate(str));
            //6. 提交或回滚事务
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("出现异常");
        }finally {
        //7. 关闭资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
