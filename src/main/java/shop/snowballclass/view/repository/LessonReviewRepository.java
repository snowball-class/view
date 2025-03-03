package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.snowballclass.view.entity.LessonReview;

import java.util.List;

public interface LessonReviewRepository extends JpaRepository<LessonReview, Long> {

    List<LessonReview> findByLessonId(Long lessonId);

}
