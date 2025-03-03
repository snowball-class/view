package shop.snowballclass.view.dto.lesson;

import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;

import java.util.List;

public record LessonDetailsResponse(
        LessonResponse lesson,
        EventResponse event,
        List<ReviewResponse> reviewList

) {
    public static LessonDetailsResponse from(LessonResponse lessonResponse, EventResponse eventResponse, List<ReviewResponse> reviewResponse) {
        return new LessonDetailsResponse(
                lessonResponse,
                eventResponse,
                reviewResponse
        );
    }
}
