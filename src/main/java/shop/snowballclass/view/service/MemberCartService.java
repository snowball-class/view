package shop.snowballclass.view.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.snowballclass.view.dto.membercart.GetMemberCartLessonsDto;
import shop.snowballclass.view.entity.CartLesson;
import shop.snowballclass.view.entity.MemberCart;
import shop.snowballclass.view.exception.ErrorCode;
import shop.snowballclass.view.exception.common.EntityNotFoundException;
import shop.snowballclass.view.repository.CartLessonRepository;
import shop.snowballclass.view.repository.MemberCartRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberCartService {
    private final MemberCartRepository memberCartRepository;
    private final CartLessonRepository cartLessonRepository;

    public GetMemberCartLessonsDto getMemberCartLessons(UUID memberUUID) {
        MemberCart memberCart = memberCartRepository.findByMemberUUID(memberUUID)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EVENT_LESSON_NOT_FOUND, "존재하지 않는 MemberCart입니다. memberUUID: " + memberUUID.toString()));
        List<CartLesson> cartLessons = cartLessonRepository.findByCartId(memberCart.getCartId());
        return GetMemberCartLessonsDto.create(memberCart,cartLessons);
    }
}
