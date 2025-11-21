package peaksoft.repository;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    void updateCourse(Long id, Course course);

    void deleteCourse(Long id);

    void assignCourseToInstructor(Long courseId,Long instructorId);

}
