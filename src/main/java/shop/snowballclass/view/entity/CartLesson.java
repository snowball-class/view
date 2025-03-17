package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
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
}
