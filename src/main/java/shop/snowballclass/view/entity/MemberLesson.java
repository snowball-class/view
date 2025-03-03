package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "member_lesson",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_lesson",
                        columnNames = {
                                "memberId",
                                "lesson_id"
                        }
                )
        }
)
public class MemberLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID memberId;

    @Column(nullable = false)
    private Long lessonId;

    private String content;
}
