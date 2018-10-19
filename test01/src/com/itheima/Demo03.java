package com.itheima;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*创建员工表和员工类，包含字段：编号，姓名，性别，入职日期，工资
	1. 使用PreparedStatement向员工表中插入3条记录。
	2. 使用PreparedStatement，将3号员工的名字改成"猪八戒"，工资还在5300，入职日期改成2017-02-01
	3. 查询所有的员工类，封装成List<Employee>返回
	4. 查询所有员工的平均工资
	5. 查询所有姓张的员工的信息
	6. 编写方法：通过ID查询指定员工的信息，并且封装成一个员工对象返回
	7. 编写方法：通过ID删除指定的员工，并且返回删除的行数
* */
public class Demo03 {
    public static void main(String[] args) throws Exception {
        DataSource ds = new ComboPooledDataSource();
        Connection conn = ds.getConnection();

        //使用PreparedStatement，将3号员工的名字改成"猪八戒"，工资还在5300，入职日期改成2017-02-01
       /* String sql = "update employee set name = '猪八戒',join_date = '2017-02-01' where id = 3 ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();*/
       //List<Employee> list = method02(conn);
        //查询所有员工的平均工资
       /* String sql = "SELECT AVG(salary) AVG FROM employee";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Double d = rs.getDouble("avg");
            System.out.println(d);
        }*/
        //查询所有姓张的员工的信息
       /* String sql = "select*from employee where name like  '张%'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String join_date = rs.getString("join_date");
            String salary = rs.getString("salary");
            System.out.println(id + name + gender + join_date + salary);
        }*/
        /*Employee emp = method03(conn, 2);
        System.out.println(emp);*/
        int i = method04(conn, 2);
        System.out.println(i);
        //关闭释放资源
        conn.close();
    }
    public static int method04(Connection conn ,int i) throws Exception{
        String sql = "delete from employee where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,i);
        int n = ps.executeUpdate();
        return n;
    }
    //编写方法：通过ID查询指定员工的信息，并且封装成一个员工对象返回
    public static Employee method03(Connection conn ,int i) throws SQLException {
            Employee emp = new Employee();
        String sql = "select*from employee where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,i);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String join_date = rs.getString("join_date");
            String salary = rs.getString("salary");
            emp.setId(id);
            emp.setName(name);
            emp.setGender(gender);
            emp.setJoin_date(join_date);
            emp.setSalary(salary);
        }
        return emp;
    }
    private static List<Employee> method02(Connection conn) throws SQLException {
        //查询所有的员工类，封装成List<Employee>返回
        List<Employee> list = new ArrayList<Employee>(){};
        String sql = "select*from employee";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String join_date = rs.getString("join_date");
            String salary = rs.getString("salary");
            Employee emp = new Employee();
            emp.setId(id);
            emp.setName(name);
            emp.setGender(gender);
            emp.setJoin_date(join_date);
            emp.setSalary(salary);
            list.add(emp);
        }
        ps.close();
        return list;
    }
}
