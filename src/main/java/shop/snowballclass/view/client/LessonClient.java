package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.lesson.LessonResponse;

import java.util.List;

@FeignClient(name = "lesson-service", url = "${feign.snowball.lesson}")
public interface LessonClient {
    @GetMapping("/{lessonId}")
    ApiResponse<LessonResponse> getLessonByLessonId(@PathVariable("lessonId") Long lessonId);

    @GetMapping("/bulk")
    ApiResponse<List<LessonResponse>> getBulkLessons(@RequestParam("ids") String lessonIds);

}
