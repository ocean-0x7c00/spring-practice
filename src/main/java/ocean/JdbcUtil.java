package ocean;

import java.sql.*;

/**
 * @author yancy
 * @date 2019/12/16
 */
public class JdbcUtil {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    {
        try {
            //加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/demo", "root",
                            "271828lhy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void execute(String sql) throws SQLException {
        PreparedStatement st = conn.prepareStatement(sql);
//        st.setObject(1, stu.name);
//        st.setObject(2, stu.age);

        //执行SQL语句
        st.executeUpdate();
    }


    public Student executeQuery(String sql) throws SQLException {
        Student student = null;

        st = conn.prepareStatement(sql);
//            st.setObject(1, num);


        //执行SQL语句
        rs = st.executeQuery(sql);
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            student = new Student(id, name, age);
        }
        return student;
    }

    /**
     * 关闭资源
     * Connection、Statement、ResultSet
     * 关闭顺序 ResultSet -> Statement -> Connection
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
