package shop.snowballclass.view.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.eventlesson.EventLessonCreateRequest;
import shop.snowballclass.view.dto.eventlesson.EventLessonResponse;
import shop.snowballclass.view.service.EventLessonService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Tag(name = "EventLesson 엔티티 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/event-lesson")
public class EventLessonController {
    private final EventLessonService eventLessonService;

    @Operation(summary = "EventLesson 레코드 생성")
    @PostMapping("/events/{eventId}/lessons")
    public ApiResponse createEventLessons(@PathVariable("eventId") Long eventId, @RequestBody EventLessonCreateRequest request) {
        eventLessonService.createEventLessons(eventId, request);
        return ApiResponse.success();
    }

    @Operation(summary = "lessonId들로 EventLesson 벌크 조회")
    @GetMapping("/lessons/bulk")
    public ApiResponse<List<EventLessonResponse>> getEventLessonListByLessonIds(@RequestParam("ids") String lessonIds) {
        List<Long> ids = Arrays.stream(lessonIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return ApiResponse.success(eventLessonService.getBulkEventLessonsByLessonIds(ids));
    }

}
