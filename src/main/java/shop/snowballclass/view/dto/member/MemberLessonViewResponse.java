package shop.snowballclass.view.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import shop.snowballclass.view.dto.lesson.LessonResponse;

import java.util.List;
import java.util.UUID;

public record MemberLessonViewResponse(
        @Schema(description = "회원 ID", example = "24886e22-6f45-4449-a153-43f58e9b519a")
        UUID memberUUID,
        @Schema(description = "회원이 보유한 클래스 목록")
        List<LessonResponse> lessonList
) {
    public static MemberLessonViewResponse from(UUID memberUUID, List<LessonResponse> lessonList) {
        return new MemberLessonViewResponse(
                memberUUID,
                lessonList
        );
    }
}
