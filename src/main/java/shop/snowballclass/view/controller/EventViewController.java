package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.event.EventLessonCreateRequest;
import shop.snowballclass.view.dto.event.EventViewResponse;
import shop.snowballclass.view.service.EventLessonService;
import shop.snowballclass.view.service.EventViewService;

@Tag(name = "이벤트 View API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/view/events")
public class EventViewController {
    private final EventLessonService eventLessonService;
    private final EventViewService eventViewService;

    @Hidden
    @Operation(summary = "EventLesson 레코드 생성")
    @PostMapping("/{eventId}/lessons")
    public ApiResponse createEventLessons(@PathVariable("eventId") Long eventId, @RequestBody EventLessonCreateRequest lessonIds) {
        eventLessonService.createEventLessons(eventId, lessonIds);
        return ApiResponse.success();
    }

    @Operation(summary = "이벤트 상세 정보 조회")
    @GetMapping("/{eventId}")
    public ApiResponse<EventViewResponse> getLessonView(@PathVariable("eventId") Long eventId) {
        return ApiResponse.success(eventViewService.getLessonView(eventId));
    }

}
