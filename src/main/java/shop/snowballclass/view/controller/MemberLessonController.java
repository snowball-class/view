package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.memberlesson.MemberLessonCreateRequest;
import shop.snowballclass.view.service.MemberLessonService;

import java.util.UUID;

@Hidden
@Tag(name = "MemberLesson 엔티티 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member-lesson")
public class MemberLessonController {
    private final MemberLessonService memberLessonService;

    @Operation(summary = "MemberLesson 레코드 생성")
    @PostMapping("/members/{memberId}/lessons")
    public ApiResponse createEventLessons(@PathVariable("memberId") UUID memberId, @RequestBody MemberLessonCreateRequest request) {
        memberLessonService.createMemberLessons(memberId, request);
        return ApiResponse.success();
    }

}
