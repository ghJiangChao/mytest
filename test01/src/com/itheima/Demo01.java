package com.itheima;



import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;


//java语言对数据库的增删改
public class Demo01 {
    public static void main(String[] args) throws Exception {
       /* Scanner sca = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = sca.nextLine();
        System.out.println("请输入密码：");
        String password = sca.nextLine();*/
        //创建连接池
        DataSource ds  = new ComboPooledDataSource();
        Connection conn = ds.getConnection();
        method01(conn);
        //method02(conn);
        //method03(conn);
        /*//查询数据,判断用户是否存在
        String sql = "select*from user where name = ? and password = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败！");
        }
        rs.close();
        ps.close();*/
        //关闭资源
        conn.close();

    }
    //修改数据
    private static void method03(Connection conn) throws SQLException {
        String sql = "update user set  password = '1234' where name = 'Jack'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }

    //删除一个数据
    private static void method02(Connection conn) throws SQLException {
        String sql = "delete from user  where id = 5";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }

    private static void method01(Connection conn) throws SQLException {
        //添加一个sql对象
        String sql = "create table student (id int primary key auto_increment,name varchar(10) not null unique,gender char(1))";
        PreparedStatement ps = conn.prepareStatement(sql);
        //获取设置
        ps.executeUpdate();
        ps.close();
    }
}
