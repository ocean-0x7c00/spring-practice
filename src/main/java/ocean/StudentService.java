package ocean;

import java.sql.*;

/**
 * 最原始的方式，实现对表的增删改查
 * <p>
 * 分析：看看是否符合面向对象的原则
 * 存在的问题：
 * 1. 代码大量重复，特别是资源关闭的代码
 * 2. 不满足开闭原则
 * 3. DML、DQL与数据库连接、管理混在一起，不满足单一职责
 * 4. 频繁的创建数据库连接  （为什么说创建数据库连接是一件很耗性能的事）
 * <p>
 * DDL、DML、DQL、DCL
 * https://www.jianshu.com/p/671a2d54dca0
 *
 * @author yancy
 * @date 2019/12/12
 */
public class StudentService {
    public void save1(){

        String sql="";
        //todo execute sql 返回执行结果

    }
    public void save(Student stu) {
        String sql = "INSERT INTO student(name,age) values(?,?)";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            //加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取数据库连接
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/demo", "root", "271828lhy");

            //创建语句对象
            st = conn.prepareStatement(sql);
            st.setObject(1, stu.name);
            st.setObject(2, stu.age);

            //执行SQL语句
            st.executeUpdate();

        } catch (Exception e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * save 和 delete 唯一的差别就是创建的语句对象不同，其他的都一样
     */


    public void delete(Long id) {

        String sql = "DELETE FROM student WHERE id=?";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            //加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取数据库连接
            conn = DriverManager.getConnection
                    ("jdbc.properties:mysql://localhost:3306/demo", "root", "271828lhy");

            //创建语句对象
            st = conn.prepareStatement(sql);
            st.setObject(1, id);

            //执行SQL语句
            st.executeUpdate();

        } catch (Exception e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void update(Student stu) {
        String sql = "update student SET name=?,age=? WHERE id= ? ";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            //加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取数据库连接
            conn = DriverManager.getConnection
                    ("jdbc.properties:mysql://localhost:3306/demo", "root", "271828lhy");

            //创建语句对象
            st = conn.prepareStatement(sql);
            st.setObject(1, stu.name);
            st.setObject(2, stu.age);
            st.setObject(3, stu.id);

            //执行SQL语句
            st.executeUpdate();

        } catch (Exception e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Student query(Long num) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取数据库连接
            conn = DriverManager.getConnection
                    ("jdbc.properties:mysql://localhost:3306/demo", "root", "271828lhy");

            //创建语句对象
            st = conn.prepareStatement(sql);
            st.setObject(1, num);


            //执行SQL语句
            rs = st.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                student = new Student(id, name, age);
            }

        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            } finally {
                try {

                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {

                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return student;
    }
}
