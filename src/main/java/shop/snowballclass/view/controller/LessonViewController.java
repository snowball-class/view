package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.lesson.LessonDetailsResponse;
import shop.snowballclass.view.service.LessonViewService;

@Tag(name = "강의 조회 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/lessons/view")
public class LessonViewController {
    private final LessonViewService lessonViewService;

    @Operation(summary = "강의 상세 정보 조회 API")
    @GetMapping("/{lessonId}")
    public ApiResponse<LessonDetailsResponse> getLessonView(@PathVariable("lessonId") Long lessonId) {
        return ApiResponse.success(lessonViewService.getLessonView(lessonId));
    }

}
