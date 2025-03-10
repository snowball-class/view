package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.event.EventResponse;

import java.util.List;

@FeignClient(name = "admin-service", url = "${feign.snowball.admin.event}")
public interface EventClient {
    @GetMapping("/{eventId}")
    ApiResponse<EventResponse> getEventByEventId(@PathVariable("eventId") Long eventId);

    @GetMapping
    ApiResponse<List<EventResponse>> getAllEvent();

}
