package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@Table(name = "event_lesson_view")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLessonView {
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
