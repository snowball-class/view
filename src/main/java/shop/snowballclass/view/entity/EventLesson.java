package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    
    // Kafka 도입시 컬럼 추가, DB Postgresql로 변경, json 데이터 파일 String으로 저장
    private String content;
}
