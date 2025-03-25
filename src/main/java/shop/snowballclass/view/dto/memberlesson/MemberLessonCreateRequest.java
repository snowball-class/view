package shop.snowballclass.view.dto.memberlesson;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MemberLessonCreateRequest(
        @NotNull(message = "회원이 보유한 강의에 추가할 강의를 입력해 주세요.")
        @Size(min = 1, message = "하나 이상의 클래스를 포함해야 합니다.")
        @Schema(description = "회원이 보유한 강의에 추가할 클래스 목록 (id)", example = "[1, 15, 6, 7, 2]")
        List<Long> lessonIds
) {
}
