package shop.snowballclass.view.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;

import java.util.List;
import java.util.UUID;

public record MemberReviewResponse(

        @Schema(description = "회원 ID", example = "24886e22-6f45-4449-a153-43f58e9b519a")
        UUID memberUUID,
        @Schema(description = "회원이 적은 리뷰 목록")
        List<ReviewResponse> reviewList,
        @Schema(description = "회원이 적은 리뷰가 달린 강의")
        List<LessonResponse> lessonList
) {
    public static MemberReviewResponse from(UUID memberUUID, List<ReviewResponse> reviewList, List<LessonResponse> lessonList){
        return new MemberReviewResponse(memberUUID, reviewList, lessonList);
    }
}