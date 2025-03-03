package shop.snowballclass.view.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.entity.LessonReview;
import shop.snowballclass.view.repository.LessonReviewRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LessonReviewService {
    private final LessonReviewRepository lessonReviewRepository;

    @Transactional(readOnly = true)
    public List<LessonReview> getLessonReviewListByLessonId(Long lessonId) {
        return lessonReviewRepository.findByLessonId(lessonId);
    }

}
