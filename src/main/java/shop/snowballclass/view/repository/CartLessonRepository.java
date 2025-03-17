package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.snowballclass.view.entity.CartLesson;

import java.util.List;

@Repository
public interface CartLessonRepository extends JpaRepository<CartLesson, Long> {
    List<CartLesson> findByCartId(Long cartId);
}
