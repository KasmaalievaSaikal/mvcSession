package peaksoft.repository;

import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentRepository {

    void saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    void assignStudentToCourse(Long studentId, Long courseId);

    List<Student> getAllStudentsWithoutCourse();

}
