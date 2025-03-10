package shop.snowballclass.view.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.client.EventClient;
import shop.snowballclass.view.client.LessonClient;
import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.event.EventViewResponse;
import shop.snowballclass.view.dto.lesson.LessonDetailsResponse;
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.exception.common.ExternalServiceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventViewService {
    private final EventLessonService eventLessonService;
    private final LessonClient lessonClient;
    private final EventClient eventClient;

    @Transactional(readOnly = true)
    public EventViewResponse getEventView(Long eventId) {
        try {
            EventResponse eventResponse = eventClient.getEventByEventId(eventId).data();
            String lessonIds = eventLessonService.getEventLessonByEventId(eventId).stream()
                    .map(EventLesson::getLessonId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            List<LessonDetailsResponse> lessonsResponse = lessonClient.getBulkLessons(lessonIds).data().stream()
                    .map(lesson -> LessonDetailsResponse.from(
                            lesson, lesson.price() * (100 - eventResponse.discountRate()) / 100,
                            null, null))
                    .collect(Collectors.toList());
            return EventViewResponse.from(eventResponse, lessonsResponse);
        } catch (FeignException e) {
            throw new ExternalServiceException(String.format("Failed to get response on getEventView. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    public List<EventViewResponse> getActiveEventList() {
        try {
            LocalDateTime now = LocalDateTime.now();
            List<EventResponse> activeEventList = eventClient.getAllEvent().data().stream()
                    .filter(event -> event.startDateTime().isBefore(now) && event.endDateTime().isAfter(now))
                    .collect(Collectors.toList());
            if(activeEventList.isEmpty()) return List.of();
            List<Long> eventIds = activeEventList.stream()
                    .map(EventResponse::id)
                    .collect(Collectors.toList());

            List<EventLesson> eventLessonList = eventLessonService.getEventLessonByEventIdList(eventIds);
            String lessonIds = eventLessonList.stream()
                    .map(EventLesson::getLessonId)
                    .distinct()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            List<LessonResponse> lessonList = lessonClient.getBulkLessons(lessonIds).data();
            return activeEventList.stream()
                    .map(event -> { // 해당 이벤트에 연결된 Lesson 데이터 필터링
                        List<LessonDetailsResponse> lessonsResponse = lessonList.stream()
                                .filter(lesson -> eventLessonList.stream()
                                        .anyMatch(eventLesson -> eventLesson.getEventId().equals(event.id())
                                                && eventLesson.getLessonId().equals(lesson.lessonId())))
                                .map(lesson -> LessonDetailsResponse.from(
                                        lesson, lesson.price() * (100 - event.discountRate()) / 100,
                                        null, null))
                                .collect(Collectors.toList());
                        return EventViewResponse.from(event, lessonsResponse);
                    })
                    .collect(Collectors.toList());
        } catch (FeignException e) {
            throw new ExternalServiceException(String.format("Failed to get response on getActiveEventList. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }
}
