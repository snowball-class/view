package shop.snowballclass.view.dto.eventlesson;

import shop.snowballclass.view.entity.EventLesson;

public record EventLessonResponse(
        Long id,
        Long eventId,
        Long lessonId
) {
    public static EventLessonResponse from(EventLesson eventLesson) {
        return new EventLessonResponse(
                eventLesson.getId(),
                eventLesson.getEventId(),
                eventLesson.getLessonId()
        );
    }
}
