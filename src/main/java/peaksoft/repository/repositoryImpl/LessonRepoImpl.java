package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Lesson;
import peaksoft.repository.LessonRepository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LessonRepoImpl implements LessonRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public void saveLesson(Lesson lesson) {
        entityManager.persist(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class, id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = getLessonById(id);
        lesson1.setTitle(lesson.getTitle());
        lesson1.setImageUrl(lesson.getImageUrl());
        lesson1.setDescription(lesson.getDescription());
        entityManager.merge(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        entityManager.remove(lesson);
    }
}
