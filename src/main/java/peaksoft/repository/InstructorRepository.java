package peaksoft.repository;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepository {

    void saveInstructor(Instructor instructor);

    List<Instructor> getAllInstructors();

    Instructor getInstructorById(Long id);

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseId);

     List<Instructor> getAllInstructorsWithoutCourse();


}
