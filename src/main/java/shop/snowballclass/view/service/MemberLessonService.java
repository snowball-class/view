package shop.snowballclass.view.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.dto.memberlesson.MemberLessonCreateRequest;
import shop.snowballclass.view.entity.MemberLesson;
import shop.snowballclass.view.repository.MemberLessonRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberLessonService {
    private final MemberLessonRepository memberLessonRepository;

    @Transactional
    public void createMemberLessons(UUID memberId, MemberLessonCreateRequest request) {
        List<MemberLesson> memberLessonList = request.lessonIds().stream()
                .map(lessonId -> MemberLesson.from(memberId, lessonId))
                .collect(Collectors.toList());
        try {
            memberLessonRepository.saveAll(memberLessonList);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 구매한 강의가 포함되어 있거나 올바르지 않은 요청입니다.");
        }
    }

}
