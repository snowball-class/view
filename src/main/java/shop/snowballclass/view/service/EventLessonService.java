package shop.snowballclass.view.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.dto.event.EventLessonCreateRequest;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.exception.ErrorCode;
import shop.snowballclass.view.exception.common.EntityNotFoundException;
import shop.snowballclass.view.repository.EventLessonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventLessonService {
    private final EventLessonRepository eventLessonRepository;

    @Transactional
    public void createEventLessons(Long eventId, EventLessonCreateRequest lessonIds) {
        List<EventLesson> eventLessonList = lessonIds.lessonIds().stream()
                .map(lessonId -> EventLesson.from(eventId, lessonId))
                .collect(Collectors.toList());
        eventLessonRepository.saveAll(eventLessonList);
    }


    @Transactional(readOnly = true)
    public Optional<EventLesson> findEventLessonByLessonId(Long lessonId) {
        List<EventLesson> eventLessons = eventLessonRepository.findByLessonId(lessonId);
        if (eventLessons.size() > 1)
            log.error("Multiple EventLessons found for lessonId: {}", lessonId);
        return eventLessons.stream().findFirst();
    }

    @Transactional(readOnly = true)
    public EventLesson getEventLessonById(Long id) {
        return eventLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EVENT_LESSON_NOT_FOUND, "존재하지 않는 EventLesson입니다. Id: " + id));
    }

}
