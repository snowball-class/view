package shop.snowballclass.view.dto.memberCart;

import shop.snowballclass.view.entity.CartLesson;
import shop.snowballclass.view.entity.MemberCart;

import java.util.List;
import java.util.UUID;

public record GetMemberCartLessonsDto(
        UUID memberUUID,
        Long cartId,
        List<CartLessonDto> lessons
) {
    public static GetMemberCartLessonsDto create(MemberCart memberCart, List<CartLesson> cartLessons) {
        return new GetMemberCartLessonsDto(
                memberCart.getMemberUUID(),
                memberCart.getCartId(),
                cartLessons
                        .stream()
                        .map(CartLessonDto::from)
                        .toList()
        );
    }
}
