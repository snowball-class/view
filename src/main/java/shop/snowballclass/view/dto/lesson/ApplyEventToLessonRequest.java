package shop.snowballclass.view.dto.lesson;

import java.time.LocalDateTime;
import java.util.List;

public record ApplyEventToLessonRequest(
        Long eventId,
        Integer discountRate,
        LocalDateTime discountStartDate,
        LocalDateTime discountFinishDate,
        List<Long> lessonIds
) {
}
