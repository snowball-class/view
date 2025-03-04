package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.lesson.LessonViewResponse;
import shop.snowballclass.view.service.LessonViewService;

@Tag(name = "강의 View API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/view/lessons")
public class LessonViewController {
    private final LessonViewService lessonViewService;

    @Operation(summary = "강의 상세 정보 조회")
    @GetMapping("/{lessonId}")
    public ApiResponse<LessonViewResponse> getLessonView(@PathVariable("lessonId") Long lessonId) {
        return ApiResponse.success(lessonViewService.getLessonView(lessonId));
    }

}
