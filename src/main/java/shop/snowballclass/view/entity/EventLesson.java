package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "event_lesson",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_event_lesson",
                        columnNames = {
                                "eventId",
                                "lessonId"
                        }
                )
        }
)
public class EventLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long eventId;
    @Column(nullable = false)
    private Long lessonId;
    private String content;

    @Builder
    private EventLesson(Long eventId, Long lessonId) {
        this.eventId = eventId;
        this.lessonId = lessonId;
    }

    public static EventLesson from(Long eventId, Long lessonId) {
        return EventLesson.builder()
                .eventId(eventId)
                .lessonId(lessonId)
                .build();
    }

}
