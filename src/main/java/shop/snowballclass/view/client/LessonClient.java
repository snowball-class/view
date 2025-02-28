package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "lesson-service", url = "${feign.snowball.lesson.url}")
public interface LessonClient {

}
