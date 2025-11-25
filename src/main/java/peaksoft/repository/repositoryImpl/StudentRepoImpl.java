package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import java.util.List;


@Repository
@Service
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setImageUrl(student.getImageUrl());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student2 = entityManager.find(Student.class, id);
        entityManager.remove(student2);

    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        course.getStudents().add(student);
        student.getCourses().add(course);

    }

    @Override
    public List<Student> getAllStudentsWithoutCourse() {
        return entityManager.createQuery(
                "select s from Student s where s.courses is empty", Student.class
        ).getResultList();
    }
}
