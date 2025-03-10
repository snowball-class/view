package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.snowballclass.view.entity.EventLesson;

import java.util.List;

public interface EventLessonRepository extends JpaRepository<EventLesson, Long> {

    List<EventLesson> findByLessonId(Long lessonId);

    List<EventLesson> findByEventId(Long eventId);

    List<EventLesson> findByEventIdIn(List<Long> eventId);

    List<EventLesson> findByLessonIdIn(List<Long> eventId);

}
