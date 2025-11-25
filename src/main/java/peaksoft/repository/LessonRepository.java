package peaksoft.repository;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonRepository {

    void saveLesson(Lesson lesson);

    List<Lesson> getAllLessons();

    Lesson getLessonById(Long id);

    void updateLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);
}
