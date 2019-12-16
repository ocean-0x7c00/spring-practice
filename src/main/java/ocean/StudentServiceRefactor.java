package ocean;

import java.sql.SQLException;

/**
 * 重构StudentService
 * v1.0
 * 实现的功能点：
 * - 实现连接创建和关闭的代码复用
 * <p>
 * 存在的问题
 * - 每个DML或DQL中仍然有重复的连接和关闭代码（ new JdbcUtil()和jdbcUtil.close()）
 * - 无法灵活的使用PreparedStatement设置SQL参数中的值
 * - DQL的返回结果解析只支持单一的Model，新增不同的Model，都需要重新改写查询的结果解析
 *   希望提供一个通用的查询结果集解析
 * - JdbcUtil会频繁的创建连接和关闭连接，会造成性能问题（如何设计一个实验来证明性能存在问题）。希望通过线程池解决此问题
 * - 数据库连接信息硬编码在程序中，当在不同环境连接DB时，需要频繁的修改代码
 *
 * @author yancy
 * @date 2019/12/16
 */
public class StudentServiceRefactor {
    public void save(Student stu) throws SQLException {
        String sql = "INSERT INTO student(name,age) values('%s',%s)";

        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.execute(String.format(sql, stu.name, stu.age));
        jdbcUtil.close();
    }

    public void delete(Long id) throws SQLException {

        String sql = "DELETE FROM student WHERE id=%s";

        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.execute(String.format(sql, id));
        jdbcUtil.close();

    }

    public void update(Student stu) throws SQLException {
        String sql = "update student SET name='%s',age=%s WHERE id= %s ";
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.execute(String.format(sql, stu.name, stu.age, stu.id));
        jdbcUtil.close();
    }

    public Student query(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id=%s";
        JdbcUtil jdbcUtil = new JdbcUtil();
        Student student = jdbcUtil.executeQuery(String.format(sql, id));
        jdbcUtil.close();
        return student;
    }


}
