package Course.JDBC1;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class JdbcTest {
    Connection con=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/smapp","root","asdfghjkl");
    Statement stmt=con.createStatement();

    public JdbcTest() throws SQLException {
    }

    public List<teacher> jdbc11() throws SQLException {

        ResultSet rs=stmt.executeQuery("select * from student");
        List<teacher> teachers=new ArrayList<>();

        while(rs.next()) {
            teacher teacher=new teacher();
            teacher.setId(rs.getLong(1));
            teacher.setName(rs.getString(2));
            teacher.setSurname(rs.getString(3));
            ///// /4System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            teachers.add(teacher);
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
        return  teachers;
    }




}
