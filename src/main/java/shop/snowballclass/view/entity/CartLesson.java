package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.lesson.LessonResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
public class CartLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long cartId;
    @Column(nullable = false)
    Long lessonId;
    @Column(nullable = false)
    String lessonThumbnail;
    @Column(nullable = false)
    String lessonTitle;
    @Column(nullable = false)
    Long discount;
    @Column(nullable = false)
    Long originPrice;
    @Column(nullable = false)
    Long totalPrice;
    @Column(nullable = false)
    @CreatedDate
    LocalDateTime createdAt;

    private CartLesson(
        Long cartId, Long lessonId, String lessonThumbnail, String lessonTitle, Long discount, Long originPrice, Long totalPrice, LocalDateTime createdAt
    ) {
        this.cartId = cartId;
        this.lessonId = lessonId;
        this.lessonThumbnail = lessonThumbnail;
        this.lessonTitle = lessonTitle;
        this.discount = discount;
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public static CartLesson create(Long cartId, EventResponse event, LessonResponse lesson) {
        Long discountPrice = Integer.toUnsignedLong(lesson.price() * (1 - event.discountRate()/100));
        return new CartLesson(
                cartId,
                lesson.lessonId(),
                lesson.thumbnailUrl(),
                lesson.title(),
                discountPrice,
                lesson.price().longValue(),
                lesson.price() - discountPrice,
                LocalDateTime.now()
        );
    }
}
