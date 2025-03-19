package shop.snowballclass.view.dto.membercart;

import shop.snowballclass.view.entity.CartLesson;

import java.time.LocalDateTime;

public record CartLessonDto(
        Long lessonId,
        String lessonThumbnail,
        String lessonTitle,
        Long discount,
        Long originPrice,
        Long totalPrice,
        LocalDateTime createdAt
) {
    public static CartLessonDto from(CartLesson cartLesson) {
        return new CartLessonDto(
                cartLesson.getLessonId(),
                cartLesson.getLessonThumbnail(),
                cartLesson.getLessonTitle(),
                cartLesson.getDiscount(),
                cartLesson.getOriginPrice(),
                cartLesson.getTotalPrice(),
                cartLesson.getCreatedAt()
        );
    }
}
