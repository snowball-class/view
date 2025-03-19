package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.membercart.GetMemberCartLessonsDto;
import shop.snowballclass.view.service.MemberCartService;

import java.util.UUID;

@Tag(name = "MemberCart 엔티티 관련 API")
@RequiredArgsConstructor
@RestController
public class MemberCartController {
    private final MemberCartService memberCartService;

    @Operation(summary = "카트 조회")
    @GetMapping("/member/cart")
    public ApiResponse<GetMemberCartLessonsDto> getCart(
        @RequestHeader UUID memberUUID
    ) {
        return ApiResponse.success(memberCartService.getMemberCartLessons(memberUUID));
    }
}
