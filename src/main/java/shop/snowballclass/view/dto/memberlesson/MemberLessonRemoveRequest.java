package shop.snowballclass.view.dto.memberlesson;

import java.util.List;

public record MemberLessonRemoveRequest(
        List<Long> lessonIds
) {
}
