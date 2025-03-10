package shop.snowballclass.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.event.EventViewResponse;
import shop.snowballclass.view.service.EventViewService;

import java.util.List;

@Tag(name = "이벤트 View API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/view/events")
public class EventViewController {
    private final EventViewService eventViewService;

    @Operation(summary = "이벤트 상세 정보 조회")
    @GetMapping("/{eventId}")
    public ApiResponse<EventViewResponse> getEventView(@PathVariable("eventId") Long eventId) {
        return ApiResponse.success(eventViewService.getEventView(eventId));
    }

    @Operation(summary = "진행중인 모든 이벤트 상세 정보 조회")
    @GetMapping("/active")
    public ApiResponse<List<EventViewResponse>> getActivatedEventList() {
        return ApiResponse.success(eventViewService.getActiveEventList());
    }

}
