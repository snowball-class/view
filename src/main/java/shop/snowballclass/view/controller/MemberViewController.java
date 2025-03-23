package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.member.MemberLessonViewResponse;
import shop.snowballclass.view.service.MemberViewService;

@Tag(name = "회원 View API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/view/members")
public class MemberViewController {
    private final MemberViewService memberViewService;

    @Operation(summary = "회원이 보유한 모든 강의 조회")
    @GetMapping("/lessons")
    public ApiResponse<MemberLessonViewResponse> getMemberLessonView(@Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ApiResponse.success(memberViewService.getMemberLessonView(token));
    }

}
