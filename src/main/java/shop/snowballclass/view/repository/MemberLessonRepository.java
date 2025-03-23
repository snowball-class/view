package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.snowballclass.view.entity.MemberLesson;

import java.util.List;
import java.util.UUID;

public interface MemberLessonRepository extends JpaRepository<MemberLesson, Long> {

    List<MemberLesson> findByMemberId(UUID memberId);

}
