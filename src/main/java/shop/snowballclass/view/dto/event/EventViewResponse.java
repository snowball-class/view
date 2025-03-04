package shop.snowballclass.view.dto.event;

import shop.snowballclass.view.dto.lesson.LessonDetailsResponse;

import java.util.List;

public record EventViewResponse(
        EventResponse event,
        List<LessonDetailsResponse> lessonList
) {
    public static EventViewResponse from(EventResponse event, List<LessonDetailsResponse> lessonList) {
        return new EventViewResponse(
                event,
                lessonList
        );
    }
}
