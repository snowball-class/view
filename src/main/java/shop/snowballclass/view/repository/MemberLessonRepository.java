package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.snowballclass.view.entity.MemberLesson;

public interface MemberLessonRepository extends JpaRepository<MemberLesson, Long> {

}
