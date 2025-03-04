package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.event.EventLessonCreateRequest;
import shop.snowballclass.view.service.EventLessonService;

@Tag(name = "이벤트 View API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/view/events")
public class EventViewController {
    private final EventLessonService eventLessonService;

    @Hidden
    @Operation(summary = "EventLesson 레코드 생성")
    @PostMapping("/{eventId}/lessons")
    public ApiResponse createEventLessons(@PathVariable("eventId") Long eventId, @RequestBody EventLessonCreateRequest lessonIds) {
        eventLessonService.createEventLessons(eventId, lessonIds);
        return ApiResponse.success();
    }

}
