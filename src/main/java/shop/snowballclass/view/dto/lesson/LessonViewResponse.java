package shop.snowballclass.view.dto.lesson;

import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;

import java.util.List;

public record LessonViewResponse(
        LessonDetailsResponse lesson,
        EventResponse event,
        List<ReviewResponse> reviewList

) {
    public static LessonViewResponse from(LessonDetailsResponse lessonResponse, EventResponse eventResponse, List<ReviewResponse> reviewResponse) {
        return new LessonViewResponse(
                lessonResponse,
                eventResponse,
                reviewResponse
        );
    }
}
