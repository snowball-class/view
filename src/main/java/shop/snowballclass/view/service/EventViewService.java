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
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.exception.common.ExternalServiceException;

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
    public EventViewResponse getLessonView(Long eventId) {
        try {
            EventResponse eventResponse = eventClient.getEventByEventId(eventId).data();
            String lessonIds = eventLessonService.getEventLessonByEventId(eventId).stream()
                    .map(EventLesson::getLessonId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            List<LessonDetailsResponse> lessonsResponse = lessonClient.getBulkLessons(lessonIds).data().stream()
                    .map(lessonResponse -> LessonDetailsResponse.from(
                            lessonResponse,
                            lessonResponse.price() * (100 - eventResponse.discountRate()) / 100,
                            null, null))
                    .collect(Collectors.toList());
            return EventViewResponse.from(eventResponse, lessonsResponse);
        } catch (FeignException e) {
            throw new ExternalServiceException(String.format("Failed to get response. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }
}
