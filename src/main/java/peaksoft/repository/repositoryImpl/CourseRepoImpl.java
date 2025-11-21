package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CourseRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CourseRepoImpl implements CourseRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }


    @Override
    public void updateCourse(Long id, Course course) {
        course.setId(id);
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }


    @Override
    public void assignCourseToInstructor(Long courseId, Long instructorId) {
        Course course = entityManager.find(Course.class, courseId);
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        course.getInstructors().add(instructor);
        instructor.getCourses().add(course);
    }
}
