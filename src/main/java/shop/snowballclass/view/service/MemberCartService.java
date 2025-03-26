package shop.snowballclass.view.service;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.snowballclass.view.client.EventClient;
import shop.snowballclass.view.client.LessonClient;
import shop.snowballclass.view.client.MemberClient;
import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.dto.membercart.AddMemberCartLessonDto;
import shop.snowballclass.view.dto.membercart.GetMemberCartLessonsDto;
import shop.snowballclass.view.entity.CartLesson;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.entity.MemberCart;
import shop.snowballclass.view.exception.ErrorCode;
import shop.snowballclass.view.exception.common.EntityNotFoundException;
import shop.snowballclass.view.repository.CartLessonRepository;
import shop.snowballclass.view.repository.EventLessonRepository;
import shop.snowballclass.view.repository.MemberCartRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberCartService {
    private final MemberCartRepository memberCartRepository;
    private final CartLessonRepository cartLessonRepository;
    private final LessonClient lessonClient;
    private final EventClient eventClient;
    private final EventLessonRepository eventLessonRepository;
    private final MemberClient memberClient;

    public GetMemberCartLessonsDto getCart(String token) {
         UUID memberUUID = memberClient.getMemberInfo(token).data().memberUUID();
        MemberCart memberCart = getMemberCart(memberUUID);
        List<CartLesson> cartLessons = cartLessonRepository.findByCartId(memberCart.getCartId());
        return GetMemberCartLessonsDto.create(memberCart,cartLessons);
    }

    @Transactional
    public Boolean addCartLesson(String token, AddMemberCartLessonDto addMemberCartLessonDto) {
        try {
            UUID memberUUID = memberClient.getMemberInfo(token).data().memberUUID();
            Long lessonId = addMemberCartLessonDto.getLessonId();

            MemberCart memberCart = getMemberCart(memberUUID);
            LessonResponse lesson = lessonClient.getLessonByLessonId(lessonId).data();
            EventLesson eventLesson = eventLessonRepository.findByLessonId(lessonId).get(0);
            EventResponse event = eventClient.getEventByEventId(eventLesson.getEventId()).data();
            CartLesson cartLesson = CartLesson.create(memberCart.getCartId(), event, lesson);

            cartLessonRepository.save(cartLesson);
            return true;
        } catch (FeignException e) {
            return false;
        }
    }

    public MemberCart getMemberCart(UUID memberUUID) {
        return memberCartRepository
                .findByMemberUUID(UUID.randomUUID())
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.EVENT_LESSON_NOT_FOUND, "존재 하지 않는 MemberCart [memberUUID:" + memberUUID + "]")
                );
    }
}
