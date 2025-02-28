package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import shop.snowballclass.view.common.ApiResponse;
import shop.snowballclass.view.dto.lesson.ApplyEventToLessonRequest;
import shop.snowballclass.view.dto.lesson.LessonResponse;

import java.util.List;

@FeignClient(name = "admin-service", url = "${feign.snowball.admin.url}")
public interface AdminClient {

}
