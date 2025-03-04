package shop.snowballclass.view.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.exception.ErrorCode;
import shop.snowballclass.view.exception.common.EntityNotFoundException;
import shop.snowballclass.view.repository.EventLessonRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventLessonService {
    private final EventLessonRepository eventLessonRepository;

    @Transactional(readOnly = true)
    public EventLesson getEventLessonByLessonId(Long lessonId) {
        List<EventLesson> eventLessons = eventLessonRepository.findByLessonId(lessonId);
        if (eventLessons.isEmpty())
            throw new EntityNotFoundException(ErrorCode.EVENT_LESSON_NOT_FOUND, "EventLesson not found By lessonId: " + lessonId);
        if (eventLessons.size() > 1)
            log.error("Multiple EventLessonViews found for lessonId: " + lessonId);
        return eventLessons.get(0);
    }

}
