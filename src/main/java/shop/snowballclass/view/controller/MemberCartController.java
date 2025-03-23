package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.membercart.AddMemberCartLessonDto;
import shop.snowballclass.view.dto.membercart.GetMemberCartLessonsDto;
import shop.snowballclass.view.service.MemberCartService;

@Tag(name = "MemberCart 엔티티 관련 API")
@RequiredArgsConstructor
@RestController
public class MemberCartController {
    private final MemberCartService memberCartService;

    @Operation(summary = "카트 조회")
    @GetMapping("/member/cart")
    public ApiResponse<GetMemberCartLessonsDto> getCart(
        @RequestHeader("Authorization") String token
    ) {
        return ApiResponse.success(memberCartService.getCart(token));
    }

    @Operation(summary = "강의 장바구니 담기")
    @PostMapping("/member/cart")
    public ApiResponse<Boolean> addCartLesson(
            @RequestHeader("Authorization") String token,
            @RequestBody AddMemberCartLessonDto addMemberCartLessonDto
            ) {
        return ApiResponse.success(memberCartService.addCartLesson(token, addMemberCartLessonDto));
    }
}
