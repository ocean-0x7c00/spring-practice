package ocean;

/**
 * @author yancy
 * @date 2019/12/12
 */
public class Client {
    public static void main(String[] args) {
        Student student = new Student();
        student.name = "haiyang";
        student.age = 27;
        StudentService studentService = new StudentService();
        studentService.save(student);

        Student query = studentService.query(1L);
        System.out.println();

    }
}
