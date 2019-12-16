package ocean;

import java.sql.SQLException;

/**
 * @author yancy
 * @date 2019/12/12
 */
public class Client {
    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.name = "yancy";
        student.age = 27;
//        StudentService studentService = new StudentService();
//        studentService.save(student);
//
//        Student query = studentService.query(1L);
//        System.out.println();

        StudentServiceRefactor serviceRefactor = new StudentServiceRefactor();
//        serviceRefactor.save(student);
//        serviceRefactor.delete(4L);

        Student stu = new Student();
        stu.name = "y2";
        stu.age = 47;
        stu.id = 5L;
//        serviceRefactor.update(stu);

        Student result = serviceRefactor.query(2);

        System.out.println(result.toString());

    }
}
